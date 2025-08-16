import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
public class AncientSortingAlgorithm {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] strings = br.readLine().trim().split("\\s+");
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(strings[i]);
        }
        int [] sorted = Arrays.copyOf(num, n);
        sort(sorted);
        sorter(num, sorted);
    }
    public static String checker(int n){
        if(n%2==0){
            return "EVEN";
        } else {
            return "ODD";
        }
    }
    public static void swapper(int[] arr, int i){
        int temp = arr[i];
        arr[i] = arr[i+1];
        arr[i+1] = temp;
    }
    public static void sort(int[] arr){
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]> max) max = arr[i];
        }
        int[] count = new int[max+1];
        for(int i : arr){
            count[i]++;
        }
        int idx = 0;
        for (int i = 0; i <= max; i++) {
            while(count[i]-- > 0){
                arr[idx++] = i;
            }
        }
    }
    public static void sorter(int[] arr, int[] sorted) {
        int n = arr.length;
        int maxTries = n + n;
        int tries = 0;

        while (tries < maxTries) {
            boolean changed = false;
            int i = 0;

            while (i <= n-2) {
                if (arr[i] > arr[i+1] && checker(arr[i]).equals(checker(arr[i+1]))) {
                    swapper(arr, i);
                    changed = true;
                }
                i++;
            }

            if (!changed) break;
            tries++;
        }

        // print final array
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}
