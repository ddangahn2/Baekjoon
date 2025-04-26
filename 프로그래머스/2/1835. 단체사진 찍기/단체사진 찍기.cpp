#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int answer = 0;

bool check(string current, vector<string> data) {
    for(string d : data) {
        char first = d[0];
        char second = d[2];
        char cond = d[3];
        int num = d[4] - '0';
        
        int first_pos = current.find(first);
        int second_pos = current.find(second);
        int temp;
        
        // first_pos < second_pos
        if(first_pos > second_pos) {
            temp = first_pos;
            first_pos = second_pos;
            second_pos = temp;
        }
        
        int dist = second_pos - first_pos - 1;
        
        if(cond == '=' && dist != num) {
            return false;
        } 
        else if(cond == '<' && dist >= num) {
            return false;
        }
        else if(cond == '>' && dist <= num) {
            return false;
        }
    }
    return true;
}

void fact(vector<string>& kakao, string current, vector<bool>& visited, int depth, vector<string>& data) {
    if(depth == kakao.size()) {
        if(check(current, data)) {
            answer++;
        }
        return;
    }
    
    for(int i=0; i<kakao.size(); i++) {
        if(visited[i]) continue;
        visited[i] = true;
        fact(kakao, current + kakao[i], visited, depth + 1, data);
        visited[i] = false;
    }
}

// 전역 변수를 정의할 경우 함수 내에 초기화 코드를 꼭 작성해주세요.
int solution(int n, vector<string> data) {
    answer = 0;
    vector<string> kakao = {"A", "C", "F", "J", "M", "N", "R", "T"};
    string current;
    vector<bool> visited(8, false);
    // 1. 8! => 50,000
    fact(kakao, current, visited, 0, data);
    // 2. 이후 각 조건 만족하는지 확인 
    
    return answer;
}

