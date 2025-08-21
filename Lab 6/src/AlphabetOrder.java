//*******************************    A I ***************************


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AlphabetOrder {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] words = new String[n];
        Set<Character> allChars = new HashSet<>();

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
            for (char c : words[i].toCharArray()) {
                allChars.add(c);
            }
        }

        // Build graph from word comparisons
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        // Initialize graph
        for (char c : allChars) {
            graph.put(c, new HashSet<>());
            indegree.put(c, 0);
        }

        // Compare adjacent words to build dependencies
        for (int i = 0; i < n - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            // Check for invalid case: longer word before its prefix
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                System.out.println(-1);
                return;
            }

            // Find first differing character
            int minLen = Math.min(word1.length(), word2.length());
            for (int j = 0; j < minLen; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);

                if (c1 != c2) {
                    // c1 should come before c2 in alphabet
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        indegree.put(c2, indegree.get(c2) + 1);
                    }
                    break;
                }
            }
        }

        // Topological sort with lexicographically smallest order
        PriorityQueue<Character> pq = new PriorityQueue<>();

        // Add all characters with indegree 0
        for (char c : allChars) {
            if (indegree.get(c) == 0) {
                pq.offer(c);
            }
        }

        StringBuilder result = new StringBuilder();

        while (!pq.isEmpty()) {
            char current = pq.poll();
            result.append(current);

            // Reduce indegree of neighbors
            for (char neighbor : graph.get(current)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    pq.offer(neighbor);
                }
            }
        }

        // Check if all characters are included (no cycle)
        if (result.length() != allChars.size()) {
            System.out.println(-1);
        } else {
            System.out.println(result.toString());
        }
    }
}