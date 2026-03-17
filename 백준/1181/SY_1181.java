import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Set<String> set = new HashSet<>();
		while (N-- > 0)
			set.add(br.readLine());
		
		List<String> words = new ArrayList<>(set);
		words.sort((o1, o2) -> {
			if (o1.length() != o2.length()) {
				return o1.length() - o2.length();
			}
			return o1.compareTo(o2);
		});

		StringBuilder sb = new StringBuilder();
		for (String word : words)
			sb.append(word).append("\n");
		System.out.println(sb);
	}

}
