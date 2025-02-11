import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static StringTokenizer st;
    public static StringBuilder sb = new StringBuilder();

    public static ArrayList<Integer>[] arr;
    public static int[] depth;
    public static int[][] parent;

    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        depth = new int[N+1];
        parent = new int[20][N+1];

        arr = new ArrayList[N+1];
        for(int i=0; i<N+1; i++) {
            arr[i] = new ArrayList<>();
        }

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            arr[n1].add(n2);
            arr[n2].add(n1);
        }

        bfs();

        findParent();

        int M = Integer.parseInt(br.readLine());

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            findLCA(n1, n2);
        }
        System.out.print(sb);
    }

    public static void bfs() {
        boolean[] visited = new boolean[N+1];

        int root = 1;
        depth[root] = 1;

        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        visited[root] = true;

        while(!q.isEmpty()) {
            int cur = q.remove();

            for(int adj : arr[cur]) {
                if(!visited[adj]) {
                    parent[0][adj] = cur;
                    depth[adj] = depth[cur] + 1;
                    visited[adj] = true;
                    q.add(adj);
                }
            }
        }
    }

    public static void findParent() {
        for(int i=1; i<20; i++) {
            for(int j=1; j<=N; j++) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }
    }

    public static void findLCA(int n1, int n2) {
        // n1이 더 깊게 만들기
        if(depth[n1] < depth[n2]) {
            int temp1 = n1;
            n1 = n2;
            n2 = temp1;
        }

        // n1 끌어올리기
        for(int i=19; i>=0; i--) {
            if(depth[n1] - depth[n2] >= (1<<i)) {
                n1 = parent[i][n1];
            }
        }

        // 조상 확인
        if(n1 == n2) {
            sb.append(n1).append("\n");
            return;
        }

        // 조상 찾기
        for(int i=19; i>=0; i--) {
            if(parent[i][n1] != parent[i][n2]) {
                n1 = parent[i][n1];
                n2 = parent[i][n2];
            }
        }
        sb.append(parent[0][n1]).append("\n");
    }
}