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
        int b = Integer.parseInt(std.nextToken());
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(a);
        int[] check = new int[100001];
        int[] ch = new int[100001];
        check[a] = 1;
        int count = 0;
        int cnt =0 ;
        boolean flag = true;
        Queue<Integer> list = new LinkedList<>();
        while(!Q.isEmpty()){
            int len = Q.size();
            for(int i=0;i<len;i++){
                int tmp = Q.poll();
                if(tmp+1<check.length&&ch[tmp+1]==0){
                    if(check[tmp+1] == 0){
                        check[tmp+1] = check[tmp];
                        Q.offer(tmp+1);
                    }
                    else{
                        check[tmp+1] += check[tmp];
                    }
                    list.offer(tmp+1);
                }
                if(tmp-1>=0&&ch[tmp-1]==0){
                    if(check[tmp-1] == 0){
                        check[tmp-1] = check[tmp];
                        Q.offer(tmp-1);
                    }
                    else{
                        check[tmp-1] += check[tmp];
                    }
                    list.offer(tmp-1);
                }
                if(tmp*2<check.length&&ch[tmp*2]==0){
                    if(check[tmp*2] == 0){
                        check[tmp*2] = check[tmp];
                        Q.offer(tmp*2);
                    }
                    else{
                        check[tmp*2] += check[tmp];
                    }
                    list.offer(tmp*2);
                }
            }
            count++;
            if(check[b] != 0) break;
            while(!list.isEmpty()) ch[list.poll()] = 1;
        }
        if(a==b) str.append(0+"\n"+1);
        else str.append(count+"\n"+check[b]);
        bw.write(str.toString());
        bw.flush();
        bw.close();
     }
}