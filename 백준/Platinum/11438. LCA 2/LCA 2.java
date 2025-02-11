import java.io.*;
import java.util.*;

public class Main {
	static int N, parent[][], depth[];
	static ArrayList<ArrayList<Integer>> edges;
	static int M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		depth = new int[N+1];
		parent = new int[30][N+1];
		
		edges = new ArrayList<>();
		for (int i=0; i<=N; i++) {
			edges.add(new ArrayList<>());
		}
		
		int s, e;
		for (int i=0; i<N-1; i++) { // 누가 부모인지 모름
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			edges.get(s).add(e);
			edges.get(e).add(s); 
		}
		
		// 1번 노드부터 depth 및 parent 계산		
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		depth[1] = 1;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i=0; i<edges.get(cur).size(); i++) {
				int next = edges.get(cur).get(i);
				if (depth[next] == 0) {
					depth[next] = depth[cur]+1;
					parent[0][next] = cur;
					q.add(next);
				}
			}
		}

		// N의 2^k번째 부모를 저장하는 dp 테이블 계산
		for (int k=1; k<=29; k++) {
			for (int i=1; i<=N; i++) {
				parent[k][i] = parent[k-1][parent[k-1][i]];
			}
		}
		
		M = Integer.parseInt(br.readLine());
		int a, b;
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			System.out.println(lca(a, b));
		}
	}
	
	static int lca(int a, int b) {
		if (depth[a] > depth[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		// 높이 맞추기
		for (int k=29; k>=0; k--) {
			if (depth[b]-depth[a] >= (1 << k)) {
				b = parent[k][b];
			}
		}
		
		// 맞춘 높이가 같다 => 공통조상
		if (a == b) return a;
		
		// 공통조상이 다르다면 거슬러 올라가기
		for (int k=29; k>=0; k--) {
			if (parent[k][a] != parent[k][b]) {
				a = parent[k][a];
				b = parent[k][b];
			}
		}
		
		return parent[0][a];
	}
}