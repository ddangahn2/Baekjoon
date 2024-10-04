import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=1; i<=N; i++){
            arr.add(i);
        }
        StringBuilder sb = new StringBuilder("<");
        boolean flag = true;

        int idx = -1;
        while(arr.size() != 0){
            idx += K;
            idx %= arr.size();
            if(idx < 0) idx += arr.size();

            int removed = arr.remove(idx);
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