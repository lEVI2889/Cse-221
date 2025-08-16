import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Diamonds {

    static char[][] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int R = inputs[0], H = inputs[1];

        grid = new char[R][H];
        for (int i = 0; i < R; i++) {
            grid[i] = br.readLine().trim().toCharArray();
        }
    }
}