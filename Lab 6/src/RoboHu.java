import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RoboHu {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = input[0], m = input[1];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int [] tackle = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            graph.get(tackle[0]).add(tackle[1]);
            graph.get(tackle[1]).add(tackle[0]);
        }

        int max = 0;
        int[] color = new int[n+1];
        for (int i = 1; i <= n; i++) {
            if(color[i] == 0){
                int[] max_tackle = bipartite(graph, i, color);
                max += Math.max(max_tackle[0], max_tackle[1]);
            }
        }

        System.out.println(max);
    }
    static int[] bipartite(List<List<Integer>> graph, int st, int[] color) {
        Queue<Integer> q = new LinkedList<>();
        int hu = 0, ro = 0;

        q.offer(st);
        color[st] = 1;

        while(!q.isEmpty()){
            int curr = q.poll();
            if(color[curr]==1) hu++;
            else ro++;
            for(int node : graph.get(curr)){
                if(color[node]==0){
                    color[node] = -color[curr];
                    q.offer(node);
                }
            }
        }
        return new int[]{hu, ro};

    }
}

// Ai diye DFS:
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoboHu {
    private static int[] color;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = input[0], m = input[1];

        graph = new ArrayList<>();
        color = new int[n + 1]; // 0: unvisited, 1: color1, -1: color2

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int[] tackle = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            graph.get(tackle[0]).add(tackle[1]);
            graph.get(tackle[1]).add(tackle[0]);
        }

        int maxPartitionSize = 0;

        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {
                int[] partitionSizes = new int[2]; // [count of color1, count of color2]
                dfs(i, 1, partitionSizes);
                maxPartitionSize += Math.max(partitionSizes[0], partitionSizes[1]);
            }
        }

        System.out.println(maxPartitionSize);
    }

    private static void dfs(int node, int nodeColor, int[] partitionSizes) {
        color[node] = nodeColor;

        if (nodeColor == 1) {
            partitionSizes[0]++;
        } else {
            partitionSizes[1]++;
        }

        for (int neighbor : graph.get(node)) {
            if (color[neighbor] == 0) {
                dfs(neighbor, -nodeColor, partitionSizes);
            }
        }
    }
}*/

// Ai diye BFS:
/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RobotsVsHumans {
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

        for (int i = 0; i < m; i++) {
            int[] tackle = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int u = tackle[0], v = tackle[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[] color = new int[n + 1]; // 0: unvisited, 1: color1, -1: color2
        int maxPartitionSize = 0;

        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {
                int[] partitionSizes = bfs(graph, i, color);
                maxPartitionSize += Math.max(partitionSizes[0], partitionSizes[1]);
            }
        }

        System.out.println(maxPartitionSize);
    }

    private static int[] bfs(List<List<Integer>> graph, int start, int[] color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        color[start] = 1;

        int count1 = 0, count2 = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (color[node] == 1) count1++;
            else count2++;

            for (int neighbor : graph.get(node)) {
                if (color[neighbor] == 0) {
                    color[neighbor] = -color[node];
                    queue.offer(neighbor);
                }
            }
        }

        return new int[]{count1, count2};
    }
}*/
