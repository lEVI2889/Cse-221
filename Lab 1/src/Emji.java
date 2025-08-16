import java.util.Arrays;

public class Emji {
    public static void main(String[] args) {
        int[] arr = {1, 10, 4 , 3, 2 , 9};
        int [] sorted = {1, 2, 3, 4, 9, 10};
        countingSort(arr);
        //Arrays.sort(arr);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }
    static void countingSort(int[] arr){
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>max) max = arr[i];
        }
        int [] count = new int[max+1];
        for (int i : arr) {
            count[i]++;
        }
        int idx = 0;
        for (int i = 0; i <= max ; i++) {
            while(count[i]-- >0){
                arr[idx++] = i;
            }
        }
    }
    boolean isSortable(int[] arr, int[] sorted){
        int n = arr.length;
        int maxTries = n+n;
        int tries = 0;
        while(tries<maxTries){
            int i = 0;
            boolean found = false;
            while(i<=n-3){
                if(arr[i]>arr[i+1]||arr[i+1]>arr[i+2]){
                    //reverseWindow(nums, i);
                    found = true;
                }
                i++;
            }
            if(Arrays.equals(arr, sorted)) return true;
            if(!found) break;
            tries++;
        }
        return Arrays.equals(arr, sorted);
    }
}
