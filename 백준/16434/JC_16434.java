import java.io.*;

class Main {

    static long N;
    static long H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        N = Long.parseLong(split[0]);
        H = Long.parseLong(split[1]);

        long[][] rooms = new long[(int) N][3];
        for (int i = 0; i < N; i++) {
            String[] split1 = br.readLine().split(" ");
            long t = Long.parseLong(split1[0]);
            long a = Long.parseLong(split1[1]);
            long h = Long.parseLong(split1[2]);

            rooms[i] = new long[]{t, a, h};
        }

        long lo = 1;
        long hi = Long.MAX_VALUE;
        while (lo + 1 < hi) {
            long mid = (lo + hi) / 2;
            if (isPossible(rooms, mid)) hi = mid;
            else lo = mid + 1;
        }

        long answer = 0;
        if (isPossible(rooms, hi)) answer = hi;
        if (isPossible(rooms, lo)) answer = lo;

        System.out.println(answer);
    }

    private static boolean isPossible(long[][] rooms, long mid) {
        long playerLife = mid;
        long playerAttack = H;
        for (int i = 0; i < N; i++) {
            if (rooms[i][0] == 1) {
                long monsterAttack = rooms[i][1];
                long monsterLife = rooms[i][2];

                long attackCount = (monsterLife + playerAttack - 1) / playerAttack;
                long damage = monsterAttack * (attackCount - 1);

                playerLife -= damage;
                if (playerLife <= 0) return false;
            } else {
                playerAttack += rooms[i][1];
                playerLife += rooms[i][2];
                if (playerLife > mid) playerLife = mid;
            }
        }

        return true;
    }
}
