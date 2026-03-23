#pragma GCC optimize("O3")
#pragma GCC optimize("Ofast")
#pragma GCC optimize("unroll-loops")

#include <bits/stdc++.h>

using namespace std;

#define MOD 10007
#define ll long long
#define INF 1e9
#define sz 501

int n, m, t;
int board[sz][sz];
int tempBoard[sz][sz];
vector<pair<int, int>> airP;// y x

int dy[4] = { -1,1,0,0 };
int dx[4] = { 0,0,-1,1 };

void spread() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			tempBoard[i][j] = 0;
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (board[i][j] == -1 || board[i][j] == 0) continue;
			int v = board[i][j] / 5;

			int cnt = 0;
			for (int k = 0; k < 4; k++) {
				int y = i + dy[k];
				int x = j + dx[k];

				if (y >= 0 && y < n && x >= 0 && x < m && board[y][x] != -1) {
					tempBoard[y][x] += v;
					tempBoard[i][j] -= v;
				}
			}
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			board[i][j] += tempBoard[i][j];
		}
	}
}

void airRun() {
	int y = airP[0].first, x = airP[0].second;

	int prev = 0;
	for (x++; x < m; x++) {
		int v = board[y][x];
		board[y][x] = prev;
		prev = v;
	}
	x--;
	for (y--; y >= 0; y--) {
		int v = board[y][x];
		board[y][x] = prev;
		prev = v;
	}
	y++;
	for (x--; x >= 0; x--) {
		int v = board[y][x];
		board[y][x] = prev;
		prev = v;
	}
	x++;
	for (y++; y < n; y++) {
		if (board[y][x] == -1) break;
		int v = board[y][x];
		board[y][x] = prev;
		prev = v;
	}

	prev = 0;
	y = airP[1].first, x = airP[1].second;
	for (x++; x < m; x++) {
		int v = board[y][x];
		board[y][x] = prev;
		prev = v;
	}
	x--;
	for (y++; y < n; y++) {
		int v = board[y][x];
		board[y][x] = prev;
		prev = v;
	}
	y--;
	for (x--; x >= 0; x--) {
		int v = board[y][x];
		board[y][x] = prev;
		prev = v;
	}
	x++;
	for (y--; y >= 0; y--) {
		if (board[y][x] == -1) break;
		int v = board[y][x];
		board[y][x] = prev;
		prev = v;
	}
}

void print() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cout << board[i][j] << ' ';
		}
		cout << '\n';
	}
}

void solve() {
	cin >> n >> m >> t;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> board[i][j];
			if (board[i][j] == -1) {
				airP.push_back({ i,j });
			}
		}
	}

	for (int i = 1; i <= t; i++) {
		spread();
		airRun();
	}
	int ans = 2;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			ans += board[i][j];
		}
	}
	cout << ans << '\n';
}

int main() {
	ios::sync_with_stdio(false); cin.tie(nullptr);
	solve();
}