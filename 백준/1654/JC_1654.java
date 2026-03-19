import java.util.*;

class Main {

    static int K;
    static int N;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] split = scanner.nextLine().split(" ");
        K = Integer.parseInt(split[0]);
        N = Integer.parseInt(split[1]);

        long[] lines = new long[K];
        for (int i = 0; i < K; i++) {
            lines[i] = scanner.nextInt();;
            scanner.nextLine();
        }

        long lo = 1;
        long hi = Arrays.stream(lines).max().orElse(0);
        long answer = 0;
        while (lo + 1 < hi) {
            long mid = (lo + hi) / 2;
            if (isPossible(mid, lines)) {
                lo = mid;
                answer = mid;
            } else {
                hi = mid - 1;
                answer = mid - 1;
            }
        }

        if (isPossible(lo, lines)) answer = lo;
        if (isPossible(hi, lines)) answer = hi;

        System.out.println(answer);
    }

    private static boolean isPossible(long mid, long[] lines) {
        long sum = 0;

        for (long line : lines) {
            sum += line / mid;
        }

        return sum >= N;
    }
}
