#include<iostream>

using namespace std;

const int MAX = 100'000;
int head[MAX+1], nxt[2*MAX+1], to[2*MAX+1], wei[2*MAX+1];
int start_idx = 1;
int p[MAX+1][20], d[MAX+1], q[MAX+1];
long long W[MAX+1][20];

void build_edge(int u, int v, int w) {
    to[start_idx] = v;
    wei[start_idx] = w;
    if(head[u] == 0) nxt[start_idx] = -1;
    else nxt[start_idx] = head[u];
    head[u] = start_idx++;
}

void build_graph(int node) {
    d[node] = 1;

    int st = 0, ed = 0;
    q[ed++] = node;

    while(st < ed) {
        int cur_node = q[st++];
        int cur_d = d[cur_node];

        // cur_node에서 자기랑 연결된 모든 애들(visited되지 않은 -> d로 체크) 검사
        int cur_next = head[cur_node];

        while(cur_next != -1) {
            int cur_to = to[cur_next];
            int cur_wei = wei[cur_next];
            
            if(d[cur_to] == 0) {
                // cur_to의 부모 cur_node
                p[cur_to][0] = cur_node;
                // cur_to를 q에 넣기
                q[ed++] = cur_to;
                // d는 부모 + 1 (방문체크)
                d[cur_to] = cur_d + 1;
                // cur_to에서 부모까지의 비용도 계산
                W[cur_to][0] = cur_wei;
            }

            cur_next = nxt[cur_next];
        }
    }
}

int find_parent(int u, int v) {
    // v를 더 깊게
    if(d[u] > d[v]) {
        int temp = u;
        u = v;
        v = temp;
    }

    int diff = d[v] - d[u];

    int p_u = u;
    int p_v = v;

    for(int i=19; i>=0; i--) {
        int pow = 1<<i;
        if(pow <= diff) {
            p_v = p[p_v][i];
            diff -= pow;
        }
    }

    if(p_u == p_v) return p_u;

    for(int i=19; i>=0; i--) {
        if(p[p_u][i] != 0 && p[p_u][i] != p[p_v][i]) {
            p_u = p[p_u][i];
            p_v = p[p_v][i];
        }
    }

    return p[p_u][0];
}

long long p_to_c(int parent, int child) {
    int diff = d[child] - d[parent];

    long long answer = 0L;

    int p_child = child;
    for(int i=19; i>=0; i--) {
        int pow = 1 << i;
        if(pow <= diff) {
            answer += W[p_child][i];
            p_child = p[p_child][i];
            diff -= pow;
        }
    }

    return answer;
}

long long find_cost(int u, int v) {
    int parent = find_parent(u, v);

    return p_to_c(parent, u) + p_to_c(parent, v);
}

int find_k(int u, int v, int k) {
    int parent = find_parent(u, v);

    int u_to_p = d[u] - d[parent];
    int v_to_p = d[v] - d[parent];
    int total_node = u_to_p + v_to_p + 1;
    if(k == 1) return u;
    else if(k == total_node) return v; 
    // u -> parent로 가는 경우
    if(k <= u_to_p + 1) {
        int p_u = u;
        k -= 1;
        for(int i=19; i>=0; i--) {
            int pow = 1 << i;
            if(pow <= k) {
                p_u = p[p_u][i];
                k -= pow;
            }
        }
        return p_u;
    }

    // u -> parent -> v 로 가는경우
    else {
        k = total_node - k;
        int p_v = v;
        for(int i=19; i>=0; i--) {
            int pow = 1 << i;
            if(pow <= k) {
                p_v = p[p_v][i];
                k -= pow;
            }
        }
        return p_v;
    }
}


void build_lca() {
    for(int i=1; i<20; i++) {
        for(int j=0; j<=MAX; j++) {
            p[j][i] = p[p[j][i-1]][i-1];
        }
    }
    for(int i=1; i<20; i++) {
        for(int j=0; j<=MAX; j++) {
            W[j][i] = W[p[j][i-1]][i-1] + W[j][i-1];
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int N, M;
    int u, v, w, k, op;

    cin >> N;

    for(int i=0; i<N-1; i++) {
        cin >> u >> v >> w;

        build_edge(u, v, w);
        build_edge(v, u, w);
    }

    build_graph(1);
    build_lca();

    cin >> M;

    for(int i=0; i<M; i++) {
        cin >> op >> u >> v;

        if(op == 1) {
            cout << find_cost(u, v) << "\n";
        }
        else if(op == 2) {
            cin >> k;
            cout << find_k(u, v, k) << "\n";
        }
    }
}