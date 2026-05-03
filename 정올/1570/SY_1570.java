import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> maxs = new PriorityQueue<>((o1, o2) -> o2 - o1);
		PriorityQueue<Integer> mins = new PriorityQueue<>();

		StringBuilder sb = new StringBuilder();
		maxs.offer(Integer.parseInt(br.readLine()));
		sb.append(maxs.peek() + "\n");
		for (int i = 0; i < N / 2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			maxs.offer(Integer.parseInt(st.nextToken()));
			mins.offer(Integer.parseInt(st.nextToken()));

			if (maxs.peek() > mins.peek()) {
				int max = maxs.poll();
				int min = mins.poll();
				maxs.offer(min);
				mins.offer(max);
			}

			sb.append(maxs.peek() + "\n");
			if (i % 1000 == 0 || i + 1 == N / 2) {
				System.out.println(sb);
				sb = new StringBuilder();
			}
		}

	}

}
