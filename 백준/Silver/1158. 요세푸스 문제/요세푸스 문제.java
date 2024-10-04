import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        LinkedList<Integer> ll = new LinkedList<>();
        for(int i=1; i<=N; i++){
            ll.add(i);
        }
        StringBuilder sb = new StringBuilder("<");
        boolean flag = true;

        int idx = -1;
        while(!ll.isEmpty()){
            idx += K;
            idx %= ll.size();
            if(idx < 0) idx += ll.size();

            int removed = ll.remove(idx);
            idx -= 1;
            if(flag){
                flag = false;
                sb.append(Integer.toString(removed));
            }
            else{
                sb.append(", " + Integer.toString(removed));
            }
        }
        sb.append(">");

        System.out.println(sb.toString());
    }
}