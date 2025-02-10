import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] p;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		p = new int[N+1];
		for (int i=1; i<=N; i++) {
			p[i] = i;
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a, b, c;
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			if (a==0) {
				union(b, c);
			} else {
				System.out.println(find(b) == find(c) ? "YES" : "NO");
			}
		}
	}
	
	static void union(int x, int y) {
		int pX = find(x);
		int pY = find(y);
		
		if (pX != pY) {
			p[pX] = p[pY];
		}
	}
	
	static int find(int x) {
		if (x == p[x]) {
			return x;
		}
		
		p[x] = find(p[x]);
		return p[x];
	}
}