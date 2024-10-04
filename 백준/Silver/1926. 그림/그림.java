import java.util.*;

public class Main {
    static boolean[][] board;
    static int n, m;
    static int max = 0;
    static int count = 0;
    static int[] x = {-1,1,0,0};
    static int[] y = {0,0,-1,1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        board = new boolean[n][m];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(sc.nextInt() == 1){
                    board[i][j] = true;
                }
                else{
                    board[i][j] = false;
                }
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(board[i][j]){
                    count += 1;
                    bfs(i, j);
                }
            }
        }

        System.out.println(count);
        System.out.println(max);
    }
    public static void bfs(int i, int j){
        Queue<int[]> q = new LinkedList<>();

        int[] init = {i, j};
        board[i][j] = false;
        q.add(init);

        int area = 1;

        while(!q.isEmpty()){
            int[] pos = q.remove();

            for(int k=0; k<4; k++){
                int xpos = pos[0]+x[k];
                int ypos = pos[1]+y[k];

                if(xpos>=0 && xpos<n && ypos>=0 && ypos<m && board[xpos][ypos]){
                    board[xpos][ypos] = false;
                    area += 1;
                    int[] npos = {xpos, ypos};
                    q.add(npos);
                }
            }
        }
        max = Math.max(max, area);
    }
}