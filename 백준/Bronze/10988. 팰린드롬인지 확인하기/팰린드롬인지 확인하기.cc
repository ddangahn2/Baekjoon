#include<iostream>

using namespace std;

int main() {
    string str;

    cin >> str;

    int len;
    bool flag = true;

    len = str.length();

    for(int i=0; i<len/2; i++) {
        if(str[i] != str[len-1-i]) {
            flag = false;
            break;
        }
    }

    if(flag) {
        cout << 1;
    } else {
        cout << 0;
    }
}