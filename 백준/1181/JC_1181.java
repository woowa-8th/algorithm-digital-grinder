import java.util.*;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();;
        scanner.nextLine();

        String[] strings = new String[N];
        for (int i = 0; i < N; i++) {
            strings[i] = scanner.nextLine();
        }

        List<String> list = Arrays.stream(strings).distinct()
                .sorted(Comparator.comparing(String::length).thenComparing(String::compareTo))
                .collect(Collectors.toList());

        for (String s : list) {
            System.out.println(s);
        }
    }
}
