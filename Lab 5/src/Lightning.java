import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Lightning {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().trim().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int n = inputs[0], m = inputs[1], s = inputs[2], d = inputs[3];
        int[] end1 = m > 0 ? Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray() : new int[0];
        int[] end2 = m > 0 ? Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray() : new int[0];
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = end1[i], v = end2[i];
            g.get(u).add(v);
            g.get(v).add(u);
        }
        for (int i = 1; i <= n; i++) {
            Collections.sort(g.get(i));
        }
        /*// Replace Collections.sort() with manual insertion sort
        for (int i = 1; i <= n; i++) {
            List<Integer> adj = graph.get(i);
            for (int j = 1; j < adj.size(); j++) {
                int key = adj.get(j);
                int k = j - 1;
                while (k >= 0 && adj.get(k) > key) {
                    adj.set(k + 1, adj.get(k));
                    k--;
                }
                adj.set(k + 1, key);
            }
        }*/
        bfs(g, s, d, n);
    }

    static void bfs(List<List<Integer>> graph, int start, int end, int size){
        Queue<Integer> queue = new LinkedList<>();
        //ekhane path storing list pore likhbo
        int[] distance_fromStart = new int[size + 1];
        int[] parent_defined = new int[size + 1];
        Arrays.fill(distance_fromStart, -1);

        queue.add(start);
        distance_fromStart[start] = 0;
        parent_defined[start] = -1; // start node er parent -1 set kora hocche
        //parent_defined[start] = start;
        //queue.add(start);
        while(!queue.isEmpty()){
            int curr = queue.poll();
            for(int neighbor : graph.get(curr)){
                if(distance_fromStart[neighbor] == -1){
                    distance_fromStart[neighbor] = distance_fromStart[curr] + 1;
                    parent_defined[neighbor] = curr;
                    queue.add(neighbor);
                }
            }
        }
        if(distance_fromStart[end]==-1) System.out.println(-1);
        else{
            System.out.println(distance_fromStart[end]);
            List<Integer> path = new ArrayList<>();
            for(int current = end; current != -1; current = parent_defined[current]) path.add(current);
            Collections.reverse(path);
            for(int node : path){
                System.out.print(node + " ");
            }
        }
    }
}
