import java.util.*;

public class Main {
    static int[][] studentClass;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        ArrayList<Integer> arr = new ArrayList<>();

        for(int i=0; i<N; i++) arr.add(sc.nextInt());
        Collections.sort(arr, Comparator.reverseOrder());
        arr.add(0);

        long[] sum = new long[arr.size()];
        sum[0] = arr.get(0);
        for(int i=1; i<arr.size(); i++){
            sum[i] = sum[i-1] + arr.get(i);
        }

        long tree = 0;
        int idx;

        for(idx=0; idx<arr.size(); idx++){
            tree = arr.get(idx);
            long cut = sum[idx] - tree * (idx+1);
            if(cut == M) {
                break;
            }
            else if(cut > M){
                tree += (cut - M) / (idx);
                break;
            }
        }
        System.out.println(tree);
    }
}