
import java.util.Arrays;
import java.util.ArrayList;
import java.io.*;
public class GraphMetamorphosis {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ver = Integer.parseInt(br.readLine().trim());
        int temp = ver;
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < ver; i++) {
            adjList.add(new ArrayList<>());
        }

        int vertexIndex = 0;
        while(temp-- > 0) {
            int[] vals = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            adding(vals, adjList.get(vertexIndex));
            vertexIndex++;
        }
        int[][] mat = new int[ver][ver];


        for (int i = 0; i < ver; i++) {
            for (int neighbor : adjList.get(i)) {
                mat[i][neighbor] = 1;
            }
        }


        for (int i = 0; i < ver; i++) {
            for (int j = 0; j < ver; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }

    }

    static void adding(int[] vals, ArrayList<Integer> currentVertexList) {
        int count = vals[0];
        for (int i = 1; i <= count; i++) {
            currentVertexList.add(vals[i]);
        }
    }
}
