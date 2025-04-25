#include <string>
#include <vector>
using namespace std;

// 주어진 숫자 n의 최대 약수를 찾는 함수
int maxDivisor(long long n) {
    // n이 1인 경우 약수가 없으므로 0을 반환
    if (n == 1) return 0;

    int result = 1; // 최대 약수를 저장할 변수, 초기값은 1

    // 2부터 n의 제곱근까지 반복
    for (long long i = 2; i * i <= n; i++) {
        // i가 n의 약수인지 확인
        if (n % i == 0) {
            long long div1 = i; // 약수 i
            long long div2 = n / i; // 약수 n / i

            // div2가 10,000,000 이하일 경우 div2를 반환
            if (div2 <= 10000000)
                return (int)div2;

            // div1이 10,000,000 이하일 경우 최대 약수 업데이트
            if (div1 <= 10000000)
                result = max(result, (int)div1);
        }
    }

    // 찾은 최대 약수 반환
    return result;
}

// 주어진 범위(begin ~ end) 내의 각 숫자에 대해 최대 약수를 찾는 함수
vector<int> solution(long long begin, long long end) {
    vector<int> answer; // 결과를 저장할 벡터

    // begin부터 end까지 반복
    for (long long i = begin; i <= end; i++) {
        // 각 숫자에 대해 최대 약수를 찾아서 answer에 추가
        answer.push_back(maxDivisor(i));
    }

    // 결과 반환
    return answer;
}