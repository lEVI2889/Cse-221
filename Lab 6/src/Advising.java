import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Advising {
     public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         int[] input = Arrays.stream(br.readLine().split(" "))
                 .mapToInt(Integer::parseInt)
                 .toArray();
         int n = input[0], m = input[1];

         List<List<Integer>> graph = new ArrayList<>();
         for (int i = 0; i <= n; i++) {
             graph.add(new ArrayList<>());
         }

         int[] indegree = new int[n + 1];

         for (int i = 0; i < m; i++) {
             int[] arr = Arrays.stream(br.readLine().split(" "))
                     .mapToInt(Integer::parseInt)
                     .toArray();

             int pre = arr[0], course = arr[1];
             graph.get(pre).add(course);
             indegree[course]++;
         }

         Queue<Integer> q = new LinkedList<>();
         for (int i = 1; i <= n; i++) {
             if(indegree[i] == 0){
                 q.offer(i);
             }
         }

         List<Integer> ans = new ArrayList<>();
         while(!q.isEmpty()){
             int curr = q.poll();
             ans.add(curr);
             for(int neigh : graph.get(curr)){
                 indegree[neigh]--;// ekhane visited bujhacche jonno ekhane value komailam
                 if(indegree[neigh] == 0){
                     q.offer(neigh);
                 }
             }
         }

         if(ans.size() != n) System.out.println(-1);
         else{
             for (int node : ans) {
                 System.out.print(node + " ");
             }
         }
     }
}

         /*List<List<Integer>> graph = new ArrayList<>();
         int[] inDegree = new int[n + 1];

         for (int i = 0; i <= n; i++) {
             graph.add(new ArrayList<>());
         }

                    // Build the graph
         for (int i = 0; i < m; i++) {
             int[] courses = Arrays.stream(br.readLine().split(" "))
                     .mapToInt(Integer::parseInt)
                     .toArray();
             int prerequisite = courses[0], course = courses[1];
             graph.get(prerequisite).add(course);
             inDegree[course]++;
         }

                    // Topological sort using Kahn's algorithm
         Queue<Integer> queue = new LinkedList<>();
         for (int i = 1; i <= n; i++) {
             if (inDegree[i] == 0) {
                 queue.offer(i);
             }
         }

         List<Integer> result = new ArrayList<>();
         while (!queue.isEmpty()) {
             int current = queue.poll();
             result.add(current);

             for (int neighbor : graph.get(current)) {
                 inDegree[neighbor]--;
                 if (inDegree[neighbor] == 0) {
                     queue.offer(neighbor);
                 }
             }
         }

                    // Check if all courses can be completed
         if (result.size() != n) {
             System.out.println(-1);
         } else {
             for (int i = 0; i < result.size(); i++) {
                 if (i > 0) System.out.print(" ");
                 System.out.print(result.get(i));
             }
             System.out.println();
         }*/

// Dfs approach (commented out for reference)
/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AdvisingDFS {
    private static List<List<Integer>> graph;
    private static int[] visited; // 0: unvisited, 1: visiting, 2: visited
    private static Stack<Integer> result;
    private static boolean hasCycle = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = input[0], m = input[1];

        // Initialize graph and visited array
        graph = new ArrayList<>();
        visited = new int[n + 1];
        result = new Stack<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // Build the graph
        for (int i = 0; i < m; i++) {
            int[] courses = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int prerequisite = courses[0], course = courses[1];
            graph.get(prerequisite).add(course);
        }

        // Perform DFS on all unvisited nodes
        for (int i = 1; i <= n; i++) {
            if (visited[i] == 0) {
                dfs(i);
                if (hasCycle) break;
            }
        }

        if (hasCycle) {
            System.out.println(-1);
        } else {
            List<Integer> topOrder = new ArrayList<>();
            while (!result.isEmpty()) {
                topOrder.add(result.pop());
            }
            for (int i = 0; i < topOrder.size(); i++) {
                if (i > 0) System.out.print(" ");
                System.out.print(topOrder.get(i));
            }
            System.out.println();
        }
    }

    private static void dfs(int node) {
        if (hasCycle) return;

        visited[node] = 1; // Mark as visiting (gray)

        for (int neighbor : graph.get(node)) {
            if (visited[neighbor] == 1) {
                // Back edge found - cycle detected
                hasCycle = true;
                return;
            } else if (visited[neighbor] == 0) {
                dfs(neighbor);
            }
        }

        visited[node] = 2; // Mark as visited (black)
        result.push(node); // Add to result in post-order
    }
}*/