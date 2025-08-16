import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CycleDetection {
    static List<List<Integer>> graph;
    static boolean[] visited;
    static boolean[] marked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int num = inputs[0], edges = inputs[1];

        if (num < 2) {
            System.out.println("NO");
            return;
        }

        graph = new ArrayList<>();
        for (int i = 0; i <= num ; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges; i++) {
            int[] edge = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
        }

        visited = new boolean[num+1];
        marked = new boolean[num+1];
        boolean has = false;

        for (int i = 1; i <= num ; i++) {
            if(!visited[i]) {
                if (dfs(i)) {
                    has = true;
                    break;
                }
            }
        }
        System.out.println(has ? "YES" : "NO");
    }

    static boolean dfs(int node) {
        visited[node] = true;
        marked[node] = true;
        for(int neighbor : graph.get(node)){
            if(marked[neighbor]){
                return true;
            }
            if(!visited[neighbor] && dfs(neighbor)){
                return true;
            }
        }
        marked[node] = false;
        return false;
    }
}
/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CycleDetection {
    static List<List<Integer>> graph;
    static boolean[] visited;
    static boolean[] recStack; // Recursion stack for cycle detection

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int num = inputs[0], edges = inputs[1];

        graph = new ArrayList<>();
        for (int i = 0; i <= num; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            int[] edge = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
        }

        visited = new boolean[num + 1];
        recStack = new boolean[num + 1];

        boolean hasCycle = false;
        for (int i = 1; i <= num; i++) {
            if (!visited[i]) {
                if (dfs(i)) {
                    hasCycle = true;
                    break;
                }
            }
        }

        System.out.println(hasCycle ? "YES" : "NO");
    }

    static boolean dfs(int node) {
        visited[node] = true;
        recStack[node] = true; // Add to recursion stack

        for (int neighbor : graph.get(node)) {
            // If neighbor is in current recursion path = cycle found
            if (recStack[neighbor]) {
                return true;
            }
            // If unvisited neighbor has cycle = cycle found
            if (!visited[neighbor] && dfs(neighbor)) {
                return true;
            }
        }

        recStack[node] = false; // Remove from recursion stack
        return false;
    }
}*/
