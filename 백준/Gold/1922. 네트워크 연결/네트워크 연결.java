import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        for(int i=1; i<=N; i++) {
            parent[i] = i;
        }

        Queue<Edge> pq = new PriorityQueue<>((a, b) -> {
            return a.price - b.price;
        });

        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            if(A == B) continue;

            if(A > B) {
                pq.add(new Edge(B, A, C));
            } else {
                pq.add(new Edge(A, B, C));
            }
        }
        int answer = 0;
        while(!pq.isEmpty()) {
            Edge edge = pq.remove();

            if(find(edge.st) == find(edge.ed)) continue;

            union(edge.st, edge.ed);

            answer += edge.price;
        }
        System.out.println(answer);
    }

    public static int find(int node) {
        if(parent[node] == node) return node;
        return parent[node] = find(parent[node]);
    }

    public static void union(int node1, int node2) {
        int pnode1 = find(node1);
        int pnode2 = find(node2);

        parent[pnode1] = pnode2;
    }
}

class Edge {
    int st;
    int ed;
    int price;

    public Edge(int st, int ed, int price) {
        this.st = st;
        this.ed = ed;
        this.price = price;
    }
}