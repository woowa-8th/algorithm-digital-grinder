import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static int N;
    static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        C = Integer.parseInt(split[1]);

        int[] houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);

        int lo = 1;
        int hi = houses[N - 1];
        while (lo + 1 < hi) {
            int mid = (int) ((long) lo + (long) hi) / 2;
            if (isPossible(houses, mid)) lo = mid;
            else hi = mid - 1;
        }

        int answer = 0;
        if (isPossible(houses, lo)) answer = lo;
        if (isPossible(houses, hi)) answer = hi;

        System.out.println(answer);
    }

    private static boolean isPossible(int[] houses, long mid) {
        int count = 1;
        int cur = houses[0];
        for (int i = 1; i < N; i++) {
            if (houses[i] - cur >= mid) {
                count++;
                cur = houses[i];
            }
        }

        return count >= C;
    }
}
