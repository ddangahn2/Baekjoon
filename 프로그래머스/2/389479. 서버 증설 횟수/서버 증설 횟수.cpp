#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(vector<int> players, int m, int k) {
    int answer = 0;
    
    vector<int> v(24, 0);
    vector<int> v2(24, 0);
    for(int i=0; i<24; i++) {
        v[i] = players[i] / m;
    }
    
    for(int i=0; i<24; i++) {
        if(v2[i] < v[i]) {
            int sub = v[i] - v2[i];
            answer += sub;
            
            for(int j = i; j < i+k; j++) {
                if(j >= 24) break;
                v2[j] += sub;
            }
        }
    }
    
    return answer;
}