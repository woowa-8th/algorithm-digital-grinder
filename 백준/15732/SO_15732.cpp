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

struct node { int a, b, c; };

int n, k, d;
node arr[sz];

bool chk(int mid) {
    ll sum = 0;
    for (int i = 0; i < k; i++) {
        auto [a, b, c] = arr[i];
        if (a > mid) break;
        int end = min(mid, b);
        sum += (end - a) / c + 1;
    }
    return sum < d;
}

void solve() {
    cin >> n >> k >> d;
    for (int i = 0; i < k; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        arr[i] = { a,b,c };
    }
    sort(arr, arr + k, [](node a, node b) {
        return a.a < b.a;
        });

    int lo = 0;
    int hi = n + 1;
    while (lo + 1 < hi) {
        int mid = (lo + hi) / 2;
        if (chk(mid)) lo = mid;
        else hi = mid;
    }
    cout << hi;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr);
    solve();
}