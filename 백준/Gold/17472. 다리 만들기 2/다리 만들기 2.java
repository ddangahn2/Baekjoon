import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int[][] board;
    public static int N,M, landNo;
    public static Queue<Bridge> pq = new PriorityQueue<>((a,b) -> {
        return a.length - b.length;
    });
    public static int[] P;
    public static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N+1][M+1];
        landNo = 1;

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                int land = Integer.parseInt(st.nextToken());

                board[i][j] = land;
            }
        }
        visited = new boolean[N+1][M+1];

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                if(!visited[i][j] && board[i][j] != 0) {
                    bfs(i, j);
                }
            }
        }
        landNo--;

        makeBridge();

        P = new int[landNo+1];
        for(int i=1; i<=landNo; i++) P[i] = i;

        int answer = 0;
        int connect = 0;

        while(!pq.isEmpty()) {
            Bridge bridge = pq.remove();

            if (find(bridge.st) == find(bridge.ed)) continue;

            answer += bridge.length;
            connect++;

            union(bridge.st, bridge.ed);
        }

        if(connect != landNo - 1) System.out.println(-1);
        else System.out.println(answer);
    }

    public static void bfs(int st, int ed) {
        int[] x = {-1,1,0,0};
        int[] y = {0,0,-1,1};

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {st,ed});
        visited[st][ed] = true;
        board[st][ed] = landNo;
        landNo++;

        while(!q.isEmpty()) {
            int[] cur = q.remove();

            for(int i=0; i<4; i++) {
                int nx = cur[0] + x[i];
                int ny = cur[1] + y[i];

                if(nx < 1 || nx > N || ny < 1 || ny > M) continue;
                if(visited[nx][ny]) continue;
                if(board[nx][ny] == 0) continue;

                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
                board[nx][ny] = board[cur[0]][cur[1]];
            }
        }
    }

    public static int find(int a) {
        if(P[a] == a) return a;
        return P[a] = find(P[a]);
    }

    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        P[pa] = pb;
    }

    public static void makeBridge(){
        for(int i=1; i<=N; i++){
            int length = 0;
            int leftLand = 0;

            for(int j=1; j<=M; j++){
                if(board[i][j] == 0) length++;
                else {
                    if(length > 1 && leftLand != 0) {
                        pq.add(new Bridge(leftLand, board[i][j], length));
                    }
                    leftLand = board[i][j];
                    length = 0;
                }
            }
        }
        for(int i=1; i<=M; i++) {
            int length = 0;
            int topLand = 0;

            for(int j=1; j<=N; j++) {
                if(board[j][i] == 0) length++;
                else {
                    if(length > 1 && topLand != 0) {
                        pq.add(new Bridge(topLand, board[j][i], length));
                    }
                    topLand = board[j][i];
                    length = 0;
                }
            }
        }
    }
}

class Bridge{
    int st;
    int ed;
    int length;

    public Bridge(int st, int ed, int length) {
        this.st = st;
        this.ed = ed;
        this.length = length;
    }
}