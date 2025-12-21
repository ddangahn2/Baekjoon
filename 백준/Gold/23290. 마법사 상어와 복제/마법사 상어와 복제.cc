#include<iostream>

using namespace std;

// i,j의 격자에 있는 물고기 담는 배열
int fish[4][4][8];
int fishBck[4][4][8];

// 물고기 냄새 기록용
int board[4][4];

// 상어 위치
int sx, sy;
int maxFish = -1;
int finalMove[3];
int tempMove[3];

int dx[8] = {0, -1, -1, -1, 0, 1, 1, 1};
int dy[8] = {-1, -1, 0, 1, 1, 1, 0, -1};

void moveFish();
void backupAndClear();
void move();
void moveShark();
void reduceSmell();
int remainFish();
void makeFish();
void moveShark(int tempSx, int tempSy, int catchFish, int moveCount);
void realMoveShark();

void whereToMove(int &x, int &y, int &d);

int main() {
    ios_base::sync_with_stdio(false); 
    cin.tie(NULL);

    // 물고기수 M, 상어 마법 연습횟수 S
    int M, S;
    cin >> M >> S;
    // 물고기 M마리의 정보
    for(int i=0; i<M; i++) {
        int fx, fy, d;
        cin >> fx >> fy >> d;
        
        fish[fx-1][fy-1][d-1]++;
    }
    // 상어의 정보
    cin >> sx >> sy;
    sx -= 1;
    sy -= 1;

    for(int i=0; i<S; i++) {
        moveFish();

        maxFish = -1;
        finalMove[0] = finalMove[1] = finalMove[2] = -1;

        moveShark(sx, sy, 0, 0);

        realMoveShark();
        // cout << "shark x : " << sx << ", shark y : " << sy << endl;
        reduceSmell();
        // cout << "smell (2, 1) : " << board[2][1] << endl;
        makeFish();
    }
    cout << remainFish();
}

// 1번. 이동
void moveFish() {
    backupAndClear();
    move();

    // for(int i=0; i<4; i++) {
    //     for(int j=0; j<4; j++) {
    //         cout << "(" << i << ", " << j << ") : ";
    //         for(int k=0; k<8; k++) {
    //             cout << fish[i][j][k] << " ";
    //         }
    //         cout << endl;
    //     }
    // }
    // cout << "--------------------------" << endl;
}

// fishBck에 원본 넣고, fish 초기화
void backupAndClear() {
    for(int i=0; i<4; i++) {
        for(int j=0; j<4; j++) {
            for(int k=0; k<8; k++) {
                fishBck[i][j][k] = fish[i][j][k];
                fish[i][j][k] = 0;
            }
        }
    }
}

// 기존 물고기 이동
void move() {
    for(int i=0; i<4; i++) {
        for(int j=0; j<4; j++) {
            for(int k=0; k<8; k++) {
                int cx = i;
                int cy = j;
                int cd = k;

                int result = fishBck[cx][cy][cd];

                whereToMove(cx, cy, cd);
                fish[cx][cy][cd] += result;
            }
        }
    }
}

// 기존 x, y, d에서 이동 가능한 위치로 이동
void whereToMove(int &x, int &y, int &d) {
    bool movedFlag = false;

    for(int i=0; i<8; i++) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        if(nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
            if(board[nx][ny] == 0) {
                if(!(nx == sx && ny == sy)) {
                    movedFlag = true;
                    break;
                }
            }
        }
        // 반시계 방향으로 45도 이동
        d = (d+7) % 8;
    }
    if(movedFlag) {
        x += dx[d];
        y += dy[d];
    }
}

// 2. 상어 이동
bool sharkBoard[4][4];

// 위, 왼쪽, 아래, 오른쪽
int sdx[4] = {-1, 0, 1, 0};
int sdy[4] = {0, -1, 0, 1};

//  moveShark(sx, sy, 0, 0);
void moveShark(int tempSx, int tempSy, int catchFish, int moveCount) {
    if(moveCount == 3) {
        if(maxFish < catchFish) {
            maxFish = catchFish;
            for(int i=0; i<3; i++) {
                finalMove[i] = tempMove[i];
            }
        }
        return;
    }

    for(int i=0; i<4; i++) {
        int nx = tempSx + sdx[i];
        int ny = tempSy + sdy[i];

        if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;

        bool alreadyVisited = sharkBoard[nx][ny];
        
        int addFish = 0;
        if(!alreadyVisited) {
            for(int j=0; j<8; j++) {
                addFish += fish[nx][ny][j];
            }
        }
        
        tempMove[moveCount] = i;
        sharkBoard[nx][ny] = true;
        moveShark(nx, ny, catchFish + addFish, moveCount + 1);
        sharkBoard[nx][ny] = alreadyVisited;
    }
}

// finalMove대로 움직이면서 물고기가 있으면 체크
void realMoveShark() {
    for(int i=0; i<3; i++) {
        sx += sdx[finalMove[i]];
        sy += sdy[finalMove[i]];

        int tempFish = 0;
        for(int j=0; j<8; j++) {
            tempFish += fish[sx][sy][j];
            fish[sx][sy][j] = 0;
        }

        if(tempFish > 0) {
            board[sx][sy] = 3;
        }
    }
}

// 3. 냄새 줄이기
void reduceSmell() {
    for(int i=0; i<4; i++) {
        for(int j=0; j<4; j++) {
            if(board[i][j] == 0) continue;
            board[i][j]--;
        }
    }
}

// 4. 물고기 복제 (fishBck에 저장한 원본 물고기를 fish에 추가)
void makeFish() {
    for(int i=0; i<4; i++) {
        for(int j=0; j<4; j++) {
            for(int k=0; k<8; k++) {
                fish[i][j][k] += fishBck[i][j][k];
            }
        }
    }
}

// 남아있는 물고기 총합
int remainFish() {
    int result = 0;
    for(int i=0; i<4; i++) {
        for(int j=0; j<4; j++) {
            for(int k=0; k<8; k++) {
                result += fish[i][j][k];
            }
        }
    }
    return result;
}