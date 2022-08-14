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
    public static int BFS(int a,int b,int x,int y,char[][] ch,int[] ck,int[] cj){
        Queue<point> Q = new LinkedList<>();
        ArrayList<point> list = new ArrayList<>();
        Q.offer(new point(x,y));
        list.add(new point(x,y));
        int[][] check = new int[a][b];
        check[x][y] = 1;
        int[] visited = new int[b];
        for(int i=0;i<b;i++) visited[i] = Integer.MIN_VALUE;
        visited[y] = x;
        while(!Q.isEmpty()){
            point tmp = Q.poll();
            for(int i=0;i<4;i++){
                int nx = tmp.x+ck[i];
                int ny = tmp.y+cj[i];
                if(nx>=0&&nx<a&&ny>=0&&ny<b&&ch[nx][ny]=='x'&&check[nx][ny] == 0){
                    check[nx][ny] = 1;
                    Q.offer(new point(nx,ny));
                    list.add(new point(nx,ny));
                    if(visited[ny] < nx) visited[ny] = nx;
                    if(nx == a-1) return 0;
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i=0;i<list.size();i++){
            point tmp = list.get(i);
            int t = 0;
            for(int j=tmp.x+1;j<a;j++){
                if(ch[j][tmp.y]=='x'&&check[j][tmp.y]==0) break;
                else t++;
            }
            min = Math.min(t,min);
        }
        if(min == 0 ) return 0;
        for(int i=0;i<b;i++){
            if(visited[i]!=Integer.MIN_VALUE){
                for(int j=visited[i];j>=0;j--){
                    if(ch[j][i]=='x'&&check[j][i]==1){
                        ch[j][i] = '.';
                        ch[j+min][i] = 'x';
                    }
                }
            }
        }
        return 1;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer std = new StringTokenizer(br.readLine());
        StringBuilder str = new StringBuilder();
        int a = Integer.parseInt(std.nextToken());
        int b = Integer.parseInt(std.nextToken());
        char[][] ch = new char[a][b];
        for(int i=0;i<a;i++){
            std = new StringTokenizer(br.readLine());
            String tmp = std.nextToken();
            ch[i] = tmp.toCharArray();
        }
        std = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(std.nextToken());
        std = new StringTokenizer(br.readLine());
        int[] ck = {1,0,-1,0};
        int[] cj = {0,1,0,-1};
        for(int i=0;i<k;i++){
            int t = Integer.parseInt(std.nextToken());
            int x = -1,y =-1;
            if(i%2==0){
                t = a-t;
                for(int j=0;j<b;j++){
                    if(ch[t][j]=='x'){
                        ch[t][j] = '.';
                        x = t;
                        y = j;
                        break;
                    }
                    else continue;
                }
            }
            else{
                t = a-t;
                for(int j=b-1;j>=0;j--){
                    if(ch[t][j]=='x'){
                        ch[t][j] = '.';
                        x = t;
                        y = j;
                        break;
                    }
                    else continue;
                }
            }
            if(!(x==-1&&y==-1)){
                for(int j=0;j<4;j++){
                    int nx = x+ck[j];
                    int ny = y+cj[j];
                    if(nx>=0&&nx<a&&ny>=0&&ny<b&&ch[nx][ny]=='x'){
                        int tmp = BFS(a,b,nx,ny,ch,ck,cj);
                        if(tmp == 1) break;
//                        else continue;
                    }
                }
            }
//            for(int m=0;m<a;m++){
//                for(int j=0;j<b;j++){
//                    System.out.print(ch[m][j]+" ");
//                }
//                System.out.println();
//            }
//            System.out.println();
        }
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){
                str.append(ch[i][j]);
            }
            str.append('\n');
        }
        bw.write(str.toString());
        bw.flush();
        bw.close();
     }
}