import java.io.*;
import java.util.*;

public class Main {
	static int N, P;
	static int INF = 987654321;
	static int[][] adj;
	static int[] dp; // 최소 비용
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        adj = new int[N][N];
        dp = new int[1<<N];
        for (int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j=0; j<N; j++) {
        		adj[i][j] = Integer.parseInt(st.nextToken()); // 0인 경우 갈 수 없음
        	}
        }
        
        for (int i=0; i<(1<<N); i++) {
        	dp[i] = -1;
        }
       
        int defaultOn = 0;
        String s = br.readLine();
        for (int i=0; i<N; i++) {
        	if (s.charAt(i) == 'Y') {
        		defaultOn += (1<<i);
        	}
        }
        
        P = Integer.parseInt(br.readLine());
        int ans = tsp(defaultOn);
        if (ans == INF) {
        	System.out.println(-1);
        } else {
        	System.out.println(ans);
        }
    }
    
    static int tsp(int on) {
    	if (Integer.bitCount(on) >= P) {
    		return 0;
    	}
    	
    	if (dp[on] != -1) return dp[on]; // 재사용
    	
    	dp[on] = INF;
    	for (int i=0; i<N; i++) {
    		if ((on & (1<<i)) != 0) { // 현재 발전소가 켜져 있다면
    			for (int j=0; j<N; j++) { // 다음 발전소 켜보기
    				if ((on & (1<<j)) == 0) {
    					int t = tsp(on | (1<<j)) + adj[i][j];
    					dp[on] = Math.min(dp[on],  t);
    				}
    			}
    		}
    	}

    	return dp[on];
    }
}
