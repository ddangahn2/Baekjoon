import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        if(str.charAt(0) == '0' || str.charAt(0) == '+') System.out.println("No Money");
        else {
            StringTokenizer st = new StringTokenizer(str, "+");
            if(st.countTokens() != 2) System.out.println("No Money");
            else {
                String str1 = st.nextToken();
                String str2 = st.nextToken();

                boolean flag = false;

                for(char ch1: str1.toCharArray()){
                    if(ch1 - '0' < 0 || ch1 - '0' >= 10) {
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    for(char ch2: str2.toCharArray()){
                        if(ch2 - '0' < 0 || ch2 - '0' >= 10) {
                            flag = true;
                            break;
                        }
                    }
                }
                if(flag){
                    System.out.println("No Money");
                }
                else {
                    if (str1.equals(str2)) System.out.println("CUTE");
                    else System.out.println("No Money");
                }
            }
        }
    }
}