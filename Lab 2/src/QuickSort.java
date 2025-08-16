import java.io.*;
import java.util.*;

public class QuickSort {
    static int[] values, indices;
    static Random rand = new Random();

    public static void main(String[] args) throws IOException {
        BufferedReader task = new BufferedReader(new InputStreamReader(System.in));
        String[] first = task.readLine().split("\\s+");
        int n = Integer.parseInt(first[0]);
        int x = Integer.parseInt(first[1]);

        values = new int[n];
        indices = new int[n];
        String[] input = task.readLine().split("\\s+");
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(input[i]);
            indices[i] = i + 1;
        }

        //quickSort(0, n - 1);

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;
            while (left < right) {
                long sum = (long) values[i] + values[left] + values[right];
                if (sum == x) {
                    System.out.println(indices[i] + " " + indices[left] + " " + indices[right]);
                    return;
                } else if (sum < x) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(-1);
    }

    static void quickSort(int low, int high) {
        if (low < high) {
            int pivotIdx = partition(low, high);
            quickSort(low, pivotIdx - 1);
            quickSort(pivotIdx + 1, high);
        }
    }

    static int partition(int low, int high) {
        int pivotIndex = low + rand.nextInt(high - low + 1);
        swap(pivotIndex, high); // move pivot to end

        int pivot = values[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (values[j] <= pivot) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    static void swap(int i, int j) {
        int tmpVal = values[i];
        values[i] = values[j];
        values[j] = tmpVal;

        int tmpIdx = indices[i];
        indices[i] = indices[j];
        indices[j] = tmpIdx;
    }
}
