import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = sc.nextInt();
        }

        int[] gcdLeft = new int[N];
        gcdLeft[0] = arr[0];
        for(int i=1; i<N; i++) {
            gcdLeft[i] = gcd(gcdLeft[i-1], arr[i]);
        }

        int[] gcdRight = new int[N];
        gcdRight[N-1] = arr[N-1];
        for(int i=N-2; i>=0; i--) {
            gcdRight[i] = gcd(gcdRight[i+1], arr[i]);
        }

        int target = 0;

        if(gcdLeft[N-2] < gcdRight[1]) {
            target = arr[0];
        }
        else {
            target = arr[N-1];
        }
        int ans = Math.max(gcdLeft[N-2], gcdRight[1]);

        for(int i=1; i<N-1; i++) {
            int gcdWithoutTarget = gcd(gcdLeft[i-1], gcdRight[i+1]);
            if(ans < gcdWithoutTarget) {
                ans = gcdWithoutTarget;
                target = arr[i];
            }
        }

        if(ans == gcdLeft[N-1]) {
            System.out.println(-1);
        }
        else {
            System.out.println(ans + " " + target);
        }
    }

    public static int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a%b);
    }
}
