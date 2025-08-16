import java.io.*;
import java.util.*;

public class ThreeSumFinder {
    public static void main(String[] args) throws IOException {
        BufferedReader task = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(task.readLine().trim());

        while (t-- > 0) {
            String[] parts = task.readLine().trim().split(" ");
            int n = Integer.parseInt(parts[0]);
            int s = Integer.parseInt(parts[1]);

            int[] arr = Arrays.stream(task.readLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            boolean found = false;

            for (int i = 0; i < n - 2 && !found; i++) {
                Map<Integer, Integer> map = new HashMap<>();
                for (int j = i + 1; j < n && !found; j++) {
                    int complement = s - arr[i] - arr[j];
                    if (map.containsKey(complement)) {
                        int k = map.get(complement);
                        // Output positions in 1-based indexing
                        out.write((k + 1) + " " + (i + 1) + " " + (j + 1));
                        out.newLine();
                        found = true;
                    }
                    map.put(arr[j], j);
                }
            }

            if (!found) {
                out.write("-1\n");
            }
        }
        out.flush();
    }
}
