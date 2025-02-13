import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static StringTokenizer st;
    public static StringBuilder sb = new StringBuilder();

    public static int N, M, K;
    public static Queue<Integer>[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ArrayList<Adj>[] graph = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
            graph[i] = new ArrayList<Adj>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Adj(b, c));
        }

        // 모든 지점에 PQ
        dist = new PriorityQueue[N+1];
        for(int i=0; i<=N; i++) {
            dist[i] = new PriorityQueue<Integer>((a,b) -> {
                return b-a;
            });
        }

        Queue<int[]> q = new PriorityQueue<>((a,b) -> {
            return a[1]-b[1];
        });
        q.add(new int[] {1, 0});
        dist[1].add(0);

        while(!q.isEmpty()) {
            int[] cur = q.remove();

            int curNode = cur[0];
            int curCost = cur[1];

            for(Adj adj : graph[curNode]) {
                if(dist[adj.node].size() < K) {
                    dist[adj.node].add(curCost + adj.cost);
                    q.add(new int[] {adj.node, curCost + adj.cost});
                }
                else {
                    if(dist[adj.node].peek() < curCost + adj.cost) continue;

                    dist[adj.node].remove();
                    dist[adj.node].add(curCost + adj.cost);
                    q.add(new int[] {adj.node, curCost + adj.cost});
                }
            }
        }

        for(int i=1; i<=N; i++) {
            if(dist[i].size() < K) System.out.println(-1);
            else System.out.println(dist[i].peek());
        }
    }
}

class Adj {
    int node;
    int cost;
    public Adj(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}