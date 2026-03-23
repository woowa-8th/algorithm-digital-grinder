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

bool check[sz];

int digitSum(int num) {
    vector<int> vec;
    int ret = num;
    while (num) {
        ret += num % 10;
        num /= 10;
    }
    return ret;
}

void solve() {
    for (int i = 1; i <= 10000; i++) {
        if (!check[i]) cout << i << '\n';
        check[digitSum(i)] = true;
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr);
    solve();
}