import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] tree; // tree[node][2], 0: min / 1: max
	static int[] inputs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		inputs = new int[N];
		tree = new int[4*N][2];
		for (int i=0; i<N; i++) {
			inputs[i] = Integer.parseInt(br.readLine());
		}
		
		init(1, 0, N-1);
		
		for (int i=0; i<M; i++) {
			int a, b;

			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			a -= 1;
			b -= 1;
			
			if (a > b) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			
			int[] ans = query(1, 0, N-1, a, b);
			sb.append(ans[0] + " " + ans[1] + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static int[] init(int node, int start, int end) {
		if (start == end) {
			tree[node][0] = inputs[start];
			tree[node][1] = inputs[start];
			return tree[node];
		}
		
		int mid = (start+end) / 2;
		int[] l = init(node*2, start, mid);
		int[] r = init(node*2+1, mid+1, end);

		tree[node][0] = Math.min(l[0], r[0]);
		tree[node][1] = Math.max(l[1], r[1]);
		return tree[node];
	}
	
	// 주어진 구간의 최소, 최대 값 출력
	static int[] query(int node, int start, int end, int left, int right) {
		if (right < start || end < left) {
			return new int[] {Integer.MAX_VALUE, -1};
		}
		
		if (left <= start && end <= right) {
			return tree[node];
		}
		
		int mid = (start+end) / 2;
		int[] leftQ = query(node*2, start, mid, left, right);
		int[] rightQ = query(node*2+1, mid+1, end, left, right);
		return new int[] {Math.min(leftQ[0], rightQ[0]), Math.max(leftQ[1], rightQ[1])};
	}
}
