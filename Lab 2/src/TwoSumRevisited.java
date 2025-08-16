import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class TwoSumRevisited {
    public static void main(String[] args) throws IOException {
        BufferedReader task = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = task.readLine().trim().split("\\s+");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);
        int K = Integer.parseInt(inputs[2]);
        String[] Array = task.readLine().trim().split("\\s+");
        Long[] A = new Long[N];
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(Array[i]);
        }
        String[] Barray = task.readLine().trim().split("\\s+");
        Long[] B = new Long[M];
        for (int i = 0; i < M; i++) {
            B[i] = Long.parseLong(Barray[i]);
        }
        int i = 0, j = M - 1;
        int reqI = 0, reqJ = 0;
        Long minDiff = Long.MAX_VALUE;
        while(i<N && j>=0){
            Long tempSum = A[i] + B[j];
            Long difference = Math.abs(tempSum-K);
            if(difference < minDiff){
                minDiff = difference;
                reqI = i;
                reqJ = j;
            }
            if(tempSum<K) {
                i++;
            }
            else if(tempSum>K) {
                j--;
            }
            else {
                System.out.println((i + 1) + " " + (j + 1));
                return;
            }
        }
        System.out.println((reqI + 1) + " " + (reqJ + 1));
    }
}
