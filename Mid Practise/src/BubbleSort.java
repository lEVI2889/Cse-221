public class BubbleSort {
    public static void bubbleSortDescending(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Swap if current element is smaller than the next
                if (arr[j] < arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 34, 22, 12, 25, 11, 90};

        System.out.println("Original array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        bubbleSortDescending(arr);

        System.out.println("\nSorted array in descending order:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
/**/