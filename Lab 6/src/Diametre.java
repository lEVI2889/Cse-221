import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Diametre {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = n-1;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <=n; i++) graph.add(new ArrayList<>());
        for (int q = 0; q < m; q++) {
            int[] edges = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int u = edges[0], v = edges[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[] path1 = bfs(graph, 1, n);
        int hehe = path1[0];
        int[] path2 = bfs(graph, hehe, n);
        int diameter = path2[1];

        System.out.println(diameter);
        System.out.println(hehe + " " + path2[0]);
    }
    static int[] bfs(List<List<Integer>> graph, int st, int size) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[size + 1];
        int farthestNode = st;
        int maxDistance = 0;

        queue.offer(new int[]{st, 0});
        visited[st] = true;
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int node = curr[0], distance = curr[1];
            if(distance > maxDistance){
                maxDistance = distance;
                farthestNode = node;
            }

            for(int neighbor : graph.get(node)){
                if(!visited[neighbor]){
                    visited[neighbor] = true;
                    queue.offer(new int[]{neighbor, distance + 1});
                }
            }
        }
        return new int[]{farthestNode, maxDistance};
    }
}