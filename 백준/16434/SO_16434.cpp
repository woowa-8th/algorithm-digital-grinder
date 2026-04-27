#pragma GCC optimize("O3")
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")

#include <bits/stdc++.h>

using namespace std;

#define MOD 10007
#define ll long long
#define INF 1e18
#define sz 123457

struct node { ll int t, a, h; };
node room[sz];
ll n, atk, cAtk;

bool check(ll mid) {
    ll maxHP = mid;
    ll curHP = mid;

    for (int i = 0; i < n; i++) {
        auto [t, a, h] = room[i];
        if (t == 1) {
            int v = h / cAtk;
            if (h % cAtk == 0) v--;
            curHP -= v * a;
            if (curHP <= 0) return false;
        }
        else {
            cAtk += a;
            curHP = min(curHP + h, maxHP);
        }
    }
    return true;
}

void solve() {
    cin >> n >> atk;
    for (int i = 0; i < n; i++) {
        ll t, a, h;
        cin >> t >> a >> h;
        room[i] = { t,a,h };
    }

    ll lo = 0;
    ll hi = INF;
    while (lo + 1 < hi) {
        cAtk = atk;
        ll mid = (lo + hi) / 2;
        if (check(mid)) hi = mid;
        else lo = mid;
    }

    cout << hi;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr);
    solve();
}