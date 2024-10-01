class Solution {
    static boolean[] visited;
    static int travel = 0;
    static int answer = 0;
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];

        dfs(k, dungeons, travel);
        
        return answer;
    }
    public static void dfs(int k, int[][] dungeons, int travel) {
        if(travel > answer) {
            answer = travel;
        }
        for(int i=0; i<dungeons.length; i++) {
            if(!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                dfs(k - dungeons[i][1], dungeons, travel + 1);
                visited[i] = false;
            }
        }
    }
}