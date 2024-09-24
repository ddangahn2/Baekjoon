import java.util.*;

public class Main {
    public static int[][] board;
    public static int[] x = {-1,0,1,0};
    public static int[] y = {0,1,0,-1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        board = new int[N][M];

        int r = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        int count = 0;

        while (true) {
            if (board[r][c] == 0) {
                board[r][c] = 2;
                count += 1;
            }
            if (board[r][c+1] != 0 && board[r][c-1] != 0 && board[r+1][c] != 0 && board[r-1][c] != 0) {
                r -= x[d];
                c -= y[d];
                if (board[r][c] == 1) {
                    break;
                }
                continue;
            }
            else {
                while(true) {
                    d -= 1;
                    if (d < 0) {
                        d = 3;
                    }
                    if (board[r + x[d]][c + y[d]] == 0) {
                        r += x[d];
                        c += y[d];
                        break;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
