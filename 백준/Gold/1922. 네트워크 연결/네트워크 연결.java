import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] p;
	static ArrayList<int[]> edges = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		p = new int[N+1];
		for (int i=1; i<=N; i++) {
			p[i] = i;
		}
		
		int s, e, w;
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			edges.add(new int[] {s, e, w});
		}
		
		Collections.sort(edges, (o1, o2) -> {
			return Integer.compare(o1[2], o2[2]);
		});
		
		int cnt = 0;
		int minCost = 0;
		for (int i=0; i<M; i++) {
			int[] edge = edges.get(i);
			s = edge[0];
			e = edge[1];
			w = edge[2];
			
			if (find(s) != find(e)) {
				union(s, e);
				cnt++;
				minCost += w;
			}
			
			if (cnt == N-1) break;
		}
		
		System.out.println(minCost);
	}
	
	static void union(int a, int b) {
		int x = find(a);
		int y = find(b);
		
		if (x != y) {
			p[x] = y;
		}
	}
	
	static int find(int a) {
		if (p[a] == a) return a;
		
		return p[a] = find(p[a]);
	}
}