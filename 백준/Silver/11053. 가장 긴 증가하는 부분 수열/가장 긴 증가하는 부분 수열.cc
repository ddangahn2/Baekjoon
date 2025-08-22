#include<iostream>

using namespace std;


int binary_search_upper(int arr[], int target, int arr_size) {
    // 0 ~ n까지 수중에 자신보다 큰 수중 가장 왼쪽의 수
    //  즉 본인의 upperbound

    int st = 0;
    int ed = arr_size;

    while(st < ed) {
        int mid = (st + ed) / 2;

        if(arr[mid] < target) {
            st = mid + 1;
        } else {
            ed = mid;
        }
    }
    return st;
}

int main() {

    int arr_size;
    cin >> arr_size;
    int arr[arr_size];

    for(int i=0; i<arr_size; i++) {
        arr[i] = 10000;
    }

    for(int i=0; i<arr_size; i++) {
        int temp;
        cin >> temp;

        int pos = binary_search_upper(arr, temp, arr_size);
        arr[pos] = temp;
    }

    int length = 0;
    for(int i=0; i<arr_size; i++) {
        if(arr[i] != 10000) {
            length++;
        }
    }

    cout << length;
}
