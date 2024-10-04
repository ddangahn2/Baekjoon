import java.util.*;

public class Main {
    static int N, M;
    static boolean[][] way;
    static int[][] board;

    static int[] x = {-1,1,0,0};
    static int[] y = {0,0,-1,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine();

        way = new boolean[N][M];
        board = new int[N][M];

        for(int i=0; i<N; i++){
            String line = sc.nextLine();
            for(int j=0; j<M; j++){
                char c = line.charAt(j);
                if(c == '1') way[i][j] = true;
            }
        }

        System.out.println(bfs(0, 0));
    }
    public static int bfs(int i, int j){
        Queue<int[]> q = new LinkedList<>();

        way[i][j] = false;
        board[i][j] = 1;

        int[] init = {i, j, board[i][j]};
        q.add(init);

        while(!q.isEmpty()){
            int[] pos = q.remove();
            int count = pos[2];

            if(pos[0] == N-1 && pos[1] == M-1){
                return count;
            }

            for(int d=0; d<4; d++){
                int xpos = pos[0] + x[d];
                int ypos = pos[1] + y[d];

                if(xpos >= 0 && xpos < N && ypos >= 0 && ypos < M && way[xpos][ypos]){
                    way[xpos][ypos] = false;
                    int[] npos = {xpos, ypos, count + 1};
                    q.add(npos);
                }
            }
        }
        return -1;
    }
}