import java.io.*;
import java.util.*;

public class HoaresQuickSort {
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

        quickSort(0, n - 1);

        // Two-pointer 3-sum search
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
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
            int p = hoarePartition(low, high);
            quickSort(low, p);
            quickSort(p + 1, high);
        }
    }

    static int hoarePartition(int low, int high) {
        /*int pivotIndex = low + rand.nextInt(high - low + 1);
        int pivot = values[pivotIndex];

        int i = low - 1;
        int j = high + 1;

        while (true) {
            do {
                i++;
            } while (values[i] < pivot);

            do {
                j--;
            } while (values[j] > pivot);

            if (i >= j)
                return j;

            swap(i, j);
        }*/
        int pivotIdx = low + rand.nextInt(high-low+1);
        int pivot = values[pivotIdx];
        int i = low - 1;
        int j = high + 1;
        while(true){
            do{
                i++;
            }while(values[i]<pivot);
            do{
                j--;
            }while(values[j]>pivot);

            if(i>=j) return j;

            swap(i,j);
        }
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
