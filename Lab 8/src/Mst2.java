import java.util.*;
import java.io.*;

public class Mst2 {
    static class Edge implements Comparable<Edge> {
        int u, v, weight;
        int id;

        Edge(int u, int v, int weight, int id) {
            this.u = u;
            this.v = v;
            this.weight = weight;
            this.id = id;
        }

        @Override
        public int compareTo(Edge other) {
            if (this.weight != other.weight) {
                return Integer.compare(this.weight, other.weight);
            }
            return Integer.compare(this.id, other.id); // For stability
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

    static Result kruskal(List<Edge> edges, int n, Set<Integer> forbiddenEdges) {
        List<Edge> sortedEdges = new ArrayList<>(edges);
        Collections.sort(sortedEdges);

        UnionFind uf = new UnionFind(n);
        long cost = 0;
        int edgesUsed = 0;
        Set<Integer> usedEdges = new HashSet<>();

        for (Edge edge : sortedEdges) {
            if (forbiddenEdges.contains(edge.id)) continue;

            if (uf.union(edge.u, edge.v)) {
                cost += edge.weight;
                edgesUsed++;
                usedEdges.add(edge.id);
                if (edgesUsed == n - 1) break;
            }
        }

        return new Result(edgesUsed == n - 1 ? cost : -1, usedEdges);
    }

    static class Result {
        long cost;
        Set<Integer> usedEdges;

        Result(long cost, Set<Integer> usedEdges) {
            this.cost = cost;
            this.usedEdges = usedEdges;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String[] uvw = br.readLine().split(" ");
            int u = Integer.parseInt(uvw[0]);
            int v = Integer.parseInt(uvw[1]);
            int w = Integer.parseInt(uvw[2]);
            edges.add(new Edge(u, v, w, i));
        }

        // Find original MST
        Result originalResult = kruskal(edges, n, new HashSet<>());

        if (originalResult.cost == -1) {
            System.out.println(-1);
            return;
        }

        long originalMST = originalResult.cost;
        long secondBest = Long.MAX_VALUE;
        boolean found = false;

        // Method 1: Try removing each edge used in original MST
        for (int edgeId : originalResult.usedEdges) {
            Set<Integer> forbidden = new HashSet<>();
            forbidden.add(edgeId);
            Result result = kruskal(edges, n, forbidden);

            if (result.cost != -1 && result.cost > originalMST) {
                secondBest = Math.min(secondBest, result.cost);
                found = true;
            }
        }

        // Method 2: Try forcing each edge not in original MST
        Set<Integer> notInMST = new HashSet<>();
        for (int i = 0; i < m; i++) {
            if (!originalResult.usedEdges.contains(i)) {
                notInMST.add(i);
            }
        }

        for (int edgeId : notInMST) {
            // Force this edge to be included
            Edge forcedEdge = edges.get(edgeId);

            // Create MST with this edge forced
            UnionFind uf = new UnionFind(n);
            uf.union(forcedEdge.u, forcedEdge.v);

            long cost = forcedEdge.weight;
            int edgesUsed = 1;

            List<Edge> remainingEdges = new ArrayList<>();
            for (int i = 0; i < edges.size(); i++) {
                if (i != edgeId) {
                    remainingEdges.add(edges.get(i));
                }
            }

            Collections.sort(remainingEdges);

            for (Edge edge : remainingEdges) {
                if (uf.union(edge.u, edge.v)) {
                    cost += edge.weight;
                    edgesUsed++;
                    if (edgesUsed == n - 1) break;
                }
            }

            if (edgesUsed == n - 1 && cost > originalMST) {
                secondBest = Math.min(secondBest, cost);
                found = true;
            }
        }

        System.out.println(found ? secondBest : -1);
        br.close();
    }
}