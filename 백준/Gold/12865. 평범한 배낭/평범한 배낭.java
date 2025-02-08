import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[][] arr;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[N+1][K+1];
		arr = new int[N+1][2];
		
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			arr[i][0] = w;
			arr[i][1] = v;
		}
		
		for (int n=1; n<=N; n++) {
			for (int k=1; k<=K; k++) {
				int w = arr[n][0];
				int v = arr[n][1];
				
				if (w <= k) {
					dp[n][k] = Math.max(dp[n-1][k], dp[n-1][k-w] + v);
				} else {
					dp[n][k] = dp[n-1][k];
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}
}