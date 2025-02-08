import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static int[][] dp;
	static int MAX = 1000000001;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new int[201][201];
		dp[0][0] = 1;
		for (int i=1; i<201; i++) {
			dp[i][0] = 1;
			for (int j=1; j<=i; j++) {
				dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
				if (dp[i][j] > 1000000000) {
					dp[i][j] = MAX;
				}
			}
		}
		
		if (dp[N+M][M] < K) {
			System.out.println(-1);
			return;
		}
		
		while (N != 0 || M != 0) {
			if (dp[N-1+M][M] >= K) {
				N--;
				sb.append('a');
			} else {
				// Z이 앞에 오는 경우
				K -= dp[N-1+M][M];
				M--;
				sb.append('z');
			}
		}
		
		System.out.println(sb.toString());
	}
}