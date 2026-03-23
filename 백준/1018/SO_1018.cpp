#pragma GCC optimize("O3")
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")

#include <bits/stdc++.h>

#define ll long long int
#define MOD 1'000'000'007
#define INF 1e9
#define sz 51
#define all(v) v.begin(),v.end()

using namespace std;

int n, m;
char board[sz][sz];
char ch[2] = { 'B','W' };

int check(int y, int x) {
    if (y + 8 > n || x + 8 > m) return INF;

    int ret = 0;
    int idx = 0;
    for (int i = y; i < y + 8; i++) {
        for (int j = x; j < x + 8; j++) {
            if (ch[idx] != board[i][j]) ret++;
            idx++;
            idx %= 2;
        }
        idx++;
        idx %= 2;
    }

    int ret2 = 0;
    idx = 1;
    for (int i = y; i < y + 8; i++) {
        for (int j = x; j < x + 8; j++) {
            if (ch[idx] != board[i][j]) ret2++;
            idx++;
            idx %= 2;
        }
        idx++;
        idx %= 2;
    }
    return min(ret, ret2);
}

void solve() {
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> board[i][j];
        }
    }
    int ans = INF;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            ans = min(ans, check(i, j));
        }
    }
    cout << ans;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr);
    solve();
}