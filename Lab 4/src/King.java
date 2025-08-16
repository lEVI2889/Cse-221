import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class King {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[] position = Arrays.stream(br.readLine().trim().split( " ")).mapToInt(Integer::parseInt).toArray();
        int count = 0;
        List<int[]> chess =possible_positions(position, count, size);
        System.out.println(chess.size());
        for(int[] pos : chess) {
            System.out.println(pos[0] + " " + pos[1]);
        }
    }
    static List<int[]> possible_positions(int[] position, int count, int size) {
        List<int[]> possible_moves = new ArrayList<>();
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1}, {1, 0}, {1, 1}
        };
        int row = position[0];
        int col = position[1];
        for(int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if(newRow>=1 && newRow<=size && newCol>=1 && newCol<=size){
                possible_moves.add(new int[]{newRow, newCol});
                count++;
            }
        }
        return possible_moves;
    }
}