import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CanYouTraverse_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().trim().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int n = inputs[0], m = inputs[1];
        int[] end1 = Arrays.stream(br.readLine().trim().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int[] end2 = Arrays.stream(br.readLine().trim().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int st = end1[i], ed = end2[i];
            graph.get(st).add(ed);
            graph.get(ed).add(st);
        }
        boolean[] visited = new boolean[n+1];
        List<Integer> ans = new ArrayList<>();
        dfsTraversal(graph, visited, 1, ans);
        for(int node : ans){
            System.out.print(node + " ");
        }
    }
    static void dfsTraversal(List<List<Integer>> graph, boolean[] visited, int start, List<Integer> ans) {
        ans.add(start);
        visited[start] = true;
        for(int neighbor : graph.get(start)){
            if(!visited[neighbor]){
                visited[neighbor] = true;
                dfsTraversal(graph, visited, neighbor, ans);
            }
        }

    }
}
