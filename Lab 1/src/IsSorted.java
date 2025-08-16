import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class IsSorted {
    public static void main(String[] args) throws IOException{
        BufferedReader task = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(task.readLine());
        for (int i = 0; i < n; i++) {
            int len = Integer.parseInt(task.readLine());
            String[] nums = task.readLine().trim().split("\\s+");
            int def = 0;
            int count = 0;
            for (int j = 0; j < len; j++) {
                int temp = Integer.parseInt(nums[j]);
                if(temp >= def){
                    def = temp;
                    count++;
                }
                else {
                    System.out.println("NO");
                    break;
                }
                if(count == len){
                    System.out.println("YES");
                }
            }
        }
    }
}


