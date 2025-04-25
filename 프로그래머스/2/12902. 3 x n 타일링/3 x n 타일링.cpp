#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    int answer = 0;
    vector<int> v1;
    vector<int> v2;
    
    v1.push_back(3);
    v1.push_back(11);
    v2.push_back(1);
    v2.push_back(4);
    
    for(int i=2; i<=n/2; i++) {
        int temp_v1 = 0;
        int temp_v2 = 0;
        temp_v2 += v1[i-1];
        temp_v2 += v2[i-1];
        temp_v2 %= 1000000007;
        v2.push_back(temp_v2);
        
        temp_v1 = 2 * temp_v2;
        temp_v1 %= 1000000007;
        temp_v1 += v1[i-1];
        temp_v1 %= 1000000007;
        v1.push_back(temp_v1);
    }
    
    return v1[n/2 - 1];
}