import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Knights {
    static int[][] directions = {
            {-2, -1}, {-2, 1},
            {-1, -2}, {-1, 2},
            {1, -2}, {1, 2},
            {2, -1}, {2, 1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        //int [][] board = new int[size][size];
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int x1 = input[0] - 1, y1 = input[1] - 1;
        int x2 = input[2] - 1, y2 = input[3] - 1;

        if(x1 == x2 && y1 == y2) {
            System.out.println(0);
            return;
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[size][size];

        q.offer(new int[]{x1, y1, 0});
        visited[x1][y1] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], steps = cur[2];
            for(int[] dir : directions) {
                int nwx = x + dir[0];
                int nwy = y + dir[1];

                if(valid_move(nwx, nwy, size) && !visited[nwx][nwy]) {

                    if(nwx == x2 && nwy == y2){
                        System.out.println(steps + 1);
                        return;
                    }
                    visited[nwx][nwy] = true;
                    q.offer(new int[]{nwx, nwy, steps + 1});
                }

            }
        }
        System.out.println(-1);
    }

    static boolean valid_move(int x, int y, int size) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }
}
