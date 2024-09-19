import java.util.*;

public class Main {
    static int N;
    static int [][] board;
    static Queue<Board> q = new ArrayDeque<>();

    static int[] x = {-1,1,0,0};
    static int[] y = {0,0,-1,1};
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        StringTokenizer st;
        N = Integer.parseInt(sc.nextLine());

        board = new int[N][N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(sc.nextLine());
            for (int j=0; j<N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        q.add(new Board(board, 0));

        int globalMax = 0;
        while (!q.isEmpty()) {
            Board b = q.remove();
            if (b.att < 5) {
                for (int i = 0; i < 4; i++) {
                    Board nb = tilt(b, x[i], y[i]);
                    q.add(nb);
                }
            } else {
                int localMax = findMax(b.board);
                if (globalMax < localMax) {
                    globalMax = localMax;
                }
            }
        }

        System.out.println(globalMax);
    }

    static Board tilt(Board b, int xPos, int yPos){
        int [][] board = b.board;
        int [][] newBoard = new int[N][N];
        int att = b.att + 1;

        int stX, mvX;
        int stY, mvY;

        if (xPos == 0) {
            for (int i = 0; i < N; i++) {
                if (yPos == 1) {
                    stY = N-1;
                    mvY = N-1;
                } else {
                    stY = 0;
                    mvY = 0;
                }
                int temp = 0;
                for (int j = 0; j < N; j++) {
                    if(board[mvY][i] != 0) {
                        if (temp == 0) {
                            temp = board[mvY][i];
                        } else {
                            if (temp == board[mvY][i]) {
                                newBoard[stY][i] = temp * 2;
                                stY -= yPos;
                                temp = 0;
                            } else {
                                newBoard[stY][i] = temp;
                                stY -= yPos;
                                temp = board[mvY][i];
                            }
                        }
                    }
                    mvY -= yPos;
                }
                if (temp != 0) {
                    newBoard[stY][i] = temp;
                }
            }
        }
        if (yPos == 0) {
            for(int i=0; i<N; i++) {
                if (xPos == 1) {
                    stX = N-1;
                    mvX = N-1;
                } else {
                    stX = 0;
                    mvX = 0;
                }
                int temp = 0;
                for(int j=0; j<N; j++) {
                    if(board[i][mvX] != 0){
                        if (temp == 0) {
                            temp = board[i][mvX];
                        } else {
                            if (temp == board[i][mvX]) {
                                newBoard[i][stX] = temp * 2;
                                stX -= xPos;
                                temp = 0;
                            } else {
                                newBoard[i][stX] = temp;
                                stX -= xPos;
                                temp = board[i][mvX];
                            }
                        }
                    }
                    mvX -= xPos;
                }
                if (temp != 0) {
                    newBoard[i][stX] = temp;
                }
            }
        }
        return new Board(newBoard, att);
    }
    static int findMax(int [][] board) {
        int localMax = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (board[i][j] > localMax) {
                    localMax = board[i][j];
                }
            }
        }
        return localMax;
    }
}

class Board{
    int [][] board;
    int att;

    Board(int[][] board, int att) {
        this.board = board;
        this.att = att;
    }
}