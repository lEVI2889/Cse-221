import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EasyTree {
    static List<List<Integer>> tree;
    static int[] size;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int num = inputs[0], root = inputs[1];
        tree = new ArrayList<>();
        for (int i = 0; i <= num; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < num-1; i++) {
            int[] edges = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            int st = edges[0], en = edges[1];
            tree.get(st).add(en);
            tree.get(en).add(st);
        }
        size = new int[num+1];
        visited = new boolean[num+1];
        dfs(root);

        int queries = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < queries; i++) {
            int q = Integer.parseInt(br.readLine().trim());
            System.out.println(size[q]);
        }
    }
    static int dfs(int root) {
        size[root] = 1;
        visited[root] = true;
        for(int node: tree.get(root)) {
            if(!visited[node]) {
                //visited[node] = true;
                size[root] += dfs(node);
            }
        }
        return size[root];
    }
}
