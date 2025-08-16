import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CanYouTraverse_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().trim().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);

        // Build the graph as an adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().trim().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }


        bfs(graph, n);
    }

    static void bfs(List<List<Integer>> graph, int size){
        boolean[] visited = new boolean[ size + 1 ];
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> path = new ArrayList<>();

        queue.add(1);
        visited[1] = true;
        while(!queue.isEmpty()){
            int current = queue.poll();
            path.add(current);
            for(int options : graph.get(current)){
                if(!visited[options]){
                    visited[options] = true;
                    queue.add(options);
                }
            }
        }
        for(int paths : path){
            System.out.print(paths + " ");
        }
    }
}
