import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] towers = new int[N];
		for (int i = 0; i < N; i++)
			towers[i] = Integer.parseInt(st.nextToken());

		int[] ans = new int[N];
		Deque<Integer> stack = new ArrayDeque<>();

		for (int i = N - 1; i >= 0; i--) {
			while (!stack.isEmpty() && towers[stack.peek()] < towers[i]) {
				int idx = stack.pop();
				ans[idx] = i + 1;
			}

			stack.push(i);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++)
			sb.append(ans[i]).append(" ");
		System.out.println(sb);
	}

}
