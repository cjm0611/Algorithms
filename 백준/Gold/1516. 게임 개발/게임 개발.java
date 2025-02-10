import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
	static int[] ind;
	static int[] times;
	static Queue<Integer> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		ind = new int[N+1];		
		times = new int[N+1];
		
		for (int i=0; i<=N; i++) {
			edges.add(new ArrayList<>());
		}

		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			times[i] = Integer.parseInt(st.nextToken());
			
			while (true) {
				int num = Integer.parseInt(st.nextToken());
				if (num == -1) break;
				edges.get(num).add(i);
				ind[i]++;
			}
		}
		
		for (int i=1; i<=N; i++) {
			if (ind[i] == 0) {
				q.add(i);
			}
		}
		
		int cur, next;
		int[] result = new int[N+1]; // i번째 건물을 짓기 전까지 누적된 시간
		while (!q.isEmpty()) {
			cur = q.poll();
			
			for (int i=0; i<edges.get(cur).size(); i++) {
				next = edges.get(cur).get(i);
				result[next] = Math.max(result[next],  result[cur] + times[cur]);
				
				if (--ind[next] == 0) {
					q.add(next);
				}
			}
		}
		
		for (int i=1; i<=N; i++) {
			System.out.println(result[i] + times[i]);
		}
	}
}