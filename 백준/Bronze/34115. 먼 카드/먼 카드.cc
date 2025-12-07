#include <iostream>
#include <stdlib.h>

using namespace std;

#define max(a, b) (((a) > (b)) ? (a) : (b))

int main() {
    int N;
    int input;
    int answer = 0;
    cin >> N;

    int* arr = new int[N+1]{};

    for(int i=1; i<=2*N; i++) {
        cin >> input;

        if(arr[input] == 0) arr[input] = i;
        else answer = max(answer, i - 1 - arr[input]);
    }

    cout << answer;
}