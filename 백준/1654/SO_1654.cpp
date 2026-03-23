#pragma GCC optimize("O3")
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")

#include <bits/stdc++.h>

using namespace std;

#define MOD 10007
#define ll long long
#define INF 1e9
#define sz 10001

ll k, n;
ll arr[sz];

bool check(ll len) {
    ll cnt = 0;
    for (int i = 0; i < k; i++) {
        cnt += arr[i] / len;
    }
    return cnt >= n;
}

void solve() {
    cin >> k >> n;
    for (int i = 0; i < k; i++) {
        cin >> arr[i];
    }

    ll lo = 1;
    ll hi = INT_MAX + 1L;
    while (lo + 1 < hi) {
        ll mid = (lo + hi) / 2;
        if (check(mid)) lo = mid;
        else hi = mid;
    }
    cout << lo;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr);
    solve();
}