import java.util.*;

class Main {

    static int airPurifierR1;
    static int airPurifierR2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int r = array[0];
        int c = array[1];
        int T = array[2];

        int[][] mat = new int[r][c];
        for (int i = 0; i < r; i++) {
            mat[i] = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[] airPurifier = new int[2];
        int index = 0;
        for (int i = 0; i < r; i++) {
            if (mat[i][0] == -1) airPurifier[index++] = i;
        }
        airPurifierR1 = airPurifier[0];
        airPurifierR2 = airPurifier[1];

        int[][] diffusions = new int[r][c];
        for (int t = 0; t < T; t++) {
            // 확산 미세먼지양 세팅
            Deque<List<Integer>> queue = new ArrayDeque<>();
            for (int row = 0; row < r; row++) {
                for (int col = 0; col < c; col++) {
                    if (mat[row][col] > 0) {
                        diffusions[row][col] = mat[row][col] / 5;
                        queue.addLast(List.of(row, col));
                    }
                }
            }

            // 미세먼지 확산
            while (!queue.isEmpty()) {
                List<Integer> coordinate = queue.removeFirst();
                int row = coordinate.get(0);
                int col = coordinate.get(1);

                // 상
                if (row - 1 >= 0 && mat[row - 1][col] != -1) {
                    mat[row - 1][col] += diffusions[row][col];
                    mat[row][col] -= diffusions[row][col];
                }
                // 하
                if (row + 1 < r && mat[row + 1][col] != -1) {
                    mat[row + 1][col] += diffusions[row][col];
                    mat[row][col] -= diffusions[row][col];
                }
                // 좌
                if (col - 1 >= 0 && mat[row][col - 1] != -1) {
                    mat[row][col - 1] += diffusions[row][col];
                    mat[row][col] -= diffusions[row][col];
                }
                // 우
                if (col + 1 < c && mat[row][col + 1] != -1) {
                    mat[row][col + 1] += diffusions[row][col];
                    mat[row][col] -= diffusions[row][col];
                }
            }

            // 공기 청정기 작동
            // 윗부분
            for (int i = airPurifierR1 - 1; i >= 0; i--) {
                if (mat[i + 1][0] != -1) {
                    mat[i + 1][0] = mat[i][0];
                }
            }
            for (int j = 1; j < c; j++) {
                mat[0][j - 1] = mat[0][j];
            }
            for (int i = 1; i <= airPurifierR1; i++) {
                mat[i - 1][c - 1] = mat[i][c - 1];
            }
            for (int j = c - 2; j >= 0; j--) {
                if (mat[airPurifierR1][j] != -1) {
                    mat[airPurifierR1][j + 1] = mat[airPurifierR1][j];
                } else {
                    mat[airPurifierR1][j + 1] = 0;
                }
            }

            // 아랫부분
            for (int i = airPurifierR2 + 1; i < r; i++) {
                if (mat[i - 1][0] != -1) {
                    mat[i - 1][0] = mat[i][0];
                }
            }
            for (int j = 1; j < c; j++) {
                mat[r - 1][j - 1] = mat[r - 1][j];
            }
            for (int i = r - 2; i >= airPurifierR2; i--) {
                mat[i + 1][c - 1] = mat[i][c - 1];
            }
            for (int j = c - 2; j >= 0; j--) {
                if (mat[airPurifierR2][j] != -1) {
                    mat[airPurifierR2][j + 1] = mat[airPurifierR2][j];
                } else {
                    mat[airPurifierR2][j + 1] = 0;
                }
            }
        }

        int answer = 0;
        for (int[] ints : mat) {
            answer += Arrays.stream(ints).sum();
        }
        System.out.println(answer + 2);
    }
}
