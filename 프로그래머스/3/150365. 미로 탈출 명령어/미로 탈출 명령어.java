class Solution {
//     d, l, r, u
    static int[] x = {1, 0, 0, -1};
    static int[] y = {0, -1, 1, 0};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        StringBuilder sb = new StringBuilder("");
        bfs(sb, n-1, m-1, x-1, y-1, r-1, c-1, k);
        String answer = sb.toString();
        return answer;
    }
    public static void bfs(StringBuilder sb, int n, int m, int x, int y, int r, int c, int k) {
        if((Math.abs(x-r) + Math.abs(y-c) > k) || (k+x+y+r+c) % 2 == 1) {
            sb.append("impossible");
            return;
        }
        
//         이 경우 따로 고려해줘야하나?
        if(Math.abs(x-r) + Math.abs(y-c) == k) {
            finish(sb, x, y, r, c);
            return;
        }
//         맨 아래로 슛! ddd
        while(x < n) {
            x += 1;
            k -= 1;
            sb.append("d");
            if(Math.abs(x-r) + Math.abs(y-c) == k) {
                finish(sb, x, y, r, c);
                return;
            }
        }
//         맨 왼쪽으로 슛! lll
        while(y > 0) {
            y -= 1;
            k -= 1;
            sb.append("l");
            if(Math.abs(x-r) + Math.abs(y-c) == k) {
                finish(sb, x, y, r, c);
                return;
            }
        }
//         rlrlrlrlrl 반복
        while(true) {
            y += 1;
            k -= 1;
            sb.append("r");
            if(Math.abs(x-r) + Math.abs(y-c) == k) {
                finish(sb, x, y, r, c);
                return;
            }
            
            y -= 1;
            k -= 1;
            sb.append("l");
            if(Math.abs(x-r) + Math.abs(y-c) == k) {
                finish(sb, x, y, r, c);
                return;
            }
        }
    }
    public static void finish(StringBuilder sb, int x, int y, int r, int c) {
        if (r > x) {
            sb.append("d".repeat(r-x));
            
            if(y > c) {
                sb.append("l".repeat(y-c));
            }
            else {
                sb.append("r".repeat(c-y));
            }
        }
        else {
            if(y > c) {
                sb.append("l".repeat(y-c));
            }
            else {
                sb.append("r".repeat(c-y));
            }
            sb.append("u".repeat(x-r));
        }
        return;
    }
}