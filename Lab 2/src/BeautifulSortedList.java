import java.io.*;
import java.util.Arrays;
public class BeautifulSortedList {
    public static void main(String[] args) throws IOException {
        BufferedReader task = new BufferedReader(new InputStreamReader(System.in));
        int n1 = Integer.parseInt(task.readLine().trim());
        int [] ar1 = Arrays.stream(task.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n2 = Integer.parseInt(task.readLine().trim());
        int [] ar2 = Arrays.stream(task.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int [] merged = new int[n1 + n2];
        int i =0, j = 0, k = 0;
        while( i< n1 && j< n2 ){
            if(ar1[i]<=ar2[j]){
                merged[k++] = ar1[i++];
            }
            else {
                merged[k++]= ar2[j++];
            }
        }
        while(i<n1) merged[k++]=ar1[i++];
        while(j<n2) merged[k++]=ar2[j++];

        //output line likhsi
        StringBuilder str = new StringBuilder();
        for(int value : merged){
            str.append(value).append(" ");
        }
        System.out.println(str.toString().trim());
    }
}
