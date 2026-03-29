import java.io.*;
import java.util.*;

public class Main {

    private static final int MAX = 100_000;
    private static int N;
    private static int K;
    private static boolean[] visited = new boolean[MAX + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(bfs(N, K));
    }

    private static int bfs(int n, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(n);
        visited[n] = true;

        int time = 0;

        while (!deque.isEmpty()) {
            int size = deque.size();

            for (int i = 0; i < size; i++) {
                int cur = deque.poll();

                if (cur == k) {
                    return time;
                }

                int[] next = {cur - 1, cur + 1, cur * 2};

                for (int nx : next) {
                    if (nx >= 0 && nx <= MAX && !visited[nx]) {
                        visited[nx] = true;
                        deque.offer(nx);
                    }
                }
            }
            time++;
        }

        return -1;
    }
}
