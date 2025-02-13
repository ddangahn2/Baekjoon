import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static StringTokenizer st;
    public static StringBuilder sb = new StringBuilder();

    public static int V, E;
    public static int MIN = Integer.MAX_VALUE;

    // LCA
    public static int LOG = 17;
    public static boolean[] visited;
    public static int[] depth;
    public static int[][] parent;
    public static ArrayList<Adj>[] graph;
    public static int[][][] MAX;

    // MST
    public static Queue<Edge> pq;
    public static int[] p;
    public static ArrayList<Edge> mst = new ArrayList<>();
    public static ArrayList<Adj>[] mstGraph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // MST 설정
        pq = new PriorityQueue<>((a, b) -> {return a.cost - b.cost;});
        p = new int[V+1];
        for(int i=1; i<=V; i++) p[i] = i;

        mstGraph = new ArrayList[V+1];
        for(int i=0; i<V+1; i++) mstGraph[i] = new ArrayList<Adj>();

        // LCA 설정
        visited = new boolean[V+1];
        depth = new int[V+1];
        parent = new int[LOG][V+1];


        MAX = new int[LOG][V+1][2];

        for(int i=0; i<LOG; i++) {
            for(int j=0; j<V+1; j++) {
                Arrays.fill(MAX[i][j], -1);
            }
        }

        graph = new ArrayList[V+1];
        for(int i=0; i<V+1; i++) graph[i] = new ArrayList<Adj>();

        // 입력
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[n1].add(new Adj(n2, cost));
            graph[n2].add(new Adj(n1, cost));

            pq.add(new Edge(n1, n2, cost));
        }

        // MST
        int MSTcost = buildMST();

        if(MSTcost == -1) {
            System.out.println(-1);
        } else {
            buildDepth(1);
            buildLCA();

            for(int i=1; i<=V; i++) {
                for(Adj adj : graph[i]) {
                    get(i, adj.adj, adj.cost);

                }
            }

            System.out.println((MIN == Integer.MAX_VALUE) ? -1 : MSTcost + MIN);
        }
    }

    public static void get(int st, int ed, int cost) {
        int max = -1;
        int max2 = -1;

        // ed가 더 깊게
        if(depth[st] > depth[ed]) {
            int temp = st;
            st = ed;
            ed = temp;
        }

        // ed 끌어올리기
        for(int i=LOG-1; i>=0; i--) {
            if(depth[ed] - depth[st] >= (1 << i)) {
                int[] findMax = findMaxMax2(max, max2, MAX[i][ed][0], MAX[i][ed][1]);

                max = findMax[0];
                max2 = findMax[1];

                ed = parent[i][ed];
            }
        }
        
        if(st == ed) {
            if(cost - max > 0) {
                MIN = Math.min(MIN, cost - max);
            } else if(cost - max2 > 0 && max2 >= 0) {
                MIN = Math.min(MIN, cost - max2);
            }
            return;
        }

        // st, ed 끌어올리기
        for(int i=LOG-1; i>=0; i--) {
            if(parent[i][ed] != parent[i][st]) {
                int[] findMax = findMaxMax2(max, max2, MAX[i][ed][0], MAX[i][st][0], MAX[i][ed][1], MAX[i][st][1]);

                max = findMax[0];
                max2 = findMax[1];

                st = parent[i][st];
                ed = parent[i][ed];
            }
        }
        int[] findMax = findMaxMax2(max, max2, MAX[0][st][0], MAX[0][ed][0], MAX[0][st][1], MAX[0][ed][1]);

        max = findMax[0];
        max2 = findMax[1];

        if(cost - max > 0) {
            MIN = Math.min(MIN, cost - max);
        } else if(cost - max2 > 0 && max2 >= 0) {
            MIN = Math.min(MIN, cost - max2);
        }
    }

    public static void buildDepth(int root) {
        visited[root] = true;
        depth[root] = 1;

        Queue<Integer> mstq = new LinkedList<>();
        mstq.add(root);

        while(!mstq.isEmpty()) {
            int cur = mstq.remove();

            ArrayList<Adj> adjArr = mstGraph[cur];
            for(Adj adj : adjArr) {
                if(!visited[adj.adj]) {
                    visited[adj.adj] = true;
                    depth[adj.adj] = depth[cur] + 1;

                    mstq.add(adj.adj);

                    parent[0][adj.adj] = cur;
                    MAX[0][adj.adj][0] = adj.cost;

                }
            }
        }
    }

    public static void buildLCA() {
        for(int k=1; k<LOG; k++) {
            for(int v=1; v<=V; v++) {
                if (parent[k - 1][v] == 0) continue;
                parent[k][v] = parent[k-1][parent[k-1][v]];

                int[] findMax = findMaxMax2(MAX[k-1][v][0], MAX[k-1][v][1], MAX[k-1][parent[k-1][v]][0], MAX[k-1][parent[k-1][v]][1]);

                MAX[k][v][0] = findMax[0];
                MAX[k][v][1] = findMax[1];

            }
        }

    }

    public static int buildMST() {
        int MSTcost = 0, count = 0;

        while(!pq.isEmpty()) {
            Edge cur = pq.remove();

            if(find(cur.st) == find(cur.ed)) continue;
            union(cur.st, cur.ed);

            mstGraph[cur.st].add(new Adj(cur.ed, cur.cost));
            mstGraph[cur.ed].add(new Adj(cur.st, cur.cost));

            mst.add(cur);
            MSTcost += cur.cost;
            count++;
            if (count == V - 1) break;

        }

        if(mst.size() == V-1) return MSTcost;
        else return -1;
    }

    public static int find(int v) {
        if(v == p[v]) return v;
        return p[v] = find(p[v]);
    }

    public static void union(int v1, int v2) {
        int pv1 = find(v1);
        int pv2 = find(v2);

        if(pv1 == pv2) return;

        p[pv1] = pv2;
    }

    public static int[] findMaxMax2(int... nums) {
        int max = -1;
        int secondMax = -1;

        for(int num : nums) {
            if(num > max) {
                secondMax = max;
                max = num;
            } else if (num > secondMax && num < max) {
                secondMax = num;
            }
        }
        return new int[] {max, secondMax};
    }
}

class Edge {
    int st;
    int ed;
    int cost;

    public Edge(int st, int ed, int cost) {
        this.st = st;
        this.ed = ed;
        this.cost = cost;
    }
}
class Adj {
    int adj;
    int cost;

    public Adj(int adj, int cost) {
        this.adj = adj;
        this.cost = cost;
    }
}