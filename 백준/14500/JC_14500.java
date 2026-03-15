import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int N = array[0], M = array[1];

        int[][] mat = new int[N][M];
        for (int i = 0; i < N; i++) {
            mat[i] = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int max = -1;

        // horizontalSix -> 8개
        List<List<List<Integer>>> horizontalSixRemoveList = new ArrayList<>();
        horizontalSixRemoveList.add(List.of(List.of(1, 1), List.of(1, 2)));
        horizontalSixRemoveList.add(List.of(List.of(1, 0), List.of(1, 2)));
        horizontalSixRemoveList.add(List.of(List.of(1, 0), List.of(1, 1)));
        horizontalSixRemoveList.add(List.of(List.of(0, 1), List.of(0, 2)));
        horizontalSixRemoveList.add(List.of(List.of(0, 0), List.of(0, 2)));
        horizontalSixRemoveList.add(List.of(List.of(0, 0), List.of(0, 1)));
        horizontalSixRemoveList.add(List.of(List.of(0, 2), List.of(1, 0)));
        horizontalSixRemoveList.add(List.of(List.of(0, 0), List.of(1, 2)));
        for (int i = 0; i < 8; i++) {
            int sum = calculateHorizontalSix(mat, horizontalSixRemoveList.get(i));
            if (max < sum) max = sum;
        }

        // verticalSix -> 8개
        List<List<List<Integer>>> verticalSixRemoveList = new ArrayList<>();
        verticalSixRemoveList.add(List.of(List.of(1, 1), List.of(2, 1)));
        verticalSixRemoveList.add(List.of(List.of(0, 1), List.of(2, 1)));
        verticalSixRemoveList.add(List.of(List.of(0, 1), List.of(1, 1)));
        verticalSixRemoveList.add(List.of(List.of(1, 0), List.of(2, 0)));
        verticalSixRemoveList.add(List.of(List.of(0, 0), List.of(2, 0)));
        verticalSixRemoveList.add(List.of(List.of(0, 0), List.of(1, 0)));
        verticalSixRemoveList.add(List.of(List.of(0, 1), List.of(2, 0)));
        verticalSixRemoveList.add(List.of(List.of(0, 0), List.of(2, 1)));
        for (int i = 0; i < 8; i++) {
            int localMax = calculateVerticalSix(mat, verticalSixRemoveList.get(i));
            if (max < localMax) max = localMax;
        }

        // squareFour -> 1개
        int sum = calculateSquareFour(mat);
        if (max < sum) max = sum;

        // verticalFour -> 1개
        sum = calculateVerticalFour(mat);
        if (max < sum) max = sum;

        // horizontalFour -> 1개
        sum = calculateHorizontalFour(mat);
        if (max < sum) max = sum;

        System.out.println(max);
    }

    private static int calculateHorizontalSix(int[][] mat, List<List<Integer>> lists) {
        int max = -1;
        for (int i = 0; i < mat.length - 1; i++) {
            for (int j = 0; j < mat[0].length - 2; j++) {
                int sum = 0;
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 3; l++) {
                        sum += mat[i + k][j + l];
                    }
                }
                sum -= mat[i + lists.get(0).get(0)][j + lists.get(0).get(1)];
                sum -= mat[i + lists.get(1).get(0)][j + lists.get(1).get(1)];

                if (max < sum) max = sum;
            }
        }
        return max;
    }

    private static int calculateVerticalSix(int[][] mat, List<List<Integer>> lists) {
        int max = -1;
        for (int i = 0; i < mat.length - 2; i++) {
            for (int j = 0; j < mat[0].length - 1; j++) {
                int sum = 0;
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 2; l++) {
                        sum += mat[i + k][j + l];
                    }
                }
                sum -= mat[i + lists.get(0).get(0)][j + lists.get(0).get(1)];
                sum -= mat[i + lists.get(1).get(0)][j + lists.get(1).get(1)];

                if (max < sum) max = sum;
            }
        }
        return max;
    }

    private static int calculateSquareFour(int[][] mat) {
        int max = -1;
        for (int i = 0; i < mat.length - 1; i++) {
            for (int j = 0; j < mat[0].length - 1; j++) {
                int sum = 0;
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        sum += mat[i + k][j + l];
                    }
                }

                if (max < sum) max = sum;
            }
        }
        return max;
    }

    private static int calculateVerticalFour(int[][] mat) {
        int max = -1;
        for (int i = 0; i < mat.length - 3; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                int sum = 0;
                for (int k = 0; k < 4; k++) {
                    sum += mat[i + k][j];
                }

                if (max < sum) max = sum;
            }
        }
        return max;
    }

    private static int calculateHorizontalFour(int[][] mat) {
        int max = -1;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length - 3; j++) {
                int sum = 0;
                for (int k = 0; k < 4; k++) {
                    sum += mat[i][j + k];
                }

                if (max < sum) max = sum;
            }
        }
        return max;
    }
}
