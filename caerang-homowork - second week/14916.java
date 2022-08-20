import java.io.*;
import java.util.*;
class point{
    int x,y;
    point(int x,int y){
        this.x=x;
        this.y=y;
    }
}
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer std = new StringTokenizer(br.readLine());
        StringBuilder str = new StringBuilder();
        int a = Integer.parseInt(std.nextToken());
        int answer = -1;
        int count = 0;
        while(a>=0){
            if(a%5 == 0) break;
            a = a-2;
            count++;
        }
        if(a<0) System.out.println(-1);
        else System.out.println(count+a/5);
     }
}
