#include <string>
#include <vector>
#include <map>
#include <iostream>

using namespace std;

string solution(vector<string> participant, vector<string> completion) {
    string answer = "";
    
    map<string, int> ma;
    
    for(string name : participant) {
        ma[name]++;
    }

    for(string name : completion) {
        ma[name]--;
    }
    
    for(auto it : ma) {
        if(it.second > 0) {
            return it.first;
        }
    }
}