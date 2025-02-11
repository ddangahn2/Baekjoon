import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, Set<Integer>> map = new HashMap<>();

        int[] point = new int[N+1];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            point[B]++;

            Set<Integer> getSet = map.getOrDefault(A, new HashSet<>());
            getSet.add(B);
            map.put(A, getSet);
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i=1; i<=N; i++) {
            if(point[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int front = q.remove();
            sb.append(front).append(" ");

            Set<Integer> getAdj = map.get(front);

            if(getAdj == null) continue;;

            for(int adj : getAdj) {
                point[adj]--;

                if(point[adj] == 0) {
                    q.add(adj);
                }
            }
        }
        System.out.println(sb);
    }
}


