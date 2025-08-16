import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
public class EulerianPathFinder{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = inputs[0], m = inputs[1];
        int[] u = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] degree = new int[n + 1];
        if(m== 0) {
            System.out.println("YES");
            return;
        }
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        Set<Integer> visitedVertex = new HashSet<>();
        for (int i = 0; i < m; i++) {
            int st = u[i];
            int end = v[i];
            degree[st]++;
            degree[end]++;
            adjList.get(st).add(end);
            adjList.get(end).add(st);
            visitedVertex.add(st);
            visitedVertex.add(end);
        }
        int stVertex = visitedVertex.iterator().next();
        boolean[] visited = new boolean[n + 1];
        dfsTraversal(adjList, visited, stVertex);
        for(int trav : visitedVertex){
            if(!visited[trav]){
                System.out.println("NO");
                return;
            }
        }
        int oddCount = 0;
        for (int i = 1; i <= n; i++) {
            if(degree[i]%2 == 1){
                oddCount++;
            }
        }
        if(oddCount==0||oddCount==2){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
    static void dfsTraversal(List<List<Integer>> adjlist, boolean[] visited, int vertex) {
        visited[vertex] = true;
        for(int neighbour: adjlist.get(vertex)) {
            if(!visited[neighbour]){
                dfsTraversal(adjlist, visited, neighbour);
            }
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

        // Handle edge case: no edges
        if (m == 0) {
            System.out.println("YES");
            return;
        }

        // Count degree of each vertex
        int[] degree = new int[n + 1];

        // Build adjacency list for connectivity check
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // Read edges and build graph
        String[] uLine = br.readLine().split(" ");
        String[] vLine = br.readLine().split(" ");

        // Process edges
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

        // Check connectivity using DFS
        boolean[] visited = new boolean[n + 1];
        int startVertex = verticesWithEdges.iterator().next();
        dfs(graph, visited, startVertex);

        // Check if all vertices with edges are connected
        for (int vertex : verticesWithEdges) {
            if (!visited[vertex]) {
                System.out.println("NO");
                return;
            }
        }

        // Count vertices with odd degree
        int oddDegreeCount = 0;
        for (int i = 1; i <= n; i++) {
            if (degree[i] % 2 == 1) {
                oddDegreeCount++;
            }
        }

        // Eulerian Path exists if odd degree vertices are 0 or 2
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
/*
import java.io.*;
import java.util.Arrays;
public class EulerianPathFinder {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = inputs[0], m = inputs[1];
        int[] u = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] degree = new int[n + 1];
        for (int i = 0; i < m; i++) {
            degree[u[i]]++;
            degree[v[i]]++;
        }
        int odNum = 0;
        for (int i = 0; i < degree.length; i++) {
            if(degree[i] % 2 != 0){
                odNum++;
            }
        }
        if(odNum == 0 || odNum==2){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}


*/
/*import java.io.*;
import java.util.Arrays;

public class EulerianPathFinder {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Read first line
        int[] inputs = Arrays.stream(br.readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        if (inputs.length < 2) {
            System.out.println("NO"); // Invalid input
            return;
        }
        int n = inputs[0], m = inputs[1];

        // Read u array
        int[] u = Arrays.stream(br.readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        if (u.length != m) {
            System.out.println("NO"); // Mismatch with m
            return;
        }

        // Read v array
        int[] v = Arrays.stream(br.readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        if (v.length != m) {
            System.out.println("NO"); // Mismatch with m
            return;
        }

        int[] degree = new int[n + 1]; // 1-indexed

        // Count degrees safely
        for (int i = 0; i < m; i++) {
            if (u[i] < 1 || u[i] > n || v[i] < 1 || v[i] > n) {
                System.out.println("NO"); // Invalid vertex number
                return;
            }
            degree[u[i]]++;
            degree[v[i]]++;
        }

        // Count odd-degree vertices
        int oddNum = 0;
        for (int i = 1; i <= n; i++) { // skip index 0
            if (degree[i] % 2 != 0) {
                oddNum++;
            }
        }

        // Eulerian Path condition
        if (oddNum == 0 || oddNum == 2) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}*/

