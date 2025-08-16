import java.io.*;

public class MergeSort {
    static int[] values, indices;

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
            indices[i] = i + 1; // 1-based index
        }
        //Ekhan theke merge sort//
        mergeSort(0, n - 1);

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

    static void mergeSort(int low, int high) { //low = 0 , high = n-1
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(low, mid);
            mergeSort(mid + 1, high);
            merge(low, mid, high);
        }
    }

    static void merge(int low, int mid, int high) {
        int[] tempValues = new int[high - low + 1];
        int[] tempIndices = new int[high - low + 1];
        int i = low, j = mid + 1, k = 0;

        while (i <= mid && j <= high) {
            if (values[i] <= values[j]) {
                tempValues[k] = values[i];
                tempIndices[k++] = indices[i++];
            } else {
                tempValues[k] = values[j];
                tempIndices[k++] = indices[j++];
            }
        }
        while (i <= mid) {
            tempValues[k] = values[i];
            tempIndices[k++] = indices[i++];
        }
        while (j <= high) {
            tempValues[k] = values[j];
            tempIndices[k++] = indices[j++];
        }
        for (i = low, k = 0; i <= high; i++, k++) {
            values[i] = tempValues[k];
            indices[i] = tempIndices[k];
        }
    }
}
