#pragma GCC optimize("O3")
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")

#include <bits/stdc++.h>

#define ll long long int
#define MOD 1'000'000'007
#define INF 1e9
#define sz 1000001
#define all(v) v.begin(),v.end()

using namespace std;

ll n, k;

bool chk(int mid) {
    ll sum = 0;
    for (ll i = 1; i <= n; i++) {
        sum += min(mid / i, n);
    }
    return sum < k;
}

void solve() {
    cin >> n >> k;

    ll lo = 0;
    ll hi = INF + 1;
    while (lo + 1 < hi) {
        ll mid = (lo + hi) / 2;
        if (chk(mid)) lo = mid;
        else hi = mid;
    }
    cout << hi;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr);
    solve();
}