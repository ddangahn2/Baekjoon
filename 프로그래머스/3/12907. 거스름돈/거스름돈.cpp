#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

int solution(int n, vector<int> money) {
    int answer = 0;
    
    sort(money.begin(), money.end());
    
    vector<int> dp(n+1, 0);
    
    for(int mo : money) {
        for(int i=1; i<=n; i++) {
            if(i < mo) continue;
            if(i == mo) dp[i] += 1;
            
            dp[i] += dp[i-mo];
        }    
    }
    
    
    return dp[n];
}