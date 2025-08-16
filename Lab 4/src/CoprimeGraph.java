import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
public class CoprimeGraph {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int [] inputs  = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int vertices = inputs[0];
        int qs = inputs[1];
        List<List<Integer>> graph = graphmaker(vertices); // Change ArrayList to List
        List<int[]> queries = new ArrayList<>(); // Change ArrayList to List
        while(qs-- > 0){
            int[] vals = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            queries.add(new int[]{vals[0], vals[1]});
        }
        for(int[] query : queries){
            int index_tomo = query[0];
            int smallest_tomo = query[1];
            List<Integer> neighbors = graph.get(index_tomo);
            if(smallest_tomo <= neighbors.size()){
                int req = neighbors.get(smallest_tomo - 1); // Accessing the smallest neighbor
                System.out.println(req);
            } else {
                System.out.println(-1); // If the index is out of bounds
            }
        }
    }
    static List<List<Integer>> graphmaker(int n){
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        //1 er case alada handled;
        for (int i = 2; i <= n ; i++) {
            graph.get(1).add(i);
            graph.get(i).add(1);
        }
        for (int i = 2; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                if(gcd(i,j)==1){
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        return graph;
    }
    static int gcd(int a, int b){
        while(b!=0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
