import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
	static int[] ind;
	static Queue<Integer> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ind = new int[N+1];		
		for (int i=0; i<=N; i++) {
			edges.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s, e;
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			edges.get(s).add(e);
			ind[e]++;
		}
		
		for (int i=1; i<=N; i++) {
			if (ind[i] == 0) {
				q.add(i);
			}
		}
		
		int cur, next;
		while (!q.isEmpty()) {
			cur = q.poll();
			System.out.print(cur + " ");
			for (int i=0; i<edges.get(cur).size(); i++) {
				next = edges.get(cur).get(i);
				if (--ind[next] == 0) {
					q.add(next);
				}
			}
		}
	}
}