import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (isHansu(i)) answer++;
        }

        System.out.println(answer);
    }

    private static boolean isHansu(int i) {
        String[] split = String.valueOf(i).split("");
        int[] diff = new int[split.length - 1];
        for (int j = 0; j < split.length - 1; j++) {
            diff[j] = Integer.parseInt(split[j]) - Integer.parseInt(split[j + 1]);
        }

        return Arrays.stream(diff).allMatch(d -> d == diff[0]);
    }
}
