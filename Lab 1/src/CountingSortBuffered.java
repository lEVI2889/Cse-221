import java.io.*;

public class CountingSortBuffered {
    public static void main(String[] args) throws IOException {
        BufferedReader task = new BufferedReader(new InputStreamReader(System.in));

        // Read array length
        int len = Integer.parseInt(task.readLine());

        // Read array elements as a line of space-separated numbers
        String[] nums = task.readLine().trim().split("\\s+");
        int arr[] = new int[len];

        // Parse strings to integers
        for (int i = 0; i < len; i++)
            arr[i] = Integer.parseInt(nums[i]);

        System.out.println("Original Array:");
        printArray(arr);

        countingSort(arr);

        System.out.println("Sorted Array:");
        printArray(arr);
    }

    static void countingSort(int arr[]) {
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

    static void printArray(int arr[]) {
        for (int i : arr) System.out.print(i + " ");
        System.out.println();
    }
}
