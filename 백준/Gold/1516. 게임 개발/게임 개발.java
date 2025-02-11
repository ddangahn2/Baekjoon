import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayList[] adjList = new ArrayList[N+1];
        PriorityQueue[] pq = new PriorityQueue[N+1];
        int[] degree = new int[N+1];
        int[] time = new int[N+1];

        for(int i=0; i<=N; i++) {
            adjList[i] = new ArrayList<>();
            pq[i] = new PriorityQueue(Comparator.reverseOrder());
        }

        for(int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            time[i] = Integer.parseInt(st.nextToken());

            while(st.hasMoreTokens()) {
                int build = Integer.parseInt(st.nextToken());
                if(build == -1) break;

                degree[i]++;
                adjList[build].add(i);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=N; i++) {
            if(degree[i] == 0) {
                q.add(i);
                pq[i].add(time[i]);
            }
        }

        while(!q.isEmpty()) {
            int node = q.remove();

            ArrayList<Integer> adjs = adjList[node];

            for(int adj : adjs) {
                degree[adj]--;

                int beforeBuildTime = (int) pq[node].peek();
                pq[adj].add(beforeBuildTime + time[adj]);

                if(degree[adj] == 0) {
                    q.add(adj);
                }
            }
        }

        for(int i=1; i<=N; i++) {
            System.out.println(pq[i].peek());
        }
    }
}


