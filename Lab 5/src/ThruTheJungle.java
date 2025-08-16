
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ThruTheJungle {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = inputs[0], m = inputs[1], s = inputs[2], d = inputs[3], k = inputs[4];
        if(m<0) {
            System.out.println(-1);
            return;
        };
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n ; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
        }

        if(s==d && s == k){
            System.out.println(0);
            System.out.println(s);
            return;
        }

        int[] st2k = bfs(graph, s, k, n);
        int[] k2d = bfs(graph, k, d, n);

        if (st2k == null || k2d == null) {
            System.out.println(-1);
            return;
        }

        List<Integer> ans = new ArrayList<>();
        for(int node : st2k) {
            ans.add(node);
        }
        for (int i = 1; i < k2d.length; i++) {
            ans.add(k2d[i]);
        }
        System.out.println(ans.size() - 1);
        for(int node : ans) {
            System.out.print(node + " ");
        }
    }
    static int[] bfs(List<List<Integer>> graph, int s, int d, int n){
        if (s == d) return new int[]{s};
        Queue<Integer> q = new LinkedList<>();
        int[] parent = new int[n + 1];
        int[] dist = new int[n + 1];

        Arrays.fill(dist, -1);
        parent[s] = -1;
        dist[s] = 0;
        q.add(s);

        while(!q.isEmpty()){
            int curr = q.poll();
            for(int neighbor : graph.get(curr)){
                if(dist[neighbor] == -1){
                    dist[neighbor] = dist[curr] + 1;
                    parent[neighbor] = curr;
                    q.add(neighbor);
                }
                if(neighbor == d){
                    List<Integer> path = new ArrayList<>();
                    for (int current = d; current != -1; current = parent[current]) path.add(current);
                    Collections.reverse(path);
                    return path.stream().mapToInt(i -> i).toArray();
                }
            }
        }

        return null;
    }
}
/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ThruTheJungle {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = inputs[0], m = inputs[1], s = inputs[2], d = inputs[3], k = inputs[4];

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
        }

        // Special case: if S == D == K
        if (s == d && s == k) {
            System.out.println(0);
            System.out.println(s);
            return;
        }

        // Find path S to K
        int[] pathSK = bfs(graph, s, k, n);
        // Find path K to D
        int[] pathKD = bfs(graph, k, d, n);

        if (pathSK == null || pathKD == null) {
            System.out.println(-1);
            return;
        }

        // Combine paths (remove duplicate K)
        List<Integer> fullPath = new ArrayList<>();
        for (int node : pathSK) fullPath.add(node);
        for (int i = 1; i < pathKD.length; i++) fullPath.add(pathKD[i]);

        System.out.println(fullPath.size() - 1);
        for (int i = 0; i < fullPath.size(); i++) {
            System.out.print(fullPath.get(i));
            if (i < fullPath.size() - 1) System.out.print(" ");
        }
        System.out.println();
    }

    static int[] bfs(List<List<Integer>> graph, int start, int end, int n) {
        if (start == end) return new int[]{start};

        Queue<Integer> q = new LinkedList<>();
        int[] parent = new int[n + 1];
        boolean[] visited = new boolean[n + 1];

        q.add(start);
        visited[start] = true;
        parent[start] = -1;

        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int neighbor : graph.get(curr)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    parent[neighbor] = curr;
                    q.add(neighbor);

                    if (neighbor == end) {
                        // Reconstruct path
                        List<Integer> path = new ArrayList<>();
                        for (int current = end; current != -1; current = parent[current]) {
                            path.add(current);
                        }
                        Collections.reverse(path);
                        return path.stream().mapToInt(i -> i).toArray();
                    }
                }
            }
        }
        return null; // No path found
    }
}*/
