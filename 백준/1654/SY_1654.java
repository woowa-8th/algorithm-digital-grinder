import java.util.*;
import java.io.*;

public class Main {
	static int N, K;
	static int[] lens;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		lens = new int[K];
		for (int i = 0; i < K; i++)
			lens[i] = Integer.parseInt(br.readLine());

		long lo = 0;
		long hi = Long.MAX_VALUE;
		while (lo + 1 < hi) {
			long mid = (lo + hi) / 2;
			if (check(mid))
				lo = mid;
			else
				hi = mid;
		}

		System.out.println(lo);
	}

	public static boolean check(long mid) {
		int cnt = 0;
		for (int i = 0; i < K; i++)
			cnt += lens[i] / mid;
		return cnt >= N;
	}
}
