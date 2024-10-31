import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int dir = 0;

        int sx = 0;
        int sy = 0;
        int time = 0;
        boolean flag = true;

        int K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());

            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            board[row-1][col-1] = 1;
        }

        Queue<int[]> snake = new LinkedList<>();
        snake.add(new int[]{0, 0});

        int L = Integer.parseInt(br.readLine());
        Queue<Move> move = new LinkedList<>();

        for(int i=0; i<L; i++){
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);

            move.add(new Move(t, d));
        }

        while(true){
            time++;

            sx += dx[dir];
            sy += dy[dir];

            if(sx < 0 || sx >= N || sy < 0 || sy >= N){
                break;
            }
            for(int[] body: snake){
                if(body[0] == sx && body[1] == sy) {
                    flag = false;
                    break;
                }
            }
            if(!flag) break;

            snake.add(new int[]{sx, sy});

            if(board[sx][sy] != 1) snake.poll();
            else board[sx][sy] = 0;
            

            if(!move.isEmpty() && time == move.peek().t){
                if(move.peek().d == 'D'){
                    dir += 1;
                    dir %= 4;
                }
                else{
                    dir += 3;
                    dir %= 4;
                }
                move.poll();
            }
        }

        System.out.println(time);
    }
}
class Move{
    int t;
    char d;

    Move(int time, char dir){
        this.t = time;
        this.d = dir;
    }
}