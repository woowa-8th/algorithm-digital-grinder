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

string cogwheel[4];
int isrot[4]; // -1,0,1

void solve() {
    for (int i = 0; i < 4; i++) {
        cin >> cogwheel[i];
    }

    int k;
    cin >> k;
    for (int p = 0; p < k; p++) {
        int widx, rot;
        cin >> widx >> rot;
        widx--;

        int isrot[4] = { 0, };
        isrot[widx] = rot;

        int curRot = rot;
        for (int i = widx + 1; i < 4; i++) {
            char prevSN = cogwheel[i - 1][2];
            char curSN = cogwheel[i][6];

            if (prevSN == curSN) break;

            curRot = curRot == 1 ? -1 : 1;
            isrot[i] = curRot;
        }

        curRot = rot;
        for (int i = widx - 1; i >= 0; i--) {
            char prevSN = cogwheel[i + 1][6];
            char curSN = cogwheel[i][2];

            if (prevSN == curSN) break;

            curRot = curRot == 1 ? -1 : 1;
            isrot[i] = curRot;
        }

        for (int i = 0; i < 4; i++) {
            if (isrot[i] == 0) continue;
            if (isrot[i] == 1) {
                char ch = cogwheel[i].back();
                cogwheel[i] = ch + cogwheel[i].substr(0,7);
            }
            else {
                char ch = cogwheel[i].front();
                cogwheel[i] = cogwheel[i].substr(1) + ch;
            }
        }
    }

    int ans = 0;
    if (cogwheel[0][0] == '1') ans += 1;
    if (cogwheel[1][0] == '1') ans += 2;
    if (cogwheel[2][0] == '1') ans += 4;
    if (cogwheel[3][0] == '1') ans += 8;

    cout << ans;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr);
    solve();
}