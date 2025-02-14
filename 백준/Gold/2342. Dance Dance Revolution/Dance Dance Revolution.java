import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] arr;
	static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        arr = new int[1000001]; // 밟아야 하는 위치
        st = new StringTokenizer(br.readLine());
        int cnt = 0;
        while (true) {
        	int num = Integer.parseInt(st.nextToken());
        	if (num == 0) break;
        	arr[++cnt] = num;
        }
        
        dp = new int[2][5][5]; // 0: 현재, 1: 이전 // 왼발 최종 위치, 오른발 최종 위치
        
        for (int i=0; i<5; i++) { // 아직까진 도착한 발이 없으니 초기화
        	for (int j=0; j<5; j++) {
        		dp[0][i][j] = Integer.MAX_VALUE;
        		dp[1][i][j] = Integer.MAX_VALUE;
        	}
        }
        
        dp[1][0][0] = 0; // (0, 0)에서 시작
        int p; // 현재 밟을 위치
        
        for (int k=1; k<=cnt; k++) {
        	p = arr[k];
        	
        	// 직전 발이 어디있었는지 모르니 5*5 전부 탐색
        	for (int i=0; i<5; i++) {
        		for (int j=0; j<5; j++) {
        			if (dp[1][i][j] == Integer.MAX_VALUE) continue;

        			dp[0][p][j] = Math.min(dp[0][p][j], dp[1][i][j] + cost(i, p));
        			dp[0][i][p] = Math.min(dp[0][i][p], dp[1][i][j] + cost(j, p));
        		}
        	}
        	
        	// 현재 발의 위치를 이전 배열로 옮김
        	for (int i=0; i<5; i++) {
        		for (int j=0; j<5; j++) {
        			dp[1][i][j] = dp[0][i][j];
        			dp[0][i][j] = Integer.MAX_VALUE;
        		}
        	}
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i=0; i<5; i++) {
            ans = Math.min(ans, Math.min(dp[1][arr[cnt]][i], dp[1][i][arr[cnt]]));
        }
        System.out.println(ans);
    }
    
    static int cost(int s, int e) {
    	if (s == e) return 1;
    	if (s == 0) return 2;
    	if (Math.abs(s-e) == 2) return 4;
    	return 3;
    }
}
