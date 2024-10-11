import java.util.*;
import java.io.*;

public class Main {
    static long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] dist = new long[N+1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        int[][] edge = new int[M][3];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            edge[i][0] = Integer.parseInt(st.nextToken());
            edge[i][1]= Integer.parseInt(st.nextToken());
            edge[i][2] = Integer.parseInt(st.nextToken());
        }

        boolean negCycle = false;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                int fr = edge[j][0];
                int to = edge[j][1];
                long cost = edge[j][2];
                
                if(dist[fr] == INF) continue;
                if(dist[fr] + cost < dist[to]){
                    if(i == N-1) negCycle = true;
                    dist[to] = dist[fr] + cost;
                }
            }
        }
        if(negCycle){
            System.out.println(-1);
        }
        else{
            for(int i=2; i<=N; i++){
                if(dist[i] == INF){
                    System.out.println(-1);
                }
                else System.out.println(dist[i]);
            }
        }
    }
}