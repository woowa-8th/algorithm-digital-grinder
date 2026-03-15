import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        int N = Integer.parseInt(line.split(" ")[0]);
        int M = Integer.parseInt(line.split(" ")[1]);

        String[][] whiteMat = new String[8][8];
        String[][] blackMat = new String[8][8];
        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 8; j++) {
                    if (cnt % 2 == 0) {
                        whiteMat[i][j] = "W";
                        blackMat[i][j] = "B";
                    } else {
                        whiteMat[i][j] = "B";
                        blackMat[i][j] = "W";
                    }
                    cnt++;
                }
            } else {
                for (int j = 7; j >= 0; j--) {
                    if (cnt % 2 == 0) {
                        whiteMat[i][j] = "W";
                        blackMat[i][j] = "B";
                    } else {
                        whiteMat[i][j] = "B";
                        blackMat[i][j] = "W";
                    }
                    cnt++;
                }
            }
        }

        String[][] mat = new String[N][M];
        for (int i = 0; i < N; i++) {
            String line1 = scanner.nextLine();
            mat[i] = line1.split("");
        }

        int min = 50 * 50;
        for (int i = 0; i < N - 7; i++) {
            for (int j = 0; j < M - 7; j++) {
                int cur = calculateRetouchCount(i, j, mat, whiteMat, blackMat);
                if (min > cur) {
                    min = cur;
                }
            }
        }

        System.out.println(min);
    }

    private static int calculateRetouchCount(int startX, int startY, String[][] mat, String[][] whiteMat,
                                             String[][] blackMat) {
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!mat[i + startX][j + startY].equals(whiteMat[i][j])) count1++;
                if (!mat[i + startX][j + startY].equals(blackMat[i][j])) count2++;
            }
        }

        return Math.min(count1, count2);
    }
}
