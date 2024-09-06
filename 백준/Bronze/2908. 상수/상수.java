import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str1 = st.nextToken();
        String str2 = st.nextToken();

        String reversedStr1 = new StringBuilder(str1).reverse().toString();
        String reversedStr2 = new StringBuilder(str2).reverse().toString();

        int n1 = Integer.parseInt(reversedStr1);
        int n2 = Integer.parseInt(reversedStr2);

        System.out.println(Math.max(n1, n2));
    }
}