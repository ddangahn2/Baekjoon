import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        sc.nextLine();
        for(int i=0; i<tc; i++){
            String str = sc.nextLine();

            findPw(str);
        }
    }
    public static void findPw(String str){
        LinkedList<Character> q = new LinkedList<>();
        int cursor = 0;

        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);

            if(ch == '<'){
                if(cursor == 0){
                    continue;
                }
                else{
                    cursor -= 1;
                }
            }
            else if(ch == '>'){
                if(cursor == q.size()){
                    continue;
                }
                else{
                    cursor += 1;
                }
            }
            else if(ch == '-'){
                if(cursor == 0){
                    continue;
                }
                else{
                    cursor -= 1;
                    q.remove(cursor);
                }
            }
            else{
                q.add(cursor, ch);
                cursor += 1;
            }
        }

        StringBuilder sb = new StringBuilder("");
        for(Character c: q){
            sb.append(c);
        }

        System.out.println(sb.toString());
    }
}