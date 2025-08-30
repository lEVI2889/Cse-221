import java.io.*;
import java.util.*;

public class ParityEdges {
    static class Edge{
        int to, weight;
        Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static class State implements Comparable<State>{
        int node, cost, lastParity;
        State(int node, int cost, int lastParity){
            this.node = node;
            this.cost = cost;
            this.lastParity = lastParity;
        }
        public int compareTo(State other){
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new List[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int[] u = new int[m];
        for (int i = 0; i < m; i++) {
            u[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] v = new int[m];
        for (int i = 0; i < m; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] w = new int[m];
        for (int i = 0; i < m; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            graph[u[i]].add(new Edge(v[i], w[i]));
        }

        int[][] dist = new int[n+1][2];
        for (int i = 0; i <= n ; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.offer(new State(1, 0,  -1));
        dist[1][0]=dist[1][1]=0;

        while(!pq.isEmpty()){
            State curr = pq.poll();
            int node = curr.node;
            int cost = curr.cost;
            int lastParity = curr.lastParity;

            if(lastParity!=-1 && cost>dist[node][lastParity])continue;

            for(Edge edge : graph[node]){
                int to = edge.to;
                int nwcost = cost + edge.weight;
                int parity = edge.weight % 2;

                if(lastParity!=-1&&lastParity==parity) continue;

                if(nwcost<dist[to][parity]){
                    dist[to][parity]=nwcost;
                    pq.offer(new State(to, nwcost, parity));
                }
            }
        }

        int res = Math.min(dist[n][0], dist[n][1]);

        if(res==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(res);
    }
}






/*
import java.io.*;
import java.util.*;

public class ParityEdges {
    static class Edge{
        int to, weight;
        Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static class State implements Comparable<State>{
        int node, cost;
        State(int node, int cost){
            this.node = node;
            this.cost = cost;
        }
        public int compareTo(State other){
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new List[n+1];
        for (int i = 0; i <=n ; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int fr = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[fr].add(new Edge(to, w));
        }

        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<State> pq = new PriorityQueue<>();
        dist[1] = 0;
        pq.offer(new State(1,0));
        while(!pq.isEmpty()){
            State curr = pq.poll();
            int node = curr.node;
            int cost = curr.cost;

            if(cost>dist[node]) continue;

            for(Edge edge : graph[node]){
                int to = edge.to;
                int nw = edge.weight + dist[to];

                if(nw<dist[node]){
                    dist[node] = nw;
                    pq.offer(new State(node, nw));
                }
            }
        }

        if(dist[n]==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(dist[n]);
    }
}
*/
