/*
import java.io.*;
import java.util.Arrays;
public class EdgeQueries {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0]; // Number of vertices
        int m = input[1];
        int[] in = new int[n+1];
        int[] out = new int[n+1];// Number of edges
        int [] O_edges = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int [] I_edges = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < m; i++) {
            in[I_edges[i]]++;
            out[O_edges[i]]++;
        }
        StringBuilder st = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            st.append(in[i]-out[i]).append(" ");
        }
        System.out.println(st.toString().trim());
    }
}
*/
import java.io.*;
import java.util.*;

public class EdgeQueries {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Read first line: n (vertices) and m (edges)
        String[] firstLine = br.readLine().trim().split("\\s+");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);

        // Arrays for in-degree and out-degree
        int[] in = new int[n + 1];
        int[] out = new int[n + 1];

        // Read outgoing edges
        int[] O_edges = new int[m];
        String outLine = br.readLine();
        if (outLine != null && !outLine.trim().isEmpty()) {
            String[] parts = outLine.trim().split("\\s+");
            for (int i = 0; i < m && i < parts.length; i++) {
                try {
                    O_edges[i] = Integer.parseInt(parts[i]);
                } catch (NumberFormatException e) {
                    O_edges[i] = -1; // invalid marker
                }
            }
        }

        // Read incoming edges
        int[] I_edges = new int[m];
        String inLine = br.readLine();
        if (inLine != null && !inLine.trim().isEmpty()) {
            String[] parts = inLine.trim().split("\\s+");
            for (int i = 0; i < m && i < parts.length; i++) {
                try {
                    I_edges[i] = Integer.parseInt(parts[i]);
                } catch (NumberFormatException e) {
                    I_edges[i] = -1; // invalid marker
                }
            }
        }

        // Count in and out degrees (with bounds check)
        for (int i = 0; i < m; i++) {
            if (I_edges[i] >= 1 && I_edges[i] <= n) {
                in[I_edges[i]]++;
            }
            if (O_edges[i] >= 1 && O_edges[i] <= n) {
                out[O_edges[i]]++;
            }
        }

        // Build output
        StringBuilder st = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            st.append(in[i] - out[i]).append(" ");
        }

        // Print without trailing space
        System.out.println(st.toString().trim());
    }
}


