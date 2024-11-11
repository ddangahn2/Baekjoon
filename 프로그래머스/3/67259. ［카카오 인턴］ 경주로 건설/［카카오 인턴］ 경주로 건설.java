import java.util.*;

class Solution {
    public static int[][][] Board;
    
    public static int[] dx = {-1,1,0,0};
    public static int[] dy = {0,0,-1,1};
    
    public int solution(int[][] board) {
        int answer = 0;
        
        int N = board.length;
        int M = board[0].length;
        
        Board = new int[N][M][4];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                Arrays.fill(Board[i][j], 100000000);
            }
        }
        
        Queue<int[]> q = new LinkedList<>();
        // x, y, 방향, 가격
        q.add(new int[]{0,0,3,0});
        q.add(new int[]{0,0,1,0});
        
        while(!q.isEmpty()){
            int[] cq = q.remove();
            
            for(int i=0; i<4; i++){
                int nx = cq[0] + dx[i];
                int ny = cq[1] + dy[i];
                int nc = cq[3];
                
                if(cq[2] == i) nc += 100;
                else nc += 600;
                
                if(nx >= 0 && nx < N && ny >= 0 && ny < M && board[nx][ny] == 0){
                    if(Board[nx][ny][i] <= nc) continue;
                    Board[nx][ny][i] = nc;
                    q.add(new int[]{nx, ny, i, nc});
                }
            }
        }
        answer = Math.min(Board[N-1][M-1][0], Board[N-1][M-1][1]);
        answer = Math.min(answer, Board[N-1][M-1][2]);
        answer = Math.min(answer, Board[N-1][M-1][3]);
        
        return answer;
    }
}