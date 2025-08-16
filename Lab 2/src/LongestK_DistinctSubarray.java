import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
public class LongestK_DistinctSubarray {
    public static void main(String[] args) throws IOException {
        BufferedReader task = new BufferedReader(new InputStreamReader(System.in));
        int [] inputs = Arrays.stream(task.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int size = inputs[0], value = inputs[1];
        int [] array = Arrays.stream(task.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        maxlengthFinder(value, size, array);
    }
    static void maxlengthFinder(int value, int size, int[] array){
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0, max_len = 0;
        for (int right = 0; right < size; right++) {
            map.put(array[right], map.getOrDefault(array[right], 0)+1);
            while (map.size()>value){
                map.put(array[left] , map.get(array[left])-1);
                if(map.get(array[left]) == 0){
                    map.remove(array[left]);
                }
                left++;
            }
            max_len = Math.max(max_len, right-left+1);
        }
        System.out.println(max_len);
    }
}
/*AMI JA BUCCHI: Distinct elements gula diye map tar size bare. Now we can't exceed the limit
            of the given value. And distinct elements na thakle just replace hoye jay, now amra ekhane count baracchi
            in case of the non distinct elements. So count barar shathe shathe length tao change hoye jacche and that helps
            the cause. and jokhon amader distinct element er size > k hoye jacche tokhon amra tally ta komacchi, to get
            the best possible outcome*/

/*
import java.util.*;

public class LongestK_DistinctSubarray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // Size of array
        int k = sc.nextInt(); // Max distinct elements allowed

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(findMaxLength(n, k, arr));
    }

    public static int findMaxLength(int n, int k, int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int left = 0, maxLen = 0;

        for (int right = 0; right < n; right++) {
            // Add current element to frequency map
            freqMap.put(arr[right], freqMap.getOrDefault(arr[right], 0) + 1);

            // If distinct elements exceed k, shrink window from the left
            while (freqMap.size() > k) {
                freqMap.put(arr[left], freqMap.get(arr[left]) - 1);
                if (freqMap.get(arr[left]) == 0) {
                    freqMap.remove(arr[left]);
                }
                left++;
            }

            // Update max length
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}

*/
