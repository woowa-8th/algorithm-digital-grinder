import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		char[] input = br.readLine().toCharArray();
		Deque<Integer> dq = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();

		int need = 1;
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(String.valueOf(input[i]));
			sb.append("push\n");
			dq.offer(num);

			Integer top = dq.peekLast();
			while (top != null && top == need) {
				need++;
				dq.pollLast();
				sb.append("pop\n");
				top = dq.peekLast();
			}
		}

		if (!dq.isEmpty()) {
			System.out.println("-1");
			return;
		}
		System.out.println(sb);
	}

}
