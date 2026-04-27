#pragma GCC optimize("O3")
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")

#include <bits/stdc++.h>

using namespace std;

#define MOD 10007
#define ll long long
#define INF 1e9
#define sz 20001

vector<string> arr;

void solve() {
    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        string str;
        cin >> str;
        arr.push_back(str);
    }
    sort(arr.begin(),arr.end(), [](string a, string b) {
        if (a.size() == b.size()) {
            return a < b;
        }
        return a.size() < b.size();
        });
    arr.erase(unique(arr.begin(), arr.end()), arr.end());

    for (int i = 0; i < arr.size(); i++) {
        cout << arr[i] << '\n';
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr);
    solve();
}