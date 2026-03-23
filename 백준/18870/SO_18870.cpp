#pragma GCC optimize("O3")
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")

#include <bits/stdc++.h>

using namespace std;

#define MOD 10007
#define ll long long
#define INF 1e9
#define sz 1000001

void solve() {
    int n;
    cin >> n;

    vector<int> vec(n);
    for (int i = 0; i < n; i++) {
        cin >> vec[i];
    }
    vector<int> vec2(vec);
    sort(vec2.begin(), vec2.end());
    vec2.erase(unique(vec2.begin(), vec2.end()), vec2.end());
    for (int i = 0; i < n; i++) {
        int idx = lower_bound(vec2.begin(), vec2.end(), vec[i]) - vec2.begin();
        cout << idx << ' ';
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr);
    solve();
}