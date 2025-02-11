import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int[] indexTree;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        Integer[] sortedArr = new Integer[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sortedArr[i] = arr[i];
        }
        Arrays.sort(sortedArr, Collections.reverseOrder());

        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<N; i++) {
            map.put(sortedArr[i], i);
        }

        int base = 1;
        while(base < N) {
            base <<= 1;
        }

        indexTree = new int[base * 2];

        for(int i=0; i<N; i++){
            int idx = map.get(arr[i]);

            makeTree(base + idx);

            getIdx(base, base + idx);
        }

        System.out.println(sb);
    }
    public static void getIdx(int left, int right) {
        int rank = 0;

        while(left <= right) {
            if(left % 2 == 1) {
                rank += indexTree[left];
                left++;
            }
            if(right % 2 == 0) {
                rank += indexTree[right];
                right--;
            }
            left /= 2;
            right /= 2;
        }

        sb.append(rank).append("\n");
    }
    public static void makeTree(int a) {
        while(a > 0) {
            indexTree[a] += 1;
            a /= 2;
        }
    }
}