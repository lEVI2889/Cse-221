import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
public class Knights {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int [] inputs = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int row = inputs[0], col = inputs[1], knights = inputs[2];
        Set<List<Integer>> positions = new HashSet<>();
        for (int i = 0; i < knights; i++) {
            int[] position = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            positions.add(Arrays.asList(position[0],position[1]));
        }
        System.out.println(possible_positions(positions, row, col)? "YES" : "NO");
    }
    static boolean possible_positions(Set<List<Integer>> positions, int row, int col) {
        int[][] directions = {
                {-2, -1}, {-2, 1},
                {-1, -2}, {-1, 2},
                {1, -2}, {1, 2},
                {2, -1}, {2, 1}
        };
        for(List<Integer> possibles : positions){
            int r = possibles.get(0), c = possibles.get(1);
            for(int[] direction : directions){
                int nRow = r + direction[0], nCol = c + direction[1];
                if(nRow >= 1 && nRow <= row && nCol >= 1 && nCol <= col ){
                    if(positions.contains(Arrays.asList(nRow, nCol))){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}










/*
import java.io.*;
import java.util.*;
public class Knights {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int [] inputs = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int row = inputs[0], col = inputs[1], knights = inputs[2];
        List<int[]> positions = new ArrayList<>();
        for (int i = 0; i < knights; i++) {
            int[] position = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            positions.add(position);
        }
        boolean is_possible = possible_positions(positions, row, col);
    }
    static boolean possible_positions(List<int[]> positions, int row, int col) {
        //List<int[]> possible_moves = new ArrayList<>();
        int[][] directions = {
                {-2, -1}, {-2, 1},
                {-1, -2}, {-1, 2},
                {1, -2}, {1, 2},
                {2, -1}, {2, 1}
        };
        for(int[] possibles : positions){
            int r = possibles[0], c = possibles[1];
            for(int[] direction : directions){
                int nRow = r + direction[0], nCol = c + direction[1];
                int[] target = {nRow, nCol};
                boolean exists = positions.stream().anyMatch(arr -> Arrays.equals(arr, target));
                if(nRow >= 0 && nRow < row && nCol >= 0 && nCol < col && exists){
                    System.out.println("YES");
                    return true;
                }
            }
        }
        System.out.println("NO");
        return false;
    }
}
*/
