import java.io.*;
import java.util.*;
class point implements Comparable<point>{
    int x,y;
    point(int x,int y){
        this.x=x;
        this.y=y;
    }
    @Override
    public int compareTo(point o){
        return this.x-o.x;
    }
}
public class Main {
    public static int BFS(int x,int y,int[][] ch,int row,int col){
        Queue<point> Q = new LinkedList<>();
        int[] ck = {1,0,-1,0};
        int[] cj = {0,1,0,-1};
        Q.offer(new point(x,y));
        ch[x][y] = 0;
        int count = 1;
        while(!Q.isEmpty()){
            point tmp = Q.poll();
            for(int i=0;i<4;i++){
                int nx = tmp.x+ck[i];
                int ny = tmp.y+cj[i];
                if(nx>=0&&nx<row&&ny>=0&&ny<col&&ch[nx][ny] == 1){
                    count++;
                    ch[nx][ny] = 0;
                    Q.offer(new point(nx,ny));
                }
            }
        }
        return count;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer std = new StringTokenizer(br.readLine());
        StringBuilder str = new StringBuilder();
        int row = Integer.parseInt(std.nextToken());
        int col = Integer.parseInt(std.nextToken());
        int[][] ch = new int[row][col];
        for(int i=0;i<row;i++){
            std = new StringTokenizer(br.readLine());
            for(int j=0;j<col;j++){
                ch[i][j] = Integer.parseInt(std.nextToken());
            }
        }
        int[][] check = new int[row][col];
        int answer = 0;
        int cnt = 0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(ch[i][j] == 1) {
                    cnt++;
                    answer = Math.max(answer,BFS(i,j,ch,row,col));
                }
            }
        }
        str.append(cnt+"\n");
        str.append(answer);
        bw.write(str.toString());
        bw.flush();
        bw.close();
     }
}