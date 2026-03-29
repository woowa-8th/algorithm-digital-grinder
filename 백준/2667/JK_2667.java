import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        visited = new boolean[N][N];
        
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '1' && !visited[i][j]) {
                    result.add(dfs(i, j));
                }
            }
        }

        Collections.sort(result);

        System.out.println(result.size());
        for (int n : result) {
            System.out.println(n);
        }
    }

    static int dfs(int r, int c) {
        visited[r][c] = true;
        int count = 1;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
            if (visited[nr][nc] || map[nr][nc] == '0') continue;

            count += dfs(nr, nc);
        }

        return count;
    }
}
