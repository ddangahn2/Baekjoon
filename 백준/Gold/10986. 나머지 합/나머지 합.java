import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N은 나오는 다음 입력받는 개수, M은 나누는 수
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 2번째줄 입력받고 numbers에 넣어주기
        st = new StringTokenizer(br.readLine());

        long [] numbers = new long[N];
        for (int i=0; i<N; i++) {
            numbers[i] = Long.parseLong(st.nextToken());
        }

        // numbers 차례대로 더하면서 remain에 M으로 나눈 나머지 index에 1 추가
        int [] remain = new int[M];
        long remainSum = 0;
        long result = 0;

        for (int i=0; i<N; i++) {
            remainSum += numbers[i];
            long remainder = remainSum % M;

            if (remainder < 0) {
                remainder += M;
            }

            remain[(int) remainder]++;
        }

        long extractSum = 0;
        for (int i=0; i<N; i++) {
            result += remain[(int) extractSum];
            extractSum += numbers[i];
            extractSum %= M;

            if (extractSum < 0) {
                extractSum += M;
            }

            remain[(int) extractSum]--;
        }

        System.out.println(result);
    }
}