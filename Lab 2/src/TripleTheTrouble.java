import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
public class TripleTheTrouble {

    static int [] intArray, indices;
    static Random rand = new Random();
    public static void main(String[] args) throws IOException {
        BufferedReader task = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = task.readLine().trim().split("\\s+");
        int size = Integer.parseInt(inputs[0]);
        int target = Integer.parseInt(inputs[1]);
        intArray = new int[size];
        indices = new int[size];
        String[] array = task.readLine().trim().split("\\s+");
        for (int i = 0; i < array.length; i++) {
            intArray[i] = Integer.parseInt(array[i]);
            indices[i] = i + 1;
        }

        quicksort(0, size - 1);

        if(size == 1 || size == 2) {
            System.out.println(-1);
            return;
        }
        for (int i = 0; i < size - 2; i++) {
            int left = i+1;
            int right = size - 1;
            while(left<right){
                Long sum = (long) intArray[i]+ intArray[left] + intArray[right];
                if(sum<target){
                    left++;
                } else if(sum>target){
                    right--;
                } else {
                    System.out.println(indices[i] + " " + indices[left] + " " + indices[right]);
                    return;
                }
            }
        }
        System.out.println(-1);
    }
    static void quicksort(int low, int high) {
        if (low < high) {
            int pivot = partition(low, high);
            quicksort(low, pivot);
            quicksort(pivot + 1, high);
        }
    }

    static int partition(int low, int high) {
        int pivotIdx = low + rand.nextInt(high - low +1);
        int pivot = intArray[pivotIdx];
        int i = low - 1;
        int j = high + 1;
        while(true){
            do{
                i++;
            } while(intArray[i]<pivot);
            do{
                j--;
            } while(intArray[j]>pivot);
            if(i>=j){
                return j;
            }
            swap(i, j);
        }
    }
    static void swap(int i, int j) {
        int tempIdx = indices[i];
        indices[i] = indices[j];
        indices[j] = tempIdx;
        int tempVal = intArray[i];
        intArray[i] = intArray[j];
        intArray[j] = tempVal;
    }
}
