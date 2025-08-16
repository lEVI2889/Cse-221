
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class TwoSumTrouble {
    public static void main(String[] args) throws IOException {
        BufferedReader task = new BufferedReader(new InputStreamReader(System.in));
        String[] LenNSum = task.readLine().trim().split("\\s+");
        int len = Integer.parseInt(LenNSum[0]);
        int sum = Integer.parseInt(LenNSum[1]);

        String [] array = task.readLine().trim().split("\\s+");
        int [] intarray = new int[array.length];
        for (int i = 0; i < intarray.length; i++) {
            intarray[i] = Integer.parseInt(array[i]);
        }
        int leftIdx= 0, rightIdx = intarray.length - 1;
        boolean found = false;
        while(leftIdx<rightIdx){
            int temp = intarray[leftIdx] + intarray[rightIdx];
            if(temp == sum){
                found = true;
                System.out.println((leftIdx + 1) + " " + (rightIdx + 1));
                return;
            } else if(temp < sum){
                leftIdx++;
            } else {
                rightIdx--;
            }
        }
        if(!found) System.out.println(-1);

    }
}
