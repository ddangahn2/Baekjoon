import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static StringTokenizer st;
    public static StringBuilder sb = new StringBuilder();

    public static int N;
    public static ArrayList<int[]>[] graph;
    public static int[] depth;
    public static int[][] parent;
    public static int[][][] minmax;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        parent = new int[20][N+1];
        minmax = new int[20][N+1][2];
        graph = new ArrayList[N+1];
        depth = new int[N+1];
        for(int i=0; i<N+1; i++) graph[i] = new ArrayList<int[]>();

        for(int i=0; i<N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph[A].add(new int[]{B, C});
            graph[B].add(new int[]{A, C});
        }

        // bfs()
        bfs();

        // makeParent
        makeParent();

        int K = Integer.parseInt(br.readLine());

        for(int i=0; i<K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int D = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            get(D, E);
        }
        System.out.println(sb);
    }

    public static void get(int D, int E) {
        // E가 더 깊음
        if(depth[D] > depth[E]) {
            int temp = D;
            D = E;
            E = temp;
        }
        int min = 1000001;
        int max = 0;

        // E끌어올리기
        for(int i=19; i>=0; i--) {
            if(depth[E] - depth[D] >= (1<<i)) {
                min = Math.min(min, minmax[i][E][0]);
                max = Math.max(max, minmax[i][E][1]);
                E = parent[i][E];
            }
        }

        if(D == E) {
            sb.append(min).append(" ").append(max).append("\n");
            return;
        }

        // 다르면 공통조상까지 끌어올리기
        for(int i=19; i>=0; i--) {
            if(parent[i][E] != parent[i][D]) {
                min = Math.min(Math.min(min, minmax[i][E][0]), minmax[i][D][0]);
                max = Math.max(Math.max(max, minmax[i][E][1]), minmax[i][D][1]);
                E = parent[i][E];
                D = parent[i][D];
            }
        }

        min = Math.min(Math.min(min, minmax[0][E][0]), minmax[0][D][0]);
        max = Math.max(Math.max(max, minmax[0][E][1]), minmax[0][D][1]);
        sb.append(min).append(" ").append(max).append("\n");
    }

    public static void bfs() {
        boolean[] visited = new boolean[N+1];

        Queue<Integer> q = new LinkedList();
        q.add(1);
        depth[1] = 1;
        visited[1] = true;

        while(!q.isEmpty()) {
            int cur = q.remove();

            ArrayList<int[]> adjArr = graph[cur];

            for(int[] adj : adjArr) {
                if(!visited[adj[0]]) {
                    visited[adj[0]] = true;
                    depth[adj[0]] = depth[cur] + 1;
                    q.add(adj[0]);
                    parent[0][adj[0]] = cur;
                    minmax[0][adj[0]] = new int[] {adj[1], adj[1]};
                }
            }
        }
    }

    public static void makeParent() {
        for(int k=1; k<20; k++) {
            for(int v=1; v<=N; v++) {
                parent[k][v] = parent[k-1][parent[k-1][v]];

                int kvmin = minmax[k-1][v][0];
                int kvmax = minmax[k-1][v][1];

                int k_1vmin = minmax[k-1][parent[k-1][v]][0];
                int k_1vmax = minmax[k-1][parent[k-1][v]][1];

                minmax[k][v][0] = min(kvmin, kvmax, k_1vmin, k_1vmax);
                minmax[k][v][1] = max(kvmin, kvmax, k_1vmin, k_1vmax);
            }
        }
    }

    public static int min(int a, int b, int c, int d) {
        return Math.min(Math.min(a, b), Math.min(c, d));
    }

    public static int max(int a, int b, int c, int d) {
        return Math.max(Math.max(a, b), Math.max(c, d));
    }
}