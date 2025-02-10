import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] p;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			if (N == 0 && M == 0) break;
			
			p = new int[N+1][2]; // root, distance
			for (int i=1; i<=N; i++) {
				p[i][0] = i;
				p[i][1] = 0;
			}
			
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				String action = st.nextToken();
				int a, b, w;
				if (action.equals("!")) { // ! a b w
					a = Integer.parseInt(st.nextToken());
					b = Integer.parseInt(st.nextToken());
					w = Integer.parseInt(st.nextToken());
					
					union(a, b, w);
				} else { // ? a b
					a = Integer.parseInt(st.nextToken());
					b = Integer.parseInt(st.nextToken());
					
					if (find(a)[0] == find(b)[0]) {
						System.out.println(p[b][1] - p[a][1]);
					} else {
						System.out.println("UNKNOWN");
					}
				}
			}
		}
	}

	static void union(int a, int b, int w) {
		int x = find(a)[0];
		int y = find(b)[0];
		
		p[y][0] = p[x][0];
		p[y][1] = w + (p[a][1] - p[b][1]);
	}
	
	static int[] find(int a) {
		if (a == p[a][0]) {
			return new int[] {a, 0};
		}
		
		int[] result = find(p[a][0]);
		p[a][0] = result[0];
		p[a][1] += result[1];
		return new int[] {p[a][0], p[a][1]};
	}
}