#pragma GCC optimize("O3")
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")

#include <bits/stdc++.h>

using namespace std;

#define MOD 10007
#define ll long long
#define INF 1e9
#define sz 501

int n, m;
int board[sz][sz];

// 상 하 좌 우 0 1 2 3

int shapes[15][3] = {
    {3,3,3}, {1,1,1},
    {3,1,2},
    {1,1,3}, {1,1,2}, {2,2,1}, {3,3,1}, {0,0,2}, {0,0,3}, {3,3,0}, {2,2,0},
    {1,3,1}, {1,2,1}, {2,1,2}, {3,1,3}
};
int dn[4][3] = {
    {2,3,1}, {2,3,0}, {0,1,3}, {0,1,2}
};

int dy[4] = { -1,1,0,0 };
int dx[4] = { 0,0,-1,1 };

int calcShapeSum(int y, int x) {
    int ret = 0;
    for (int i = 0; i < 15; i++) {
        int nx = x, ny = y;
        int sum = board[ny][nx];
        bool chk = true;
        for (int j = 0; j < 3; j++) {
            int idx = shapes[i][j];
            ny += dy[idx];
            nx += dx[idx];
            if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
                chk = false;
                break;
            }
            sum += board[ny][nx];
        }
        if (chk) ret = max(ret, sum);
    }
    return ret;
}

int calcDnSum(int y, int x) {
    int ret = 0;
    for (int i = 0; i < 4; i++) {
        int sum = board[y][x];
        bool chk = true;
        for (int j = 0; j < 3; j++) {
            int idx = dn[i][j];
            int nxtY = y + dy[idx];
            int nxtX = x + dx[idx];
            if (nxtY < 0 || nxtY >= n || nxtX < 0 || nxtX >= m) {
                chk = false;
                break;
            }
            sum += board[nxtY][nxtX];
        }
        if (chk) ret = max(ret, sum);
    }
    return ret;
}

void solve() {
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> board[i][j];
        }
    }

    int ans = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            ans = max(max(ans, calcShapeSum(i, j)), calcDnSum(i, j));
        }
    }
    cout << ans;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr);
    solve();
}