#pragma GCC optimize("O3")
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")

#include <bits/stdc++.h>

using namespace std;

#define MOD 10007
#define ll long long
#define INF 1e9 + 1
#define sz 200001

int n, c;
int horizon[sz];

bool check(int mid) {
    int prev = horizon[0];
    int cnt = 1;
    for (int i = 1; i < n; i++) {
        if (horizon[i] - prev >= mid) {
            cnt++;
            prev = horizon[i];
        }
    }
    return cnt >= c;
}

void solve() {
    cin >> n >> c;
    for (int i = 0; i < n; i++) {
        cin >> horizon[i];
    }
    sort(horizon, horizon + n);

    int lo = 1;
    int hi = INF;
    while (lo + 1 < hi) {
        int mid = (lo + hi) / 2;
        if (check(mid)) lo = mid;
        else hi = mid;
    }
    cout << lo;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr);
    solve();
}