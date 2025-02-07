import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        boolean[] prime = new boolean[1000001];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;

        for(int i=2; i<=1000; i++) {
            if(prime[i]) {
                int j=2;
                while(i * j <= 1000000) {
                    prime[i*j] = false;
                    j++;
                }
            }
        }

        while(true) {
            int N = sc.nextInt();
            if(N == 0) break;

            boolean flag = true;
            for(int i=2; i<= N/2; i++) {
                if(prime[i] && prime[N-i]) {
                    System.out.println(N + " = " + i + " + " + (N-i));
                    flag = false;
                    break;
                }
            }
            if(flag) {
                System.out.println("Goldbach's conjecture is wrong.");
            }
        }
    }
}
