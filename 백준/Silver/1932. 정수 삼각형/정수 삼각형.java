import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] tri;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		tri = new int[N+1][N+1];
		dp = new int[N+1][N+1];
		
		for (int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=i; j++) {
				tri[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=i; j++) {
				if (j==1) {
					dp[i][j] = dp[i-1][1] + tri[i][j];
				} else if (j==i) {
					dp[i][j] = dp[i-1][j-1] + tri[i][j];
				} else {
					dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + tri[i][j];
				}
			}
		}
		
		int ans = -1;
		for (int i=1; i<=N; i++) {
			ans = Math.max(ans, dp[N][i]);
		}
		
		System.out.println(ans);
	}
}