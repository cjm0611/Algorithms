import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static ArrayList<ArrayList<int[]>> edges;
	static ArrayList<PriorityQueue<Integer>> dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점 수
		M = Integer.parseInt(st.nextToken()); // 간선 수
		K = Integer.parseInt(st.nextToken()); // k번째
		
		edges = new ArrayList<>();
		dist = new ArrayList<>();
		for (int i=0; i<=N; i++) {
			dist.add(new PriorityQueue<>((o1, o2) -> {
				return Integer.compare(o1, o2);
			}));
			edges.add(new ArrayList<>());			
		}
		
		int s, e, w;
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			edges.get(s).add(new int[] {e, w});
		}
		
		dijkstra(1);
		
		for (int i=1; i<=N; i++) {
			if (dist.get(i).size() < K) {
				sb.append(-1 + "\n");
				continue;
			}
			
			
			for (int k=0; k<K-1; k++) {
				dist.get(i).poll();
			}
			sb.append(dist.get(i).peek() + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void dijkstra(int start) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			return Integer.compare(o1[1],  o2[1]);
		});
		pq.add(new int[] {start, 0});
		
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if (dist.get(cur[0]).size() >= K) continue;
			
			dist.get(cur[0]).add(cur[1]);
			
			for (int i=0; i<edges.get(cur[0]).size(); i++) {
				int[] edge = edges.get(cur[0]).get(i);
				int e, w;
				e = edge[0];
				w = edge[1];
				
				pq.offer(new int[] {e, cur[1] + w});
			}
		}
	}
}
