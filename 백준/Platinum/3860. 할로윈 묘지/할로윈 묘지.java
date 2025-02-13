import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static StringTokenizer st;
    public static StringBuilder sb = new StringBuilder();

    public static int[][] board;
    public static int X, Y, G, E;
    public static ArrayList<Edge>  edges;

    public static long[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            st = new StringTokenizer(br.readLine());

            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());

            if(X == 0 && Y == 0) break;

            board = new int[X][Y];
            int boardNode = X * Y;

            G = Integer.parseInt(br.readLine());
            for (int i = 0; i < G; i++) {
                st = new StringTokenizer(br.readLine());

                int X1 = Integer.parseInt(st.nextToken());
                int Y1 = Integer.parseInt(st.nextToken());

                board[X1][Y1] = -1;
                boardNode--;
            }

            edges = new ArrayList<>();

            E = Integer.parseInt(br.readLine());
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());

                int X1 = Integer.parseInt(st.nextToken());
                int Y1 = Integer.parseInt(st.nextToken());
                int X2 = Integer.parseInt(st.nextToken());
                int Y2 = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                board[X1][Y1] = 1;
                edges.add(new Edge(X1 * Y + Y1, X2 * Y + Y2, T));
            }

            makeEdges();

//            System.out.println();
//            for(int i=0; i<H; i++) {
//                for(int j=0; j<W; j++) {
//                    System.out.print(board[i][j] + " ");
//                }
//                System.out.println();
//            }

//            for(Edge edge : edges) {
//                System.out.println("(" + (edge.st / Y) + "," + (edge.st % Y) + ") -> (" + (edge.ed / Y) + "," + (edge.ed % Y) + ") : " + edge.cost);
//            }

            dist = new long[X * Y];
            Arrays.fill(dist, Long.MAX_VALUE);
            dist[0] = 0;

            for (int i = 1; i < boardNode; i++) {
                for (Edge edge : edges) {
                    if (dist[edge.st] == Long.MAX_VALUE) continue;

                    if (dist[edge.ed] > dist[edge.st] + edge.cost) {
                        dist[edge.ed] = dist[edge.st] + edge.cost;
                    }
                }
            }

            long answer = dist[X * Y -1];

            boolean cycle = false;
            for (Edge edge : edges) {
                if (dist[edge.st] == Long.MAX_VALUE) continue;

                if (dist[edge.ed] > dist[edge.st] + edge.cost) {
                    cycle = true;
                    break;
                }
            }

            if (cycle) {
                System.out.println("Never");
            } else {
                if (dist[X * Y - 1] == Long.MAX_VALUE) System.out.println("Impossible");
                else System.out.println(dist[X * Y - 1]);
            }
        }
    }

    public static void makeEdges() {
        for(int h = 0; h < X; h++) {
            for(int w = 0; w < Y -1; w++) {
                if(board[h][w] == -1 || board[h][w+1] == -1) continue;

                if(board[h][w] != 1) {
                    edges.add(new Edge(h * Y+w, h*Y+w+1, 1));
                }
                if(board[h][w+1] != 1) {
                    if(h == X -1 && w == Y -2) continue;
                    edges.add(new Edge(h * Y +w+1, h* Y +w, 1));
                }
            }
        }

        for(int w = 0; w < Y; w++) {
            for(int h = 0; h< X -1; h++) {
                if(board[h][w] == -1 || board[h+1][w] == -1) continue;

                if(board[h][w] != 1) {
                    edges.add(new Edge(h* Y +w, (h+1)* Y +w, 1));
                }
                if(board[h+1][w] != 1) {
                    if(h == X -2 && w == Y -1) continue;
                    edges.add(new Edge((h+1)* Y +w, h* Y +w, 1));
                }
            }
        }
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