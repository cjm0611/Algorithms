import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int INF = 9876543;
	static int[][] adj;
	static int[][] dp; // current에 visited 상태로 방문한 경우의 최소 비용
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        adj = new int[N][N];
        dp = new int[N][1<<N]; // N=6이면 1000000 이지만, idx는 -1이 되므로 111111
        for (int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j=0; j<N; j++) {
        		adj[i][j] = Integer.parseInt(st.nextToken()); // 0인 경우 갈 수 없음
        	}
        }
        
        System.out.println(tsp(0, 1));
    }
    
    static int tsp(int cur, int visited) {
    	if ((visited == (1<<N)-1)) { // 모든 도시 방문한 경우
    		if (adj[cur][0] == 0) return INF;
    		return adj[cur][0]; // cur -> 시작점 비용 반환 (되돌아가야 하니까)
    	}
    	
    	if (dp[cur][visited] != 0) return dp[cur][visited]; // 재사용
    	
    	int min = INF;
    	for (int i=0; i<N; i++) {
    		if ((visited & (1<<i)) == 0 && adj[cur][i] != 0) {
    			int newVisited = visited | (1<<i);
    			int t = tsp(i, newVisited) + adj[cur][i];
    			min = Math.min(min, t);
    		}
    	}

    	dp[cur][visited] = min; // 현재 케이스의 최솟값
    	return dp[cur][visited];
    }
}
