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
    public static ArrayList<int[]> isAp;

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
        isAp = new ArrayList<>();

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph[A].add(B);
            graph[B].add(A);
        }

        for(int i=1; i<=V; i++) {
            if(order[i] == 0) {
                dfs(i, 0);
            }
        }


        Collections.sort(isAp, (a,b) -> {
            if(a[0] == b[0]) {
                return a[1]-b[1];
            }
            return a[0]-b[0];
        });

        sb.append(isAp.size()).append("\n");
        for(int[] Ap : isAp) {
            sb.append(Ap[0]).append(" ").append(Ap[1]).append("\n");
        }
        System.out.print(sb);
    }

    public static int dfs(int idx, int parent) {
        order[idx] = orderNo++;
        int low = order[idx];

        for(int adj : graph[idx]) {

            if(adj == parent) continue;

            if(order[adj] == 0) {
                int subTreeLow = dfs(adj, idx);

                if(order[idx] < subTreeLow) {
                    if(adj < idx) {
                        isAp.add(new int[]{adj, idx});
                    }
                    else {
                        isAp.add(new int[]{idx, adj});
                    }
                }
                low = Math.min(subTreeLow, low);
            }
            else {
                low = Math.min(order[adj], low);
            }
        }
        return low;
    }
}