import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static StringTokenizer st;
    public static StringBuilder sb = new StringBuilder();

    public static int orderNo = 1;
    public static int[] order;

    public static ArrayList<Integer>[] graph;
    public static boolean[] isAp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V+1];
        for(int i=0; i<V+1; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        order = new int[V+1];
        isAp = new boolean[V+1];

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph[A].add(B);
            graph[B].add(A);
        }

        for(int i=1; i<=V; i++) {
            if(order[i] == 0) {
                dfs(i, true);
            }
        }
        int result = 0;
        for(int i=1; i<=V; i++) {
            if(isAp[i]) {
                result++;
                sb.append(i).append(" ");
            }
        }
        System.out.println(result);
        System.out.print(sb);
    }

    public static int dfs(int idx, boolean isRoot) {
        order[idx] = orderNo++;
        int low = order[idx];
        int child = 0;

        for(int adj : graph[idx]) {
            if(order[adj] == 0) {
                child++;
                int subTreeLow = dfs(adj, false);

                if(!isRoot && order[idx] <= subTreeLow) {
                    isAp[idx] = true;
                }
                low = Math.min(subTreeLow, low);
            }
            else {
                low = Math.min(order[adj], low);
            }
        }
        if(isRoot && child >= 2) {
            isAp[idx] = true;
        }

        return low;
    }
}