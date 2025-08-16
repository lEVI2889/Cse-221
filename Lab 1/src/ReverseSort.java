import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ReverseSort {
    public static void main(String[] args) throws IOException{
        BufferedReader task = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(task.readLine());
        String[] nums = task.readLine().trim().split("\\s+");
        int arr[] = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(nums[i]);
        }
        int[] sortednums = Arrays.copyOf(arr, len);
        if(len==1){
            System.out.println("YES");
        }
        else if (len ==2 ){
            if(arr[0] <= arr[1]){
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
        sort(sortednums);
        if (len >=3){
            if(isSortable(arr, sortednums)){
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
    public static boolean isSortable(int[] nums, int[] sortednums) {
        int n = nums.length;
        int maxTries = n + n; // reasonable upper bound
        int tries = 0;

        while (tries < maxTries) {
            boolean changedInPass = false;
            int i = 0;

            while (i <= n - 3) {
                if (nums[i] > nums[i + 1] || nums[i + 1] > nums[i + 2]) {
                    reverseWindow(nums, i);
                    changedInPass = true;
                }
                i++;
            }

            if (Arrays.equals(nums, sortednums)) return true;
            if (!changedInPass) break; // no changes in entire pass -> stuck
            tries++;
        }

        return Arrays.equals(nums, sortednums);
    }

    public static void reverseWindow(int[] nums, int start){

        int temp = nums[start];
        nums[start] = nums[start + 2];
        nums[start + 2] = temp;
    }
    public static void sort(int[] arr){
        if (arr.length == 0) return;
        int max = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > max) max = arr[i];
        int count[] = new int[max + 1];
        for (int i : arr) count[i]++;
        int index = 0;
        for (int i = 0; i <= max; i++)
            while (count[i]-- > 0)
                arr[index++] = i;
    }
}
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//
//public class ReverseSort {
//    public static void main(String[] args) throws IOException{
//        BufferedReader task = new BufferedReader(new InputStreamReader(System.in));
//        int len = Integer.parseInt(task.readLine());
//        String[] nums = task.readLine().trim().split("\\s+");
//        int arr[] = new int[len];
//        for (int i = 0; i < len; i++) {
//            arr[i] = Integer.parseInt(nums[i]);
//        }
//        int[] sortednums = Arrays.copyOf(arr, len);
//        if(len==1){
//            System.out.println("YES");
//        }
//        else if (len ==2 ){
//            if(arr[0] <= arr[1]){
//                System.out.println("YES");
//            } else {
//                System.out.println("NO");
//            }
//        }
//        sorter(sortednums);
//        if (len >=3){
//            if(isSortable(arr, sortednums)){
//                System.out.println("YES");
//            } else {
//                System.out.println("NO");
//            }
//        }
//    }
//    public static boolean isSortable(int[] numbers, int[] sortedNumbers) {
//        int n = numbers.length;
//        int maxTries = n + n; // reasonable upper bound
//        int tries = 0;
//
//        while (tries < maxTries) {
//            boolean changedInPass = false;
//            int i = 0;
//
//            while (i <= n - 3) {
//                if (numbers[i + 1] > numbers[i + 2] || numbers[i] > numbers[i + 1]) {
//                    swapper(numbers, i);
//                    changedInPass = true;
//                }
//                i++;
//            }
//
//            if (Arrays.equals(numbers, sortedNumbers)) return true;
//            if (!changedInPass) break;
//        }
//
//        return Arrays.equals(numbers, sortedNumbers);
//    }
//
//    public static void swapper(int[] nums, int start){
//
//        int temp = nums[start];
//        nums[start] = nums[start + 2];
//        nums[start + 2] = temp;
//    }
//    public static void sorter(int[] arr){
//        if (arr.length == 0) return;
//        int max = arr[0];
//        for (int i = 1; i < arr.length; i++)
//            if (arr[i] > max) max = arr[i];
//        int count[] = new int[max + 1];
//        for (int i : arr) count[i]++;
//        int index = 0;
//        for (int i = 0; i <= max; i++)
//            while (count[i]-- > 0)
//                arr[index++] = i;
//    }
//}
