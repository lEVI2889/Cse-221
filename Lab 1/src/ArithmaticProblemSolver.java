import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class ArithmaticProblemSolver {
    public static void main(String[] args) throws IOException{
        BufferedReader task = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(task.readLine().trim());
        for (int i = 0; i < num; i++) {
            String exp = task.readLine().trim();
            String calc = exp.substring(10).trim();
            String[] parts = calc.split(" ");
            int f = Integer.parseInt(parts[0]);
            String operator = parts[1];
            int l = Integer.parseInt(parts[2]);
            double res = operation(f, operator, l);
            System.out.printf("%.6f\n", res);
        }
    }
    static double operation(int f, String operator, int l) {
        double res = 0.0;
        if(operator.equals("+")) {
            res = f+l;
        }
        if(operator.equals("-")) {
            res = f-l;
        }
        if(operator.equals("*")) {
            res = f*l;
        }
        if(operator.equals("/")) {
            res = (double)f/l;
        }
        return res;
    }
}
