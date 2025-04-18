import java.util.*;
import java.io.*;

class Node {
    int v; // 정점
    int c; // 가격
    int d; // 시간

    Node(int v, int c, int d) {
        this.v = v;
        this.c = c;
        this.d = d;
    }
}

public class Main {
    public static int INF = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        int K = Integer.parseInt(s[2]);

        ArrayList<Node>[] arr = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < K; i++) {
            s = br.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            int c = Integer.parseInt(s[2]); // 비용
            int d = Integer.parseInt(s[3]); // 시간
            arr[u].add(new Node(v, c, d)); // 방향성이 있는 그래프
        }

        int[][] dis = new int[M + 1][N + 1]; // 비용을 인덱스로, 도착 노드를 인덱스로, 값은 시간
        for (int i = 0; i <= M; i++) {
            Arrays.fill(dis[i], INF);
        }

        dis[0][1] = 0; // 시작점 초기화
        for (int i = 0; i < M; i++) {
            for (int j = 1; j <= N; j++) {
                for (Node temp : arr[j]) {
                    if (i + temp.c <= M) {
                        dis[i + temp.c][temp.v] = Math.min(dis[i + temp.c][temp.v], dis[i][j] + temp.d);
                    }
                }
            }
        }

        int result = INF;
        for (int i = 0; i <= M; i++) {
            result = Math.min(result, dis[i][N]);
        }

        System.out.println(result == INF ? "Poor KCM" : result);
    }
}
