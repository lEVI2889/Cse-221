import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class NearestTour {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Fast input parsing
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        int s = Integer.parseInt(firstLine[2]);
        int q = Integer.parseInt(firstLine[3]);

        List<List<Integer>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // Read edges
        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            int st = Integer.parseInt(edge[0]);
            int end = Integer.parseInt(edge[1]);
            graph.get(st).add(end);
            graph.get(end).add(st);
        }

        // Read sources
        String[] sourceStr = br.readLine().split(" ");
        int[] sources = new int[s];
        for (int i = 0; i < s; i++) {
            sources[i] = Integer.parseInt(sourceStr[i]);
        }

        // Read destinations
        String[] destStr = br.readLine().split(" ");
        int[] destinations = new int[q];
        for (int i = 0; i < q; i++) {
            destinations[i] = Integer.parseInt(destStr[i]);
        }

        // Precompute distances using multi-source BFS
        int[] distances = precomputeDistances(graph, n, sources);

        // Fast output using StringBuilder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            sb.append(distances[destinations[i]]);
            if (i < q - 1) sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    static int[] precomputeDistances(List<List<Integer>> graph, int n, int[] sources) {
        int[] distances = new int[n + 1];
        Arrays.fill(distances, -1);

        // Use ArrayDeque instead of LinkedList for better performance
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];

        // Multi-source BFS: Add all sources at distance 0
        for (int source : sources) {
            if (!visited[source]) {
                queue.offer(source);
                visited[source] = true;
                distances[source] = 0;
            }
        }

        int currentDistance = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            currentDistance++;

            // Process all nodes at current distance level
            for (int i = 0; i < levelSize; i++) {
                int node = queue.poll();

                for (int neighbor : graph.get(node)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        distances[neighbor] = currentDistance;
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return distances;
    }
}





/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class NearestTour  {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = inputs[0], m = inputs[1], s = inputs[2], q = inputs[3];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int[] edges = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int st = edges[0], end = edges[1];
            graph.get(st).add(end);
            graph.get(end).add(st);
        }


        int[] sources = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] destinations = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] precomputed = precomputeDistances(graph, n, sources);
        for (int i = 0; i < q; i++) {
            System.out.print(precomputed[destinations[i]] + " ");
        }

    }

    static int[] precomputeDistances(List<List<Integer>> graph, int n, int[] sources) {
        int[] distances = new int[n + 1];
        Arrays.fill(distances, -1);

        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        // Multi-source BFS from all sources
        for (int source : sources) {
            queue.offer(new int[]{source, 0});
            visited[source] = true;
            distances[source] = 0;
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0], distance = curr[1];

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distances[neighbor] = distance + 1;
                    queue.offer(new int[]{neighbor, distance + 1});
                }
            }
        }

        return distances;
    }


}*/
/*
static int bfs(List<List<Integer>> graph, int n, int s, int[] sources) {
    //amar kora
        */
/*int minimumDistance = Integer.MAX_VALUE;
        boolean reached = false;
        for(int source : sources) {
            if(source == s) {
                minimumDistance = 0;
                break;
            }
            Queue<int[]> queue = new java.util.LinkedList<>();
            boolean[] set = new boolean[n + 1];

            int min_dis = 0;
            queue.offer(new int[]{s, 0});
            set[s] = true;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int node = cur[0], distance = cur[1];

                for (int neighbor : graph.get(node)) {

                    if (neighbor == source) {
                        set[neighbor] = true;
                        min_dis = distance + 1;
                        reached = true;
                        break;
                    }

                    if(!set[neighbor]) {
                        set[neighbor] = true;
                        queue.offer(new int[]{neighbor, distance + 1});
                    }
                }
            }

            minimumDistance = Math.min(minimumDistance, min_dis);
        }
        return reached ? minimumDistance : -1;*//*

    Set<Integer> set = new HashSet<>();
    for(int src : sources) {
        set.add(src);
        if(src == s) return 0;
    }
    Queue<int[]> queue = new LinkedList<>();
    boolean[] visited = new boolean[n+1];

    queue.offer(new int[]{s, 0});
    visited[s] = true;
    while(!queue.isEmpty()) {
        int[] curr = queue.poll();
        int node = curr[0], distance = curr[1];
        if(set.contains(node)) {
            return distance;
        }
        for(int neighbor : graph.get(node)) {
            if(!visited[neighbor]) {
                visited[neighbor] = true;
                queue.offer(new int[]{neighbor, distance + 1});
            }
        }
    }
    return -1;
}*/
