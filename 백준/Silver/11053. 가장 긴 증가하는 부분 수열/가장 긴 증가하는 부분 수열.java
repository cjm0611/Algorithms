import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        dp = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i=1; i<=N; i++) {
        	for (int j=0; j<=i; j++) {
        		if (arr[j] < arr[i]) {
            		dp[i] = Math.max(dp[i], dp[j]+1);
        		}
        	}
        }
        
        int ans = -1;
        for (int i=1; i<=N; i++) {
        	ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}
