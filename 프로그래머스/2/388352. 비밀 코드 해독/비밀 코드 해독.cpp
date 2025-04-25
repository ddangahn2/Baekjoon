#include <string>
#include <vector>
#include <iostream>

using namespace std;

int answer = 0;

void check(vector<int> v, vector<vector<int>> q, vector<int> ans) {
    // vector 출력하기
    int total_correct = 0;
    for(int i=0; i<ans.size(); i++) {
        int local_correct = 0;
        
        vector<int> input_num = q[i];
        
        for(auto input : input_num) {
            for(auto ans_num : v) {
                if(input == ans_num) {
                    local_correct++;
                }
            }
        }
        
        if(local_correct == ans[i]) {
            total_correct++;
        }
    }
    
    if(total_correct == ans.size()) {
        answer++;
    }
}

void dfs(int n, int st, vector<int> v, vector<vector<int>> q, vector<int> ans) {
    if(v.size() == 5) {
        check(v, q, ans);
        return;
    }
    
    for(int i=st; i<=n; i++) {
        v.push_back(i);
        dfs(n, i + 1, v, q, ans);
        v.pop_back();
    }
}


int solution(int n, vector<vector<int>> q, vector<int> ans) {
    vector<int> v;
    
    // dfs를 통해 시스템 1부터 n까지중 5개를 뽑아내야함
    dfs(n, 1, v, q, ans);
    
    return answer;
}
