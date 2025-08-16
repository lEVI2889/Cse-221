import java.io.*;
import java.util.Arrays;
public class Pb1_AdjacencyMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int [] nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int ver = nums[0], edg = nums[1];
        int[][] mat = new int[ver][ver];
        while(edg-- > 0){
            int [] vals = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            int st = vals[0] - 1, end = vals[1] - 1 , val = vals[2];
            mat[st][end] = val;
        } 
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ver; i++) {
            for (int j = 0; j < ver; j++) {
                sb.append(mat[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}