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
        int Q = Integer.parseInt(std.nextToken());
        List<point>[] list = new List[N+1];
        for(int i=0;i<N+1;i++) list[i] = new ArrayList<>();
        for(int i=0;i<N-1;i++){
            std = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(std.nextToken());
            int y = Integer.parseInt(std.nextToken());
            int z = Integer.parseInt(std.nextToken());
            list[x].add(new point(y,z));
            list[y].add(new point(x,z));
        }
        Queue<point> ans = new LinkedList<>();
        for(int i=0;i<Q;i++){
            std = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(std.nextToken());
            int x = Integer.parseInt(std.nextToken());
            ans.offer(new point(K,x));
        }
        int[][] ch = new int[N+1][N+1];
        for(int i=1;i<=N;i++){
            int[] check = new int[N+1];
            check[i] = 1;
            Queue<point> l = new LinkedList<>();
            for(point tmp : list[i]) {
                check[tmp.x] = 1;
                l.offer(tmp);
            }
            while(!l.isEmpty()){
                point tmp = l.poll();
                ch[i][tmp.x] = tmp.y;
                ch[tmp.x][i] = tmp.y;
                for(point t : list[tmp.x]){
                    if(check[t.x] == 0){
                        check[t.x] = 1;
                        if(tmp.y < t.y) l.offer(new point(t.x,tmp.y));
                        else l.offer(t);
                    }
                }
            }
        }
        while(!ans.isEmpty()){
            point tmp = ans.poll();
            int count = 0;
            for(int i=1;i<N+1;i++){
                if(ch[tmp.y][i] >= tmp.x) count++;
            }
            str.append(count+"\n");
        }
        bw.write(str.toString());
        bw.flush();
        bw.close();
     }
}