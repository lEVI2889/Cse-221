import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class OddorEven {
    public static void main(String[] args) throws IOException {
        BufferedReader task = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(task.readLine());
        for (int i = 0; i < n; i++) {
            int temp = Integer.parseInt(task.readLine());
            if(temp % 2 == 0) {
                System.out.println(temp+ " is an Even number.");
            } else {
                System.out.println(temp+ " is an Odd number.");
            }
        }
    }
}