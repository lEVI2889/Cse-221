import java.io.*;
import java.util.*;

public class BeautifulPath {
    static class State implements Comparable<State>{
        int node;
        long cost;
        State(int node, long dist){
            this.node = node;
            this.cost = dist;
        }
        public int compareTo(State other){
            return Long.compare(this.cost, other.cost);
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[] weight = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <=n ; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph[f].add(t);
        }

        long[] costs = new long[n+1];
        Arrays.fill(costs, Long.MAX_VALUE);
        PriorityQueue<State> pq = new PriorityQueue<>();
        costs[s] = weight[s];
        pq.offer(new State(s, weight[s]));
        while(!pq.isEmpty()){
            State curr = pq.poll();
            int node = curr.node;
            long cost = curr.cost;

            if(cost>costs[node]) continue;

            for(int neighbor : graph[node]){
                long newCost = costs[node] + weight[neighbor];
                if(newCost<costs[neighbor]){
                    costs[neighbor] = newCost;
                    pq.offer(new State(neighbor, newCost));
                }
            }
        }
        if(costs[d]==Long.MAX_VALUE) System.out.println(-1);
        else System.out.println(costs[d]);
    }
}
