import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] stick = br.readLine().toCharArray();
		boolean[] laser = new boolean[stick.length];
		Deque<Integer> dq = new ArrayDeque<>();
		int rst = 0;

		for (int i = 0; i < stick.length; i++) {
			if (stick[i] == '(')
				dq.offer(i);
			else {
				int idx = dq.pollLast();
				if (idx + 1 == i) { // 레이저인가?
					laser[idx] = true;
				} else {
					int cnt = 0;
					for (int j = idx; j <= i; j++)
						cnt += laser[j] ? 1 : 0;
					rst += cnt + 1;
				}
			}
		}

		System.out.println(rst);
	}

}
