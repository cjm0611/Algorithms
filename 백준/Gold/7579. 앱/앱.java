import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] dp;
	static int[] memo;
	static int[] cost;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dp = new int[10001]; // c 만큼의 비용을 사용했을 때 확보할 수 있는 최대 메모리
		
		memo = new int[N+1]; // 현재 사용중인 메모리
		cost = new int[N+1]; // 종료 시 필요한 값
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			memo[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int n=1; n<=N; n++) {
			for (int k=10000; k>=0; k--) { // k 비용을 사용했을 때 확보할 수 있는 최대 메모리 양
				int m = memo[n];
				int c = cost[n];
				if (c <= k) {
					dp[k] = Math.max(dp[k], dp[k-c]+m);
				}
			}
		}
	
		int ans = 0;
		while (true) {
			if (dp[ans] >= M) break;
			ans++;
		}
		
		System.out.println(ans);
	}
}