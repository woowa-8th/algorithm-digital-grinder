import java.io.*;

class Main {

    static long N;
    static long K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Long.parseLong(br.readLine());
        K = Long.parseLong(br.readLine());

        long lo = 1;
        long hi = N * N;
        while (lo + 1 < hi) {
            long mid = lo + (hi - lo) / 2;
            if (isPossible(mid)) hi = mid;
            else lo = mid + 1;
        }

        long answer = 0;
        if (isPossible(hi)) answer = hi;
        if (isPossible(lo)) answer = lo;

        System.out.println(answer);
    }

    private static boolean isPossible(long mid) {
        long index = 0;
        for (long i = 1; i <= N; i++) {
            index += Math.min(mid / i, N);
        }
        return index >= K;
    }
}
