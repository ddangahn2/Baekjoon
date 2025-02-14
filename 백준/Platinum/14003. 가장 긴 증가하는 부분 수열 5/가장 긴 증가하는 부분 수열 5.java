import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static StringTokenizer st;
    public static StringBuilder sb = new StringBuilder();

    public static int[] board;
    public static int[] LIS;
    public static int[] trace;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        board = new int[st.countTokens()];
        LIS = new int[st.countTokens()];
        trace = new int[st.countTokens()];
        Arrays.fill(trace, -1);

        for(int i=0; i< board.length; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }

        int index = 0;

        LIS[0] = board[0];
        trace[0] = 0;

        for(int i=1; i<board.length; i++) {
            if(board[i] > LIS[index]) {
                index++;
                LIS[index] = board[i];
                trace[i] = index;
            }
            else {
                // 0 ~ index까지 board[i]보다 큰애 찾기.
                int idx = binarySearch(index, board[i]);

                LIS[idx] = board[i];
                trace[i] = idx;
            }
        }

//        for(int i : trace) {
//            System.out.print(i + " ");
//        }
//        System.out.println();

        System.out.println(index+1);
        Stack<Integer> stack = new Stack<>();
        for(int i= board.length-1; i>=0; i--) {
            if(trace[i] == index) {
                stack.add(board[i]);
                index--;
            }
        }
        while(!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }

    public static int binarySearch(int index, int target) {
        int st = 0;
        int ed = index;

        // 똑같은애 있으면 안넣어줌
        while(st < ed) {
            int mid = (st + ed) / 2;

            if(LIS[mid] == target) return mid;

            if(LIS[mid] > target) {
                ed = mid;
            }
            else {
                st = mid+1;
            }
        }
        return st;
    }
}