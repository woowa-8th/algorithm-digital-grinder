class Main {

    public static void main(String[] args) {
        for (int i = 1; i < 10000; i++) {
            boolean isSelfNumber = true;
            for (int j = 1; j < i; j++) {
                if (isConstructor(i, j)) {
                    isSelfNumber = false;
                    break;
                }
            }
            if (isSelfNumber) {
                System.out.println(i);
            }
        }
    }

    private static boolean isConstructor(int i, int j) {
        return i == j + digitSum(j);
    }

    private static int digitSum(int j) {
        int sum = 0;
        while (j > 0) {
            sum += j % 10;
            j /= 10;
        }
        return sum;
    }
}
