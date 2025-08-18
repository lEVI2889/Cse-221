import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Diamonds {
    static char[][] grid;
    static int[][] memo;
    static boolean[][] visited;
    static int R, H;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().trim().split(" ");
        R = Integer.parseInt(inputs[0]);
        H = Integer.parseInt(inputs[1]);

        grid = new char[R][H];
        memo = new int[R][H];
        visited = new boolean[R][H];

        for (int i = 0; i < R; i++) {
            grid[i] = br.readLine().trim().toCharArray();
        }

        int maxDiamonds = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < H; j++) {
                if (grid[i][j] != '#' && !visited[i][j]) {
                    maxDiamonds = Math.max(maxDiamonds, dfs(i, j));
                }
            }
        }

        System.out.println(maxDiamonds);
    }

    static int dfs(int x, int y) {
        if (x < 0 || x >= R || y < 0 || y >= H || grid[x][y] == '#' || visited[x][y]) {
            return 0;
        }

        if (memo[x][y] != 0) {
            return memo[x][y];
        }

        visited[x][y] = true;
        int diamonds = (grid[x][y] == 'D') ? 1 : 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            diamonds += dfs(nx, ny);
        }

        memo[x][y] = diamonds;
        return diamonds;
    }
}