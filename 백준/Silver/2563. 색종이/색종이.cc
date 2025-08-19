#include<iostream>

using namespace std;

int main() {
    bool arr[100][100] = {false};

    int papers;

    cin >> papers;

    for(int i=0; i<papers; i++) {
        int x, y;
        
        cin >> x >> y;

        for(int i=x; i<x+10; i++) {
            for(int j=y; j<y+10; j++) {
                arr[i][j] = true;
            }
        }
    }

    int total = 0;

    for(int i=0; i<100; i++) {
        for(int j=0; j<100; j++) {
            if(arr[i][j] == true) {
                total++;
            }
        }
    }

    cout << total;
}