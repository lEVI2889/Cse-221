import java.io.*;
import java.util.Arrays;
public class Count_The_Numbers {
    public static void main(String[] args) throws IOException {
        BufferedReader task = new BufferedReader(new InputStreamReader(System.in));
        int [] inputs = Arrays.stream(task.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int size = inputs[0], no_ofQueries = inputs[1];
        int [] array = Arrays.stream(task.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int i = 0; i<no_ofQueries; i++) {
            int[] range = Arrays.stream(task.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            int low = lowerBound(array, range[0]);
            int high = upperBound(array, range[1]);
            System.out.println(high - low);
        }
    }
    static int lowerBound(int[] arr, int target){
        int high = arr.length, low = 0;
        while (low < high) {
            int mid = (high+low)/2;
            if(target>arr[mid]) low = mid+1;
            else high = mid;
        }
        return low;
    }
    static int upperBound(int [] arr, int target){
        int low = 0, high = arr.length;
        while(low<high){
            int mid = (high+low)/2;
            if(target>=arr[mid]) low = mid + 1;
            else high = mid;
        }
        return low;
    }
}
