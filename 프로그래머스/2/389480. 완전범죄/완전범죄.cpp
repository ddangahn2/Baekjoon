#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(vector<vector<int>> info, int n, int m) {
    int info_size = info.size();
    
    vector<vector<vector<bool>>> v(n, vector<vector<bool>>(m, vector<bool>(info_size + 1)));
    v[0][0][0] = true;
    
    for(int k=0; k<info_size; k++) {
        int A = info[k][0];
        int B = info[k][1];
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(v[i][j][k] == true) {
                    if(i + A < n) {
                        v[i+A][j][k+1] = true;
                    }
                    if(j + B < m) {
                        v[i][j+B][k+1] = true;
                    }
                }
            }
        }
    }
    
    for(int i=0; i<n; i++) {
        for(int j=0; j<m; j++) {
            if(v[i][j][info_size] == true) {
                return i;
            }
        }
    }
    
    return -1;
}