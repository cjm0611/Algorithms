import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] matrix;
	static int[][] dp;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        matrix = new int[N+1][2]; // 0: 세로 1: 가로
        for (int i=1; i<=N; i++) {
        	st = new StringTokenizer(br.readLine());
        	matrix[i][0] = Integer.parseInt(st.nextToken());
        	matrix[i][1] = Integer.parseInt(st.nextToken());
        }
        
        dp = new int[N+1][N+1];
        for (int i=1; i<N; i++) {
        	dp[i][i+1] = matrix[i][0] * matrix[i][1] * matrix[i+1][1];
        }
        
        // ABCDE -> gap2라면 ABC를 구한다.
        for (int gap=2; gap<N; gap++) { // 문자열의 길이
        	for (int start=1; start+gap<=N; start++) { // start=1, gap=2이라면 ABC
        		int end = start + gap;
        		// gap이 2일때 ABC BCD CDE
        		dp[start][end] = Integer.MAX_VALUE;
        		// k: 분할지점, ABC에서 k가 start이면 A(BC)로 분할
        		// A(BCD) -> (AB)(CB) -> (ABC)D
        		for (int k=start; k<end; k++) {
        			dp[start][end] = Math.min(dp[start][end], // start의 가로 * k의 세로 * end의 세로
        					dp[start][k]+dp[k+1][end]+matrix[start][0]*matrix[k][1]*matrix[end][1]);
        		}
        	}
        }
        
        System.out.println(dp[1][N]);
    }
}
