import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
public class FastSum {
    public static void main(String [] args ) throws IOException{
        BufferedReader task = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(task.readLine());
        for (int i = 0; i < input; i++) {
            Long temp = Long.parseLong(task.readLine());
            Long sum = temp*(temp + 1) / 2;
            System.out.println(sum);
        }
    }
}
