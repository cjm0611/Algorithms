import java.io.*;
import java.util.*;

public class Main {
	static int T, N, M;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		dp = new int[31][31];
		dp[0][0] = 1;
		for (int i=1; i<=30; i++) {
			dp[i][0] = 1;
			for (int j=1; j<=i; j++) {
				dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
			}
		}
		
		
		T = Integer.parseInt(br.readLine());
		for (int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			System.out.println(dp[M][N]);
		}
	}
}