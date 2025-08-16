import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Train {
    public static void main(String[] args) throws IOException{
        BufferedReader task=new BufferedReader(new InputStreamReader(System.in));
        int inputs=Integer.parseInt(task.readLine());
        String[] lines = new String[inputs];
        String [] names = new String[inputs];
        String [] times = new String[inputs];
        int[] indexes = new int[inputs];
        for (int i = 0; i < inputs; i++) {
            indexes[i] = i;
            lines[i] = task.readLine();
            String[] details = lines[i].trim().split(" ");
            names[i] = details[0];
            times[i] = details[details.length-1];
        }
        for (int i = 0; i < inputs-1; i++) {
            int temporaryIndex = i;
            for (int j = i+1; j < inputs; j++) {
                int nameComparison = names[temporaryIndex].compareTo(names[j]);
                boolean before = false;
                if(nameComparison==0){
                    int timeComparison = times[temporaryIndex].compareTo(times[j]);
                    if(timeComparison==0 && indexes[temporaryIndex]>indexes[j]) {
                        before = true;
                    }
                    else if(timeComparison<0) {
                        before = true;
                    }
                }
                else if(nameComparison>0) {
                    before = true;
                }
                if(before) {
                    temporaryIndex = j;
                }
            }
            if(i!=temporaryIndex) {
                swapnames(names, i, temporaryIndex);
                swaptimes(times, i, temporaryIndex);
                swapLines(lines, i, temporaryIndex);
                swapIndexes(indexes, i, temporaryIndex);
            }
        }
        for (int i = 0; i < inputs; i++) {
            System.out.println(lines[i]);
        }
    }
    public static void swapnames(String[] nameSarray, int i, int j) {
        String temp = nameSarray[i];
        nameSarray[i] = nameSarray[j];
        nameSarray[j] = temp;
    }
    public static void swaptimes(String[] timeSarray, int i, int j) {
        String temp = timeSarray[i];
        timeSarray[i] = timeSarray[j];
        timeSarray[j] = temp;
    }
    public static void swapLines(String[] linesArray, int i, int j) {
        String temp = linesArray[i];
        linesArray[i] = linesArray[j];
        linesArray[j] = temp;
    }
    public static void swapIndexes(int[] indexArray, int i, int j) {
        int temp = indexArray[i];
        indexArray[i] = indexArray[j];
        indexArray[j] = temp;
    }
}