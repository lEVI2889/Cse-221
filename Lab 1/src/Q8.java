
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q8 {
    public static void main(String[] args) throws IOException{
        InputStreamReader in=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(in);

        int n=Integer.parseInt(br.readLine());

        String[] name= new String[n];
        String[] time= new String[n];
        String[] inp= new String[n];
        int [] idx=new int [n];

        for(int i=0;i<n;i++){
            idx[i]=i;
            String s=br.readLine();
            inp[i]=s;
            String []s2=s.split(" ");
            name[i]=s2[0];
            time[i]=s2[s2.length-1];
        }

        for(int i=0;i<n-1;i++){
            int temp=i;
            for(int j=i+1;j<n;j++){
                boolean b=false;
                int num=name[temp].compareTo(name[j]);
                if(num>0) b=true;
                else if(num==0){
                    int x=time[temp].compareTo(time[j]);
                    if (x < 0) {
                        b = true;
                    } else if (x == 0 && idx[temp] > idx[j]) {
                        b = true;
                    }
                }
                if(b){
                    temp=j;
                }
            }
            if(temp!=i){
                String tempName = name[i];
                name[i] = name[temp];
                name[temp] = tempName;

                String tempTime = time[i];
                time[i] = time[temp];
                time[temp] = tempTime;

                String tempLine = inp[i];
                inp[i] = inp[temp];
                inp[temp] = tempLine;

                int tempIndex = idx[i];
                idx[i] = idx[temp];
                idx[temp] = tempIndex;
            }

        }
        for (int i = 0; i < n; i++) {
            System.out.println(inp[i]);
        }




    }
}