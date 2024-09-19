import java.util.*;

public class Main{
    static int N;
    static int[][] board;
    static Queue<PosChange> q = new ArrayDeque<>();
    static Queue<SnakeBody> snake = new ArrayDeque<>();

//    D 즉 오른쪽 기준
    static int[] xP = {0,1,0,-1};
    static int[] yP = {1,0,-1,0};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        StringTokenizer st;

        N = Integer.parseInt(sc.nextLine());
        board = new int [N][N];

        int K = Integer.parseInt(sc.nextLine());
        for (int i=0; i<K; i++) {
            st = new StringTokenizer(sc.nextLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x-1][y-1] = -1;
        }
        int L = Integer.parseInt(sc.nextLine());
        for (int i=0; i<L; i++) {
            st = new StringTokenizer(sc.nextLine());
            int t = Integer.parseInt(st.nextToken());
            char pos = st.nextToken().charAt(0);
            q.add(new PosChange(t, pos));
        }

        int time = 0;
        int position = 0;
        int snakeStX=0, snakeStY=0;
        snake.add(new SnakeBody(0,0));
        board[0][0] = 1;

        PosChange pc = q.remove();
        int nTime = pc.time;
        char nPos = pc.pos;
        boolean eatApple = false;

        while (true) {
            time++;
//            1. 머리 다음칸
            snakeStX += xP[position];
            snakeStY += yP[position];

            if(isEnd(snakeStX, snakeStY)) {
                break;
            }
            snake.add(new SnakeBody(snakeStX, snakeStY));

            if (board[snakeStX][snakeStY] == -1) {
                eatApple = true;
            }
            board[snakeStX][snakeStY] = 1;

            if (eatApple) {
                eatApple = false;
            } else {
                SnakeBody sb = snake.remove();
                board[sb.x][sb.y] = 0;
            }

            if (time == nTime) {
                if (nPos == 'D') {
                    position++;
                    if (position == 4) {
                        position = 0;
                    }
                } else {
                    position--;
                    if (position == -1) {
                        position = 3;
                    }
                }
                pc = q.poll();
                if (pc != null) {
                    nTime = pc.time;
                    nPos = pc.pos;
                }
            }
        }

        System.out.println(time);
    }
    public static boolean isEnd(int stX, int stY) {
        if (stX < 0 || stX >= N || stY < 0 || stY >= N || board[stX][stY] == 1) {
            return true;
        }
        return false;
    }
}

class PosChange {
    int time;
    char pos;
    PosChange(int time, char pos){
        this.time = time;
        this.pos = pos;
    }
}

class SnakeBody {
    int x;
    int y;
    SnakeBody(int x, int y) {
        this.x = x;
        this.y = y;
    }
}