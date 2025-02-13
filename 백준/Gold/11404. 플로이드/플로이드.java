import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static StringTokenizer st;
    public static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];
        for(int i=0; i<N; i++) {
            Arrays.fill(board[i], Integer.MAX_VALUE);
            board[i][i] = 0;
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());

            board[a][b] = Math.min(board[a][b], c);
        }

        for(int k=0; k<N; k++) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(board[i][k] == Integer.MAX_VALUE || board[k][j] == Integer.MAX_VALUE) continue;

                    board[i][j] = Math.min(board[i][j], board[i][k] + board[k][j]);
                }
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(board[i][j] == Integer.MAX_VALUE) sb.append(0);
                else sb.append(board[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}