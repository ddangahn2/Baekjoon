import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        int[][] combi = new int[N+M+1][N+M+1];
        for(int i=0; i<N+M+1; i++) {
            combi[i][0] = 1;
        }
        for(int i=0; i<N+M+1; i++) {
            combi[i][i] = 1;
        }

        for(int i=1; i<N+M+1; i++) {
            for(int j=1; j<N+M+1; j++) {
                int nCm = combi[i-1][j-1] + combi[i-1][j];

                if(nCm > 1000000000) {
                    combi[i][j] = 1000000001;
                }
                else {
                    combi[i][j] = nCm;
                }
            }
        }

        StringBuilder str = new StringBuilder();

        int aCount = N;
        int zCount = M;

        for(int i=0; i<N+M; i++) {
            int cases = combi[aCount + zCount - 1][zCount];

            if(cases >= K && aCount > 0) {
                str.append('a');
                aCount--;
            }
            else if(cases < K && zCount > 0) {
                str.append('z');
                K -= cases;
                zCount--;
            }
        }
        
        if(K != 1) {
            System.out.println(-1);
        } else {
            System.out.println(str);
        }
    }
}


