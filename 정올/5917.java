import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		Deque<Integer> dq1 = new ArrayDeque<>();
		Deque<Integer> dq2 = new ArrayDeque<>();
		Deque<Integer> dq3 = new ArrayDeque<>();
		for (int i = N; i > 0; i--)
			dq1.offer(i);

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int C = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			if (C == 1) {
				for (int j = 0; j < D; j++) {
					Integer dish = dq1.pollLast();
					if (dish != null)
						dq2.offer(dish);
				}
			} else {
				for (int j = 0; j < D; j++) {
					Integer dish = dq2.pollLast();
					if (dish != null)
						dq3.offer(dish);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		while (!dq3.isEmpty())
			sb.append(dq3.pollLast()).append("\n");
		System.out.println(sb);
	}

}
