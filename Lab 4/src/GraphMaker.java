import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GraphMaker {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int vertices = inputs[0];
        int qs = inputs[1];

        // Build the coprime graph using an optimized approach
        List<List<Integer>> graph = buildCoprimeGraph(vertices);

        // Process queries
        StringBuilder result = new StringBuilder();
        for (int q = 0; q < qs; q++) {
            int[] vals = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            int x = vals[0];
            int k = vals[1];

            List<Integer> neighbors = graph.get(x);
            if (k <= neighbors.size()) {
                result.append(neighbors.get(k - 1)).append("\n");
            } else {
                result.append(-1).append("\n");
            }
        }

        System.out.print(result.toString());
    }

    // Optimized graph construction using a sieve-like approach
    private static List<List<Integer>> buildCoprimeGraph(int n) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (gcd(i, j) == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        // Sort neighbors for each node to handle queries efficiently
        for (int i = 1; i <= n; i++) {
            Collections.sort(graph.get(i));
        }

        return graph;
    }

    // Helper method to calculate GCD
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}