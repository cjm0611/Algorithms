import java.io.*;
import java.util.*;

public class Main {
	static int T;
	static int N;
	static int SIZE = 1000;
	static int[] euler;
	static int[] dp;
	static ArrayList<Integer> pSum = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		euler = new int[SIZE+1];
		dp = new int[SIZE+1];
		for (int i=1; i<=SIZE; i++) {
			euler[i] = i;
		}
		
		for (int i=2; i<=SIZE; i++) {
			if (euler[i] == i) { // 소수이면
				for (int j=i; j<=SIZE; j+=i) {
					euler[j] = euler[j] - euler[j]/i;
				}
			}
			
			dp[i] = dp[i-1] + euler[i];
		}
		
		int cnt;
		for (int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());
			System.out.println(dp[N]*2 + 3);
		}
	}
}