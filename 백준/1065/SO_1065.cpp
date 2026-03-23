#pragma GCC optimize("O3")
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")

#include <bits/stdc++.h>

#define ll long long int
#define MOD 1'000'000'007
#define INF 1000000000
#define sz 100001
#define all(v) v.begin(),v.end()

using namespace std;

bool check(int num) {
    vector<int> vec;
    while (num) {
        vec.push_back(num % 10);
        num /= 10;
    }
    if (vec.size() <= 2) return true;

    int diff = vec[1] - vec[0];
    for (int i = 2; i < vec.size(); i++) {
        if (diff != vec[i] - vec[i - 1]) return false;
    }
    return true;
}

void solve() {
    int n;
    cin >> n;

    int ans = 0;
    for (int i = 1; i <= n; i++) {
        if (check(i)) ans++;
    }
    cout << ans;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr);
    solve();
}