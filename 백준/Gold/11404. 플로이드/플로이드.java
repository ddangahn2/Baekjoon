import java.util.*;

public class Main {
    static int INF = 1000000000;
    static int[][] board;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        board = new int[n][n];
        for(int i=0; i<n; i++){
            Arrays.fill(board[i], INF);
            board[i][i] = 0;
        }

        for(int i=0; i<m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            board[a-1][b-1] = Math.min(board[a-1][b-1], c);
        }

        for(int k=0; k<n; k++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    board[i][j] = Math.min(board[i][j], board[i][k] + board[k][j]);
                }
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(board[i][j] == INF) board[i][j] = 0;
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}