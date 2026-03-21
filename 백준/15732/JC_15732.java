import java.io.*;

class Main {

    static long N;
    static long K;
    static long D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        N = Long.parseLong(split[0]);
        K = Long.parseLong(split[1]);
        D = Long.parseLong(split[2]);

        long[][] rules = new long[(int) K][3];
        for (int i = 0; i < K; i++) {
            String[] split1 = br.readLine().split(" ");
            long A = Long.parseLong(split1[0]);
            long B = Long.parseLong(split1[1]);
            long C = Long.parseLong(split1[2]);

            rules[i] = new long[]{A, B, C};
        }

        long lo = 1;
        long hi = N;
        while (lo + 1 < hi) {
            long mid = lo + (hi - lo) / 2;
            if (isPossible(rules, mid)) hi = mid;
            else lo = mid + 1;
        }

        long answer = 0;
        if (isPossible(rules, hi)) answer = hi;
        if (isPossible(rules, lo)) answer = lo;

        System.out.println(answer);
    }

    private static boolean isPossible(long[][] rules, long mid) {
        long dotori = 0;

        for (int i = 0; i < K; i++) {
            long A = rules[i][0];
            long B = rules[i][1];
            long C = rules[i][2];

            if (A <= mid) {
                if (mid <= B) {
                    dotori += (long) Math.ceil((double) (mid - A + 1) / C);
                } else {
                    dotori += (long) Math.ceil((double) (B - A + 1) / C);
                }
            }
        }

        return dotori >= D;
    }
}
