import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static StringTokenizer st;
    public static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][N];
        for(int i=0; i<N; i++) {
            Arrays.fill(board[i], Integer.MAX_VALUE);
            board[i][i] = 0;
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            board[a][b] = 1;
        }

        for(int k=0; k<N; k++) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(board[i][k] == Integer.MAX_VALUE || board[k][j] == Integer.MAX_VALUE) continue;

                    if(board[i][j] > board[i][k] + board[k][j]) {
                        board[i][j] = 1;
                    }
                }
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(board[i][j] == 1) {
                    board[j][i] = 1;
                }
            }
        }
        int sum = 0;

        for(int i=0; i<N; i++) {
            int count = 0;
            for(int j=0; j<N; j++) {
                if(board[i][j] == 1) {
                    count++;
                }
            }
            if(count == N-1) {
                sum++;
            }
        }

        System.out.println(sum);
    }
}