import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine();

        int[][][] board = new int[N][M-7][2];
        int answer = 2500;

        for(int i=0; i<N; i++){
            String line = sc.nextLine();
            int BW = 0;
            int WB = 0;
            for(int j=M-1; j>=M-8; j--){
                if((i+j) % 2 == 0) {
                    if(line.charAt(j) == 'W') BW++;
                    else WB++;
                }
                else{
                    if(line.charAt(j) == 'W') WB++;
                    else BW++;
                }
            }
            board[i][M-8][0] = WB;
            board[i][M-8][1] = BW;

            for(int j=M-9; j>=0; j--){
                if((i+j) % 2 == 0){
                    if(line.charAt(j+8) == 'W') BW--;
                    else WB--;

                    if(line.charAt(j) == 'W') BW++;
                    else WB++;
                }
                else{
                    if(line.charAt(j+8) == 'W') WB--;
                    else BW--;

                    if(line.charAt(j) == 'W') WB++;
                    else BW++;
                }
                board[i][j][0] = WB;
                board[i][j][1] = BW;
            }
        }

        for(int j=0; j<M-7; j++){
            int WBsum = 0;
            int BWsum = 0;

            for(int i=0; i<8; i++){
                WBsum += board[i][j][0];
                BWsum += board[i][j][1];
            }
            int min = Math.min(WBsum, BWsum);
            answer = Math.min(answer, min);

            for(int i=8; i<N; i++){
                WBsum -= board[i-8][j][0];
                BWsum -= board[i-8][j][1];

                WBsum += board[i][j][0];
                BWsum += board[i][j][1];

                min = Math.min(WBsum, BWsum);
                answer = Math.min(answer, min);
            }
        }
        System.out.println(answer);
    }
}