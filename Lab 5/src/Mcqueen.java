//AI DIYE COMPLETED

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Mcqueen {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().trim().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int n = inputs[0], m = inputs[1], s = inputs[2], d = inputs[3];
        int[] end1 = m > 0 ? Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray() : new int[0];
        int[] end2 = m > 0 ? Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray() : new int[0];

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int u = end1[i], v = end2[i];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        for (int i = 1; i <= n; i++) Collections.sort(graph.get(i));

        int[] dist = new int[n + 1];
        int[] parent = new int[n + 1];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        dist[s] = 0;
        parent[s] = -1;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph.get(u)) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;// ekhane st theke or edge gular distance + kora hocche
                    parent[v] = u; // parent set kora hocche
                    q.add(v);
                }
            }
        }

        if (dist[d] == -1) {
            System.out.println(-1);// ekhane jodi distance -1 thake tahole mane d node ta reachable na
        } else {
            System.out.println(dist[d]);
            List<Integer> path = new ArrayList<>();
            for (int cur = d; cur != -1; cur = parent[cur]) path.add(cur);// ekhane d theke parent diye backtrack kora hocche
            Collections.reverse(path);
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i));
                if (i < path.size() - 1) System.out.print(" ");// ekhane space print kora hocche
            }
            System.out.println();
        }
    }
}