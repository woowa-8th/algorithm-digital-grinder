import java.util.*;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean[][] sameOrNot = new boolean[5][5];
        List<Deque<Integer>> cogwheels = new ArrayList<>();
        cogwheels.add(new ArrayDeque<>(List.of(0,0,0,0,0,0,0,0)));
        for (int i = 0; i < 4; i++) {
            cogwheels.add(new ArrayDeque<>(
                    Arrays.stream(scanner.nextLine().split(""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList())
                    )
            );
        }

        int N = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < N; i++) {
            setSameOrNot(cogwheels, sameOrNot);
            String[] split = scanner.nextLine().split(" ");

            int wheelNum = Integer.parseInt(split[0]);
            int dir = Integer.parseInt(split[1]);
            change(cogwheels.get(wheelNum), dir);

            // 위
            int curWheelNum = wheelNum + 1;
            int curDir = (dir == 1) ? -1 : 1;
            while (curWheelNum <= 4) {
                if (sameOrNot[curWheelNum - 1][curWheelNum]) {
                    change(cogwheels.get(curWheelNum), curDir);
                    curDir = (curDir == 1) ? -1 : 1;
                    curWheelNum++;
                } else {
                    break;
                }
            }

            // 아래
            curWheelNum = wheelNum - 1;
            curDir = (dir == 1) ? -1 : 1;
            while (curWheelNum >= 1) {
                if (sameOrNot[curWheelNum + 1][curWheelNum]) {
                    change(cogwheels.get(curWheelNum), curDir);
                    curDir = (curDir == 1) ? -1 : 1;
                    curWheelNum--;
                } else {
                    break;
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= 4; i++) {
            answer += (int) (cogwheels.get(i).getFirst() * Math.pow(2, i - 1));
        }
        System.out.println(answer);
    }

    private static void change(Deque<Integer> cogwheel, int dir) {
        if (dir == 1) {
            cogwheel.addFirst(cogwheel.removeLast());
        } else {
            cogwheel.addLast(cogwheel.removeFirst());
        }
    }

    private static void setSameOrNot(List<Deque<Integer>> cogwheels, boolean[][] sameOrNo) {
        for (int i = 1; i < 4; i++) {
            if (getLeft(cogwheels.get(i)) != getRight(cogwheels.get(i + 1))) {
                sameOrNo[i][i + 1] = true;
                sameOrNo[i + 1][i] = true;
            } else {
                sameOrNo[i][i + 1] = false;
                sameOrNo[i + 1][i] = false;
            }
        }
    }

    private static int getRight(Deque<Integer> cogwheel) {
        int eighth = cogwheel.pollLast();
        int seventh = cogwheel.pollLast();
        cogwheel.addLast(seventh);
        cogwheel.addLast(eighth);
        return seventh;
    }

    private static int getLeft(Deque<Integer> cogwheel) {
        int first = cogwheel.pollFirst();
        int second = cogwheel.pollFirst();
        int third = cogwheel.pollFirst();
        cogwheel.addFirst(third);
        cogwheel.addFirst(second);
        cogwheel.addFirst(first);
        return third;
    }
}
