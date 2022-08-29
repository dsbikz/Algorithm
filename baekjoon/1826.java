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
        int N = Integer.parseInt(std.nextToken());
        int[] ch = new int[1000001];
        for(int i=0;i<N;i++){
            std = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(std.nextToken());
            int y = Integer.parseInt(std.nextToken());
            ch[x] = y;
        }
        std = new StringTokenizer(br.readLine());
        int end = Integer.parseInt(std.nextToken());
        int fuel = Integer.parseInt(std.nextToken());
        PriorityQueue<Integer> Q = new PriorityQueue<>(Collections.reverseOrder());
        int answer = 0;
        for(int i=1;i<ch.length;i++){
            fuel--;
            if(i == end) break;
            if(ch[i] > 0) Q.offer(ch[i]);
            if(fuel == 0) {
                if(Q.size() == 0) {
                    answer = -1;
                    break;
                }
                else {
                    fuel = Q.poll();
                    answer++;
                }
            }
        }
        System.out.println(answer);
     }
}
