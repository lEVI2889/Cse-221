import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Arrays;
public class LongestSubarraySum {
    public static void main(String[] args) throws IOException {
        BufferedReader task = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(task.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int size = inputs[0], target = inputs[1];;
        int [] array = Arrays.stream(task.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int left = 0, maxLen = 0, sum = 0;
        for (int right = 0; right < size; right++) {
            sum += array[right];
            while(sum>target && left <= right){
                sum-=array[left++];
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        System.out.println(maxLen);
    }
}
