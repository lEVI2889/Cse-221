/*
import java.io.*;
import java.util.*;

public class WhereToMeet {
    static class Edge {
        int to, weight;
        Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static class State implements Comparable<State> {
        int node;
        long dist;
        State(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }
        public int compareTo(State other) {
            return Long.compare(this.dist, other.dist);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
        }

        // Run Dijkstra from Alice's starting point (s)
        long[] distFromS = dijkstra(graph, s, n);

        // Run Dijkstra from Bob's starting point (t)
        long[] distFromT = dijkstra(graph, t, n);

        long minTime = Long.MAX_VALUE;
        int meetingNode = -1;

        // Find the optimal meeting point
        for (int i = 1; i <= n; i++) {
            if (distFromS[i] != Long.MAX_VALUE && distFromT[i] != Long.MAX_VALUE) {
                long meetTime = Math.max(distFromS[i], distFromT[i]);
                if (meetTime < minTime || (meetTime == minTime && i < meetingNode)) {
                    minTime = meetTime;
                    meetingNode = i;
                }
            }
        }

        if (meetingNode == -1) {
            System.out.println(-1);
        } else {
            System.out.println(minTime + " " + meetingNode);
        }
    }

    static long[] dijkstra(List<Edge>[] graph, int source, int n) {
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<State> pq = new PriorityQueue<>();
        dist[source] = 0;
        pq.offer(new State(source, 0));

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            int node = curr.node;
            long d = curr.dist;

            if (d > dist[node]) continue;

            for (Edge edge : graph[node]) {
                long newDist = dist[node] + edge.weight;
                if (newDist < dist[edge.to]) {
                    dist[edge.to] = newDist;
                    pq.offer(new State(edge.to, newDist));
                }
            }
        }

        return dist;
    }
}*/


import java.io.*;
import java.util.*;

public class WhereToMeet {
    static class Edge {
        int to, weight;
        Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static class State implements Comparable<State> {
        int node;
        long dist;
        State(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }
        public int compareTo(State other) {
            return Long.compare(this.dist, other.dist);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
        }

        long[] distFromS = dijkstra(graph, s, n);
        long[] distFromD = dijkstra(graph, t, n);

        long mintime = Long.MAX_VALUE;
        int meet = -1;

        for (int i = 1; i <=n ; i++) {
            if(distFromS[i]!=Long.MAX_VALUE && distFromD[i]!=Long.MAX_VALUE){
                long meet_time = Math.max(distFromS[i], distFromD[i]);
                if(meet_time<mintime || (meet_time==mintime && i< meet)){
                    mintime = meet_time;
                    meet = i;
                }
            }
        }
        if(meet == -1) System.out.println(-1);
        else System.out.println(mintime + " " + meet);

    }

    static long[] dijkstra(List<Edge>[] graph, int start, int size){
        long[] distance = new long[size + 1];
        int[] parents = new int[size + 1];
        Arrays.fill(distance, Long.MAX_VALUE);
        //Arrays.fill(parents, -1);

        PriorityQueue<State> pq = new PriorityQueue<>();
        distance[start] = 0;



        pq.offer(new State(start, 0));
        while(!pq.isEmpty()) {
            State curr = pq.poll();
            int node = curr.node;
            long dist = curr.dist;

            if (dist > distance[node]) continue;

            for (Edge edge : graph[node]) {
                int nod = edge.to;
                long nw_dist = distance[node] + edge.weight;

                if (nw_dist < distance[nod]) {
                    distance[nod] = nw_dist;
                    //parents[nod] = node;
                    pq.offer(new State(nod, nw_dist));
                }
            }
        }
        return distance;
    }
}
