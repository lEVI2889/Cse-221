import java.util.*;
import java.io.*;

public class A_Shortest_Path {
    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class State implements Comparable<State> {
        int node, dist;
        State(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        public int compareTo(State other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int[] u = new int[M];
        for (int i = 0; i < M; i++) {
            u[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] v = new int[M];
        for (int i = 0; i < M; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] w = new int[M];
        for (int i = 0; i < M; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            graph[u[i]].add(new Edge(v[i], w[i]));
        }

        long[] dist = new long[N + 1];
        int[] parent = new int[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        Arrays.fill(parent, -1);

        PriorityQueue<State> pq = new PriorityQueue<>();
        dist[S] = 0;
        pq.offer(new State(S, 0));

        while (!pq.isEmpty()) {
            State current = pq.poll();
            int u_node = current.node;
            long u_dist = current.dist;

            if (u_dist > dist[u_node]) continue;

            for (Edge edge : graph[u_node]) {
                int v_node = edge.to;
                long new_dist = dist[u_node] + edge.weight;

                if (new_dist < dist[v_node]) {
                    dist[v_node] = new_dist;
                    parent[v_node] = u_node;
                    pq.offer(new State(v_node, (int)new_dist));
                }
            }
        }

        if (dist[D] == Long.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dist[D]);

            List<Integer> path = new ArrayList<>();
            int current = D;
            while (current != -1) {
                path.add(current);
                current = parent[current];
            }
            Collections.reverse(path);

            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i));
                if (i < path.size() - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
}