import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int Hrow, Hcol;
    static int stRrow, stRcol, stBrow, stBcol;

    static char [][] board;
    static boolean [][][][] visited;

    static int [] x = {-1,1,0,0};
    static int [] y = {0,0,-1,1};

    static Deque<Snapshot> deq;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        for (int i=0; i<N; i++) {
            String line = sc.nextLine();
            for (int j=0; j<M; j++) {
                if ('R' == line.charAt(j)) {
                    stRcol = i;
                    stRrow = j;
                    board[i][j] = '.';
                } else if ('B' == line.charAt(j)) {
                    stBcol = i;
                    stBrow = j;
                    board[i][j] = '.';
                } else if ('O' == line.charAt(j)) {
                    Hcol = i;
                    Hrow = j;
                } else {
                    board[i][j] = line.charAt(j);
                }
            }
        }

        bfs();
    }
    public static void bfs() {
        boolean flag = false;
        int initialAttempt = 0;
        Snapshot init = new Snapshot(stRcol, stRrow, stBcol, stBrow, initialAttempt);

        visited[stRcol][stRrow][stBcol][stBrow] = true;
        deq = new ArrayDeque<>();
        deq.add(init);

        while (!deq.isEmpty()) {
            Snapshot popleftSnapshot = deq.pollFirst();

            for (int i=0; i<4; i++) {
                if (tilt(popleftSnapshot, i)) {
                    System.out.println(popleftSnapshot.attempt + 1);
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        if (!flag) {
            System.out.println(-1);
        }
    }
    public static boolean tilt(Snapshot snapshot, int direction) {
        int Rcol, Rrow, Bcol, Brow, attempt;
        Rcol = snapshot.Rcol;
        Rrow = snapshot.Rrow;
        Bcol = snapshot.Bcol;
        Brow = snapshot.Brow;
        attempt = snapshot.attempt;

        int Rmove = 0;
        int Bmove = 0;
        boolean Hflag = false;

        while (board[Rcol][Rrow] == '.') {
            Rcol += y[direction];
            Rrow += x[direction];
            Rmove++;

            if (Rcol == Hcol && Rrow == Hrow) {
                Hflag = true;
                break;
            }
        }
        Rcol -= y[direction];
        Rrow -= x[direction];

        while (board[Bcol][Brow] == '.') {
            Bcol += y[direction];
            Brow += x[direction];
            Bmove++;

            if (Bcol == Hcol && Brow == Hrow) {
                return false;
            }
        }
        Bcol -= y[direction];
        Brow -= x[direction];

        if (Hflag) {
            return true;
        } else {
            if (Rcol == Bcol && Rrow == Brow) {
                if (Rmove > Bmove) {
                    Rcol -= y[direction];
                    Rrow -= x[direction];
                } else {
                    Bcol -= y[direction];
                    Brow -= x[direction];
                }
            }
            if (!visited[Rcol][Rrow][Bcol][Brow] && attempt <= 8) {
                visited[Rcol][Rrow][Bcol][Brow] = true;
                deq.add(new Snapshot(Rcol, Rrow, Bcol, Brow, attempt + 1));
            }
            return false;
        }
    }
}

class Snapshot {
    int Rcol, Rrow, Bcol, Brow;
    int attempt;

    Snapshot(int Rcol, int Rrow, int Bcol, int Brow, int attempt) {
        this.Rcol = Rcol;
        this.Rrow = Rrow;
        this.Bcol = Bcol;
        this.Brow = Brow;
        this.attempt = attempt;
    }
}