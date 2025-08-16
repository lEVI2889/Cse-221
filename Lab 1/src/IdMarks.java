import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class IdMarks {
    public static void main(String[] args) throws IOException {
        BufferedReader task=new BufferedReader(new InputStreamReader(System.in));
        int inputs=Integer.parseInt(task.readLine());
        String[] identity = task.readLine().trim().split("\\s+");
        String[] m = task.readLine().trim().split("\\s+");
        int[] marks = new int[inputs];
        int[] id = new int[inputs];
        for (int i = 0; i < inputs; i++) {
            marks[i] = Integer.parseInt(m[i]);
            id[i] = Integer.parseInt(identity[i]);
        }
        int swaps =0;
        for (int i = 0; i < inputs; i++) {
            int maxiNum = 0;
            int maxiIdx = -89;
            for(int j = i; j<inputs; j++){
                if(maxiNum==marks[j]){
                    if(id[maxiIdx]>id[j]){
                        maxiNum = marks[j];
                        maxiIdx = j;
                    }
                }
                else if(maxiNum<marks[j]){
                    maxiNum=marks[j];
                    maxiIdx=j;
                }
            }
            if(maxiIdx!=i){
                swapper(marks, id, i, maxiIdx);
                swaps++;
            }
        }
        System.out.println("Minimum swaps: "+ swaps);
        for(int k=0;k<inputs;k++){
            System.out.println("ID: "+id[k]+" Mark: "+marks[k]);
        }
    }
    static void swapper(int[] marks, int[] id, int i, int maxiIdx) {
        int tempId = id[i];
        int tempMarks = marks[i];
        id[i] = id[maxiIdx];
        marks[i] = marks[maxiIdx];
        id[maxiIdx] = tempId;
        marks[maxiIdx] = tempMarks;
    }
}