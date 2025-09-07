import java.util.*;
import java.io.*;

public class Friends {

    static class Union{

        static int[] parent;
        static int[] size;

        Union(int num) {
            parent = new int[num + 1];
            size = new int[num + 1];

            for (int i = 1; i <= num; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        static int parentFind(int a){
            if(parent[a]!=a){
                parent[a] = parentFind(parent[a]);
            }
            return parent[a];
        }

        static int befriend(int a, int b){
            int x = parentFind(a);
            int y = parentFind(b);

            if(x==y){
                return size[x];
            }
            if(size[x]<size[y]){
                parent[x] = y;
                size[y] += size[x];
                return size[y];
            } else {
                parent[y] = x;
                size[x] += size[y];
                return size[x];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int[] nk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int num = nk[0];
        int friendships = nk[1];
        Union uf = new Union(num);
        for (int i = 1; i <= friendships; i++) {
            int[] ab = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = ab[0];
            int b = ab[1];
            out.println(uf.befriend(a,b));
        }

        br.close();
        out.close();
    }
}
