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
    public static int newGame(int[][] ch,Queue<point>[][] Q,int N,int K){
        int[] ck = {0,0,-1,1};
        int[] cj = {1,-1,0,0};
        for(int t=1;t<=1000;t++){
            for(int i=1;i<=K;i++){
                int check = 0;
                for(int j=0;j<N;j++){
                    for(int k=0;k<N;k++){
                        if(Q[j][k].size()>0&&Q[j][k].peek().x == i) {
                            point tmp = Q[j][k].poll();
                            int nx = j + ck[tmp.y];
                            int ny = k + cj[tmp.y];
                            if (!(nx >= 0 && nx < N && ny >= 0 && ny < N) || ch[nx][ny] == 2) {
                                int d = tmp.y;
                                if (d % 2 == 0) d++;
                                else d--;
                                nx = j+ck[d];
                                ny = k+cj[d];
                                if (!(nx >= 0 && nx < N && ny >= 0 && ny < N) || ch[nx][ny] == 2){
                                    Queue<point> l = new LinkedList<>();
                                    l.offer(new point(tmp.x, d));
                                    while(!Q[j][k].isEmpty()) l.offer(Q[j][k].poll());
                                    while(!l.isEmpty()) Q[j][k].offer(l.poll());
                                }
                                else if (ch[nx][ny] == 0) {
                                    Queue<point> l = new LinkedList<>();
                                    l.offer(new point(tmp.x, d));
                                    while(!Q[j][k].isEmpty()) l.offer(Q[j][k].poll());
                                    while (!l.isEmpty()) Q[nx][ny].offer(l.poll());
                                    if(Q[nx][ny].size() >= 4) return t;
                                } else if (ch[nx][ny] == 1) {
                                    Stack<point> l = new Stack<>();
                                    l.push(new point(tmp.x, d));
                                    while(!Q[j][k].isEmpty()) l.push(Q[j][k].poll());
                                    while (!l.isEmpty()) Q[nx][ny].offer(l.pop());
                                    if(Q[nx][ny].size() >= 4) return t;
                                }

                            } else if (ch[nx][ny] == 0) {
                                Q[nx][ny].offer(tmp);
                                while (!Q[j][k].isEmpty()) Q[nx][ny].offer(Q[j][k].poll());
                                if(Q[nx][ny].size() >= 4) return t;
                            } else if (ch[nx][ny] == 1) {
                                Stack<point> l = new Stack<>();
                                l.push(tmp);
                                while (!Q[j][k].isEmpty()) l.push(Q[j][k].poll());
                                while (!l.isEmpty()) Q[nx][ny].offer(l.pop());
                                if(Q[nx][ny].size() >= 4) return t;
                            }
                            check = 1;
                        }
                        if(check == 1) break;
                    }
                    if(check == 1) break;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer std = new StringTokenizer(br.readLine());
        StringBuilder str = new StringBuilder();
        int N = Integer.parseInt(std.nextToken());
        int K = Integer.parseInt(std.nextToken());
        int[][] ch = new int[N][N];
        Queue<point>[][] stack = new Queue[N][N];
        for(int i=0;i<N;i++){
            std = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                stack[i][j] = new LinkedList<>();
                ch[i][j] = Integer.parseInt(std.nextToken());
            }
        }
        for(int i=1;i<=K;i++){
            std = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(std.nextToken());
            int y = Integer.parseInt(std.nextToken());
            int z = Integer.parseInt(std.nextToken());
            stack[x-1][y-1].offer(new point(i,z-1));
        }
        System.out.println(newGame(ch,stack,N,K));
     }
}