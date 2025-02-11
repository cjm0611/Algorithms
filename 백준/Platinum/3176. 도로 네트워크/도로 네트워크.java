import java.io.*;
import java.util.*;

public class Main {
	static int N, parent[][], depth[];
	static int cost[][][];
	static ArrayList<ArrayList<int[]>> edges;
	static int K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		depth = new int[N+1];
		cost = new int[30][N+1][2];
		parent = new int[30][N+1];
		
		edges = new ArrayList<>();
		for (int i=0; i<=N; i++) {
			edges.add(new ArrayList<>());
		}
		
		int s, e, c;
		for (int i=0; i<N-1; i++) { // 누가 부모인지 모름
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			edges.get(s).add(new int[] {e, c});
			edges.get(e).add(new int[] {s, c}); 
		}
		
		// 1번 노드부터 depth 및 parent 계산		
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		depth[1] = 1;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i=0; i<edges.get(cur).size(); i++) {
				int[] next = edges.get(cur).get(i);
				if (depth[next[0]] == 0) {
					depth[next[0]] = depth[cur]+1;
					parent[0][next[0]] = cur;
					cost[0][next[0]][0] = next[1];
					cost[0][next[0]][1] = next[1];
					q.add(next[0]);
				}
			}
		}

		// N의 2^k번째 부모를 저장하는 dp 테이블 계산 & 도로의 비용 계산
		for (int k=1; k<=29; k++) {
			for (int i=1; i<=N; i++) {
				parent[k][i] = parent[k-1][parent[k-1][i]];
				// 나 -> 부모, 부모 -> 부모의 부모
				cost[k][i][0] = Math.min(cost[k-1][i][0], cost[k-1][parent[k-1][i]][0]);
				cost[k][i][1] = Math.max(cost[k-1][i][1], cost[k-1][parent[k-1][i]][1]);
			}
		}
		
		K = Integer.parseInt(br.readLine());
		int a, b;
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			int[] ans = lca(a, b);
			System.out.println(ans[0] + " " + ans[1]);
		}
	}
	
	static int[] lca(int a, int b) {
		if (depth[a] > depth[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		int c1, c2;
		c1 = Integer.MAX_VALUE;
		c2 = Integer.MIN_VALUE;
	
		// 높이 맞추기
		for (int k=29; k>=0; k--) {
			if (depth[b]-depth[a] >= (1 << k)) {
				c1 = Math.min(cost[k][b][0], c1);
				c2 = Math.max(cost[k][b][1], c2);
				b = parent[k][b];
			}
		}
		
		// 맞춘 높이가 같다 => 공통조상
		if (a == b) return new int[] {c1, c2};
		
		// 공통조상이 다르다면 거슬러 올라가기
		for (int k=29; k>=0; k--) {
			if (parent[k][a] != parent[k][b]) {
				c1 = Math.min(Math.min(c1, cost[k][a][0]), cost[k][b][0]);
				c2 = Math.max(Math.max(c2, cost[k][a][1]), cost[k][b][1]);
				a = parent[k][a];
				b = parent[k][b];
			}
		}

		// 부모 노드로 가는 길 한번 더 확인
		c1 = Math.min(c1,  Math.min(cost[0][a][0], cost[0][b][0]));
		c2 = Math.max(c2,  Math.max(cost[0][a][1], cost[0][b][1]));
		return new int[] {c1, c2};
	}

}
