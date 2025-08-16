
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Q7 {
    public static void main(String[] args) throws IOException {
        InputStreamReader in= new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(in);

        int n=Integer.parseInt(br.readLine());
        String s=br.readLine();
        String s2=br.readLine();
        String[] marks=s2.split(" ");
        String[] id=s.split(" ");
        int swap=0;
        for(int i=0;i<n;i++){
            int max=0;
            int maxidx=-1;

            for (int j=i;j<n;j++){

                if(Integer.parseInt(marks[j])>max){
                    max=Integer.parseInt(marks[j]);
                    maxidx=j;

                }
                else if(Integer.parseInt(marks[j])==max){
                    if(Integer.parseInt(id[j])<Integer.parseInt(id[maxidx])){
                        max=Integer.parseInt(marks[j]);
                        maxidx=j;

                    }
                }
            }
            if(maxidx!=i){
                String temp=marks[i];
                String tempid=id[i];
                marks[i]=marks[maxidx];
                id[i]=id[maxidx];
                marks[maxidx]=temp;
                id[maxidx]=tempid;
                swap++;
            }
        }
        System.out.println("Minimum swaps: "+swap);
        for(int k=0;k<n;k++){
            System.out.println("ID: "+id[k]+" Mark: "+marks[k]);
        }
        br.close();
    }
}