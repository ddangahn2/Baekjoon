#include<iostream>

using namespace std;

int N;
string map[6][6];

bool checkPossible() {
    int xpos[4] = {-1,1,0,0};
    int ypos[4] = {0,0,-1,1};

    for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++) {
            if(map[j][i] != "T") continue;

            for(int pos=0; pos<4; pos++) {
                int go = 1;
                while(true) {
                    int ipos = i + go * xpos[pos];
                    int jpos = j + go * ypos[pos];

                    if(ipos < 0 || ipos >= N || jpos < 0 || jpos >= N) break;
                    
                    if(map[jpos][ipos] == "O") break;
                    if(map[jpos][ipos] == "S") return false;

                    go++;
                }
            }
        }
    }
    return true;
}

void printMap() {
    cout << "--printMap--" << endl;
    for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++) {
            cout << map[j][i] << " ";
        }
        cout << endl;
    }
}

bool makeWall(int wallCount, int ypos, int xpos) {    
    int pos = N * ypos + xpos;

    if(wallCount == 3) {
        return checkPossible();
    }

    for(int i = pos; i < N * N; i++) {
        int x = i % N;
        int y = i / N;

        if(map[y][x] != "X") continue;
        
        // 벽 세운다 가정
        map[y][x] = "O";

        if (makeWall(wallCount + 1, x, y)) {
            return true;
        }

        // 원상복구
        map[y][x] = "X";
    }
    return false;
}

int main() {    
    cin >> N;

    // 가로
    for(int i=0; i<N; i++) {
        // 세로
        for(int j=0; j<N; j++) {
            cin >> map[j][i];
        }
    }

    // wall 개수, wall 시작x,y값 
    
    bool isPossible = false;

    for(int i=0; i<N*N; i++) {
        int x = i % N;
        int y = i / N;

        if(map[y][x] != "X") continue;

        if(makeWall(0, y, x)) {
            isPossible = true;
            break;
        }
    }

    if(isPossible) {
        cout << "YES";
    } else {
        cout << "NO";
    }
}