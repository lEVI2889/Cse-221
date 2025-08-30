import java.util.*;
import java.io.*;

public class MinimizeTheDanger {
    static class Edge{
        int to, danger;
        Edge(int to, int danger){
            this.to = to;
            this.danger = danger;
        }
    }
    static class State implements Comparable<State>{
        int node, maxDanger;
        State(int node, int max){
            this.node = node;
            this.maxDanger = max;
        }
        public int compareTo(State other){
            return Integer.compare(this.maxDanger,other.maxDanger);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[f].add(new Edge(t, d));
            graph[t].add(new Edge(f, d));
        }

        int[] minDanger = new int[n+1];
        Arrays.fill(minDanger, Integer.MAX_VALUE);

        PriorityQueue<State> pq = new PriorityQueue<>();
        minDanger[1] = 0;
        pq.offer(new State(1, 0));

        while(!pq.isEmpty()){
            State curr = pq.poll();
            int node = curr.node;
            int max = curr.maxDanger;

            if(max > minDanger[node]) continue;

            for(Edge edge : graph[node]){
                int des = edge.to;
                int w = Math.max(edge.danger, minDanger[node]);

                if(w< minDanger[des]){
                    minDanger[des] = w;
                    pq.offer(new State(des, w));
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            if (minDanger[i] == Integer.MAX_VALUE) {
                System.out.print(-1);
            } else {
                System.out.print(minDanger[i]);
            }
            if (i < n) System.out.print(" ");
        }
        System.out.println();
    }
}
