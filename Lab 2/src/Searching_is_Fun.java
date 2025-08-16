import java.io.*;
import java.util.Arrays;

public class Searching_is_Fun {
    public static void main(String[] args) throws IOException {
        BufferedReader task = new BufferedReader(new java.io.InputStreamReader(System.in));
        int input = Integer.parseInt(task.readLine().trim());
        StringBuilder output = new StringBuilder();
        while(input-- > 0) {
            long[] nums = Arrays.stream(task.readLine().trim().split(" ")).mapToLong(Long::parseLong).toArray();
            long divisor = nums[1], k = nums[0];
            long low = 1, high = k*2;

            while(low<high){
                Long mid = (high+low)/2;
                if((mid - (mid/ divisor)) < k) low = mid + 1;
                else high = mid;
            }
            output.append(low).append("\n");
        }
        System.out.println(output);
    }
}
/*ekhane mid porjonto travle kore ami ber koretesi koyta number ase given number diye divisible na by using the
 * formula mid - mid / divisor, ekhane mid use hoise karon ei formula diye jei k porjonto divisor ase koyta ba
 * in this case nai koyta sheta ber kora jay. So amra calculate kore jeta pailam sheta jei target ta ase otar cheye boro
 * ki choto ota consider kore lower and upper k check kortesi(The format for the binary search is pretty same
 * throughout all codes)*/



