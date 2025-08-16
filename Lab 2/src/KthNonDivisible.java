//AI LIKHSE
import java.io.*;
import java.util.*;

public class KthNonDivisible {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(input.readLine());

        StringBuilder output = new StringBuilder();

        while (T-- > 0) {
            String[] tokens = input.readLine().split(" ");
            long k = Long.parseLong(tokens[0]);
            long x = Long.parseLong(tokens[1]);

            // Binary search to find smallest n such that n - n/x >= k
            long low = 1, high = 2 * k; // 2*k is a safe upper bound
            while (low < high) {
                long mid = (low + high) / 2;
                if (mid - mid / x < k) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }

            output.append(low).append("\n");
        }

        System.out.print(output);
    }
}
