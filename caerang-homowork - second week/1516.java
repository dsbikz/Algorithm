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
    public static void DFS(int x,List<Integer>[] list,ArrayList<Integer> l,int[] check){
        for(int tmp : list[x]){
            if(check[tmp] == 0){
                check[tmp] = 1;
                DFS(tmp,list,l,check);
            }
        }
        l.add(x);
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer std = new StringTokenizer(br.readLine());
        StringBuilder str = new StringBuilder();
        int a = Integer.parseInt(std.nextToken());
        List<Integer>[] list = new List[a+1];
        List<Integer>[] ll = new List[a+1];
        for(int i=0;i<a+1;i++) {
            list[i] = new ArrayList<>();
            ll[i] = new ArrayList<>();
        }
        int[] count = new int[a+1];
        int[] time = new int[a+1];
        boolean flag = true;
        for(int i=1;i<=a;i++){
            std = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(std.nextToken());
            while(flag){
                int x = Integer.parseInt(std.nextToken());
                if(x == -1) break;
                list[x].add(i);
                ll[i].add(x);
            }
        }
        ArrayList<Integer> topo = new ArrayList<>();
        int[] check = new int[a+1];
        for(int i=1;i<=a;i++){
            if(check[i] == 0) {
                for(int x : list[i]) {
                    if(check[x] == 0) {
                        check[x] = 1;
                        DFS(x,list,topo,check);
                    }
                }
                check[i] = 1;
                topo.add(i);
            }
        }
//        for(int x : topo) System.out.print(x+" ");
        Collections.reverse(topo);
        int[] answer = new int[a+1];
        for(int i=0;i<topo.size();i++){
            int tmp = topo.get(i);
            int t = 0;
            for(int x : ll[tmp]){
                t = Math.max(t,answer[x]);
            }
            answer[tmp] = t+time[tmp];
        }
//        for(int x : time) System.out.print(x+" ");
        for(int i=1;i<=a;i++){
            System.out.println(answer[i]);
        }
     }
}
