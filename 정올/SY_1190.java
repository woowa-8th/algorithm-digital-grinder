import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		while (N-- > 0)
			pq.offer(Integer.parseInt(st.nextToken()));

		long sum = 0;
		while (true) {
			int A = pq.poll();
			int B = 0;
			if (!pq.isEmpty())
				B = pq.poll();

			sum += A + B;

			if (pq.isEmpty())
				break;

			pq.offer(A + B);
		}

		System.out.println(sum);

	}

}
