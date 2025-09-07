import java.util.*;
import java.io.*;

public class King {

    static class Edge implements Comparable<Edge> {
        int u, v, weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    static class UnionFind {
        int[] parent, size;

        UnionFind(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return false;

            if (size[rootX] < size[rootY]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nm[0];
        int m = nm[1];

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int[] uvw = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            edges.add(new Edge(uvw[0], uvw[1], uvw[2]));
        }


        Collections.sort(edges);

        UnionFind uf = new UnionFind(n);
        long totalCost = 0;
        int edgesUsed = 0;

        for (Edge edge : edges) {
            if (uf.union(edge.u, edge.v)) {
                totalCost += edge.weight;
                edgesUsed++;
                if (edgesUsed == n - 1) break;
            }
        }

        System.out.println(totalCost);
        br.close();
    }
}