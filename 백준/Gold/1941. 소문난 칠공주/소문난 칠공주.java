import java.util.*;

public class Main {
    static char[][] board = new char[5][5];

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int i=0; i<5; i++){
            String str = sc.nextLine();
            for(int j=0; j<5; j++){
                board[i][j] = str.charAt(j);
            }
        }

        ArrayList<Integer> arr = new ArrayList<>();
        dfs(0, 0, arr);

        System.out.println(count);
    }
    public static void dfs(int st, int lim, ArrayList<Integer> arr){
        if(lim > 3){
            return;
        }
        if(arr.size() == 7){
            if(bfs(arr)) {
                count++;
            }
            return;
        }
        for(int i=st; i<25; i++){
            arr.add(i);

            if(board[i/5][i%5] == 'Y') dfs(i + 1, lim+1, arr);
            else dfs(i + 1, lim, arr);

            arr.remove(Integer.valueOf(i));
        }
    }
    public static boolean bfs(ArrayList<Integer> arr){
        boolean result = false;

        boolean[][] bfsBoard = new boolean[5][5];

        for(int num: arr){
            int x = num / 5;
            int y = num % 5;
            bfsBoard[x][y] = true;
        }

        Queue<int[]> q = new LinkedList<>();
        int[] init = {arr.get(0) / 5, arr.get(0) % 5};
        bfsBoard[arr.get(0) / 5][arr.get(0) % 5] = false;
        q.add(init);

        int cnt = 1;

        while(!q.isEmpty()){
            int[] pos = q.remove();

            for(int d=0; d<4; d++){
                int nx = pos[0] + dx[d];
                int ny = pos[1] + dy[d];

                if(nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && bfsBoard[nx][ny]){
                    q.add(new int[]{nx, ny});
                    bfsBoard[nx][ny] = false;
                    cnt += 1;
                }
            }
        }

        if(cnt == 7){
            result = true;
        }

        return result;
    }
}