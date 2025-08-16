import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FastSeriesDrift {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(test-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            long [] elements = new long[st.countTokens()];
            for (int i = 0; i < elements.length; i++) {
                elements[i] = Long.parseLong(st.nextToken());
            }
            long base = elements[0];
            long power = elements[1];
            long mod = elements[2];
            List<Long> powers = new ArrayList<>();
            Set<Long> seen = new HashSet<>();
            long currentPower = base % mod;
            while(!seen.contains(currentPower)){
                seen.add(currentPower);
                powers.add(currentPower);
                currentPower = (currentPower * base) % mod;
            }
            long cycles_size = powers.size();
            long fullCycle_num = power / cycles_size;
            int rem = (int)(power % cycles_size);

            long res = 0;
            long cycle = 0;

            for(long value : powers) {;
                cycle = (cycle + value) % mod;
            }
            res = (cycle * fullCycle_num) % mod;
            for(int i = 0; i < rem; i++) {
                res = (res + powers.get(i)) % mod;
            }
            sb.append(res).append("\n");
        }
        System.out.print(sb.toString().trim());
    }
}
