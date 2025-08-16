import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Pb2_AdjacencyList {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int ver = nums[0], edg = nums[1];

        ArrayList<ArrayList<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i <= ver ; i++) {
            adjList.add( new ArrayList<>() );
        }
        int [] u = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int [] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int [] w = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int i = 0; i < edg; i++) {
            adjList.get(u[i]).add(new int[]{v[i], w[i]});
        }
        for (int i = 1; i <= ver; i++) {
            System.out.print(i + ":");
            for (int[] edge : adjList.get(i)) {
                System.out.print(" (" + edge[0] + "," + edge[1] + ")");
            }
            System.out.println();
        }
    }
}


/*import java.io.*;
import java.util.*;

public class EulerianPathFinder {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);


        if (m == 0) {
            System.out.println("YES");
            return;
        }


        int[] degree = new int[n + 1];


        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        String[] uLine = br.readLine().split(" ");
        String[] vLine = br.readLine().split(" ");


        Set<Integer> verticesWithEdges = new HashSet<>();
        for (int i = 0; i < m; i++) {
            int from = Integer.parseInt(uLine[i]);
            int to = Integer.parseInt(vLine[i]);

            degree[from]++;
            degree[to]++;

            graph.get(from).add(to);
            graph.get(to).add(from);

            verticesWithEdges.add(from);
            verticesWithEdges.add(to);
        }


        boolean[] visited = new boolean[n + 1];
        int startVertex = verticesWithEdges.iterator().next();
        dfs(graph, visited, startVertex);


        for (int vertex : verticesWithEdges) {
            if (!visited[vertex]) {
                System.out.println("NO");
                return;
            }
        }


        int oddDegreeCount = 0;
        for (int i = 1; i <= n; i++) {
            if (degree[i] % 2 == 1) {
                oddDegreeCount++;
            }
        }


        if (oddDegreeCount == 0 || oddDegreeCount == 2) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static void dfs(List<List<Integer>> graph, boolean[] visited, int vertex) {
        visited[vertex] = true;
        for (int neighbor : graph.get(vertex)) {
            if (!visited[neighbor]) {
                dfs(graph, visited, neighbor);
            }
        }
    }
}*/