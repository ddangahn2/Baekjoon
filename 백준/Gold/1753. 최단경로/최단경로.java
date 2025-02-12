import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int[] dist;
    public static boolean[] visited;
    public static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(br.readLine());

        dist = new int[V+1];
        visited = new boolean[V+1];

        graph = new ArrayList[V+1];
        for(int i=0; i<=V; i++) {
            graph[i] = new ArrayList<int[]>();
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new int[] {v, w});
//            graph[v].add(new int[] {u, w});
        }

        dijkstra(K);

        for(int i=1; i<=V; i++) {
            if(dist[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }

    public static void dijkstra(int k) {
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<int[]> pq = new PriorityQueue<>((a,b) -> {
            return a[1] - b[1];
        });
        pq.add(new int[]{k, 0});
        dist[k] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.remove();

            if(visited[cur[0]]) continue;
            visited[cur[0]] = true;

            for(int[] adjInfo : graph[cur[0]]) {
                int adj = adjInfo[0];
                int cost = adjInfo[1];

                if(dist[adj] > cost + cur[1]) {
                    dist[adj] = cost + cur[1];
                }
                pq.add(new int[]{adj, dist[adj]});
            }
        }
    }
}