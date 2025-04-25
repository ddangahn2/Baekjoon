#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
    int answer = 1000000000;
    
    // 플로이드 워셜
    // a -> b, a -> i -> b
    
    vector<vector<int>> floid(n, vector<int>(n, 100000000));
    
    for(int i=0; i<n; i++) {
        floid[i][i] = 0;
    }
    
    for(vector<int> fare : fares) {
        int st = fare[0] - 1;
        int ed = fare[1] - 1;
        int cost = fare[2];
        
        if(floid[st][ed] > cost) {
            floid[st][ed] = cost;
        }
        
        if(floid[ed][st] > cost) {
            floid[ed][st] = cost;
        }
    }
    
    for(int k=0; k<n; k++) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(floid[i][j] > floid[i][k] + floid[k][j]) {
                    floid[i][j] = floid[i][k] + floid[k][j];
                }
            }
        }
    }
    
    for(int i=0; i<n; i++) {
        if(answer > floid[a-1][i] + floid[b-1][i] + floid[i][s-1]) {
            answer = floid[a-1][i] + floid[b-1][i] + floid[i][s-1];
        }
    }
    
    return answer;
}