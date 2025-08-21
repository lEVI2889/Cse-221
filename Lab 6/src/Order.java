import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Order {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // Using a Set to store unique characters from the words
        String [] wordsArray = new String[n];
        Set<Character> words = new HashSet<>();

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            wordsArray[i] = word;
            for(char c : word.toCharArray()){
                words.add(c);
            }
        }

        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> in_degree = new HashMap<>();

        for(char c : words){
            graph.put(c, new HashSet<>());
            in_degree.put(c, 0);
        }

        for (int i = 0; i < n - 1; i++) {
            String w1 = wordsArray[i];
            String w2 = wordsArray[i + 1];

            if(w1.length()>w2.length()&&w1.startsWith(w2)){
                System.out.println(-1);
                return;
            }

            int minLength = Math.min(w1.length(), w2.length());
            for (int j = 0; j < minLength; j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);

                if(c1!=c2){
                    if(!graph.get(c1).contains(c2)){
                        graph.get(c1).add(c2);
                        in_degree.put(c2, in_degree.get(c2) + 1);
                    }
                    break;
                }

            }
        }

        PriorityQueue<Character> q = new PriorityQueue<>();
        for(char c : words){
            if(in_degree.get(c)==0){
                q.offer(c);
            }
        }

        StringBuilder result = new StringBuilder();
        while(!q.isEmpty()){
            char c = q.poll();
            result.append(c);
            for(char ne : graph.get(c)){
                in_degree.put(ne, in_degree.get(ne)-1);
                if(in_degree.get(ne)==0){
                    q.offer(ne);
                }
            }
        }

        if(result.length()!= words.size()) System.out.println(-1);
        else System.out.println(result.toString());
    }
}
