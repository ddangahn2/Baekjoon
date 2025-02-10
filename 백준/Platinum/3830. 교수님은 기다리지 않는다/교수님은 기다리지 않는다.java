import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 부모
    public static int[] parent;
    // weight는 부모와의 차
    public static int[] parentWeight;

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            parent = new int[N];
            parentWeight = new int[N];
            for(int i=0; i<N; i++) parent[i] = i;

            if(N == 0 && M == 0) break;

            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());

                if("!".equals(st.nextToken())) {
                    int a = Integer.parseInt(st.nextToken()) - 1;
                    int b = Integer.parseInt(st.nextToken()) - 1;
                    int w = Integer.parseInt(st.nextToken());

                    union(a, b, w);
                }
                else {
                    int a = Integer.parseInt(st.nextToken()) - 1;
                    int b = Integer.parseInt(st.nextToken()) - 1;

                    question(a, b);
                }
//                for(int ii: parent) {
//                    System.out.print(ii + " ");
//                }
//                System.out.println();
//                for(int ii: parentWeight) {
//                    System.out.print(ii + " ");
//                }
//                System.out.println();
            }
        }
        System.out.print(sb);
    }

    public static void union(int a, int b, int w){
        int pa = find(a);
        int pb = find(b);

        if(pa == pb) return;

        int pbMinusB = parentWeight[b];
        int paMinusA = parentWeight[a];

        parent[pa] = pb;
        parentWeight[pa] = - paMinusA + pbMinusB + w;
    }

    public static int find(int a) {
        if(parent[a] == a) return a;

        int p = find(parent[a]);
        parentWeight[a] += parentWeight[parent[a]];

        return parent[a] = p;
    }

    public static void question(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        int pbMinusB = parentWeight[b];
        int paMinusA = parentWeight[a];

        if(pa != pb) sb.append("UNKNOWN").append("\n");
        else sb.append(paMinusA - pbMinusB).append("\n");
    }
}