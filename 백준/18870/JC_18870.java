import java.util.*;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();;
        scanner.nextLine();

        int[] array = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] sorted = Arrays.stream(array).distinct().sorted().toArray();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < sorted.length; i++) {
            map.put(sorted[i], i);
        }

        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = map.get(array[i]);
        }

        System.out.println(String.join(" ", Arrays.stream(answer).mapToObj(String::valueOf).toArray(String[]::new)));
    }
}
