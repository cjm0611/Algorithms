import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static long[] data;
	static long[] tree;
	static long sum = 0L;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		data = new long[N];
		tree = new long[N*4];
		
		for (int i=0; i<N; i++) {
			data[i] = Long.parseLong(br.readLine());
		}
		
		init(1, 0, N-1);
		
		for (int i=0; i<M+K; i++) {
			// a가 1이라면 b번째 수를 c로 바꾸기
			// a가 2라면 b번째~c번째 수의 합을 구하기 + 출력
			st = new StringTokenizer(br.readLine());

			int a, b;
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			b -= 1;
			
			if (a == 1) {
				long c;
				c = Long.parseLong(st.nextToken());
				long diff = c - data[b];
				data[b] += diff; // 다음 값 변경했을 때도 diff를 구하려면 변경된 값을 알아야 함.
				update(1, 0, N-1, b, diff);
			} else {
				int c;
				c = Integer.parseInt(st.nextToken());
				sum = query(1, 0, N-1, b, c-1);
				System.out.println(sum);
			}
		}
	}

	static long init(int node, int start, int end) {
		if (start == end) {
			tree[node] = data[start];
			return tree[node];
		}
		
		int mid = (start+end)/2;
		tree[node] = init(node*2, start, mid) + init(node*2+1, mid+1, end);
		return tree[node];
	}
	
	static long query(int node, int start, int end, int left, int right) {
		// 관련 없는 경우
		if (end < left || start > right) {
			return 0;
		}
		
		// 완전 포함되는 경우 (리프노드 포함)
		if (start >= left && end <= right) {
			return tree[node];
		}
		
		int mid = (start+end)/2;
		return query(node*2, start, mid, left, right) + query(node*2+1, mid+1, end, left, right);
	}
	
	static void update(int node, int start, int end, long idx, long diff) {
		// 관련 없는 경우
		if (end < idx || start > idx) {
			return;
		}
		
		tree[node] += diff;
		
		if (start != end) {
			int mid = (start+end)/2;
			update(node*2, start, mid, idx, diff);
			update(node*2+1, mid+1, end, idx, diff);
		}
	}
}
