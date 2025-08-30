import java.io.*;
import java.util.*;

public class SecShort {
    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class State implements Comparable<State> {
        int node, cost;
        State(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
        public int compareTo(State other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new List[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Read bidirectional edges
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }

        // Track first and second shortest distances
        int[] dist1 = new int[n+1];
        int[] dist2 = new int[n+1];
        Arrays.fill(dist1, Integer.MAX_VALUE);
        Arrays.fill(dist2, Integer.MAX_VALUE);

        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.offer(new State(s, 0));
        dist1[s] = 0;

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            int node = curr.node;
            int cost = curr.cost;

            // Skip if this cost is worse than second shortest
            if (cost > dist2[node]) continue;

            for (Edge edge : graph[node]) {
                int to = edge.to;
                int newCost = cost + edge.weight;

                if (newCost < dist1[to]) {
                    // New shortest path found
                    dist2[to] = dist1[to];
                    dist1[to] = newCost;
                    pq.offer(new State(to, newCost));
                } else if (newCost > dist1[to] && newCost < dist2[to]) {
                    // New second shortest path found
                    dist2[to] = newCost;
                    pq.offer(new State(to, newCost));
                }
            }
        }

        if (dist2[d] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dist2[d]);
        }
    }
}