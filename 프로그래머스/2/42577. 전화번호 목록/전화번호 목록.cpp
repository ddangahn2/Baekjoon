#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool solution(vector<string> phone_book) {
    bool answer = true; // 기본적으로 true로 설정합니다.
    
    // 전화번호부를 정렬합니다.
    sort(phone_book.begin(), phone_book.end());
    
    // 인접한 전화번호를 비교합니다.
    for(int i = 0; i < phone_book.size() - 1; i++)
    {
        // 다음 번호가 현재 번호로 시작하는지 확인합니다.
        if(phone_book[i + 1].find(phone_book[i]) == 0)
            return false; // 접두사 관계가 있으면 false를 반환합니다.
    }
    
    return answer; // 접두사 관계가 없으면 true를 반환합니다.
}