import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for(int i=0; i<N+1; i++){
            parent[i] = i;
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(op == 0) {
                // a, b 합치기
                union(a, b);
            }
            else if(op == 1) {
                // a, b 같은 집합인지
                if(find(a) == find(b)) sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
        }
        System.out.print(sb);
    }

    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        parent[pa] = pa;
        parent[pb] = pa;
    }

    public static int find(int a) {
        if(a == parent[a]) return a;
        else return parent[a] = find(parent[a]);
    }
}