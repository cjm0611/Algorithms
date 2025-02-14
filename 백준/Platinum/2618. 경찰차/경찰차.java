import java.io.*;
import java.util.*;

public class Main {
	
	static int N, W, INF=987654321;
	static int[][] dp;
	static int[][] points;
	static int ans; // 최소비용

	static int[] left = new int[2];
	static int[] right = new int[2];
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine()); // 1~w번째 사건
        points = new int[W+1][2]; // w번째 사건이 일어난 좌표 (x, y)
        int x, y;
        for (int i=1; i<=W; i++) {
        	st = new StringTokenizer(br.readLine());
        	points[i][0] = Integer.parseInt(st.nextToken())-1;
        	points[i][1] = Integer.parseInt(st.nextToken())-1;
        }
        
        dp = new int[W+1][W+1];
        for (int i=0; i<=W; i++) {
        	for (int j=0; j<=W; j++) {
        		dp[i][j] = -1;
        	}
        }
        
        ans = select(0, 0); // 최소비용
        sb.append(ans + "\n");
        
        // 역추적
        int w=0, i=0, j=0;
        int x1=1, y1=1, x2=N, y2=N; // 각 경찰차의 좌표
        while (w < W) {
        	w++;
        	int dist1 = dist(x1, y1, points[w][0], points[w][1]) + dp[w][j];
        	int dist2 = dist(x2, y2, points[w][0], points[w][1]) + dp[i][w];
        	
        	if (dist1 < dist2) {
        		i = w;
        		x1 = points[w][0];
        		y1 = points[w][1];
        		sb.append(1 + "\n");
        	} else {
        		j = w;
        		x2 = points[w][0];
        		y2 = points[w][1];
        		sb.append(2 + "\n");
        	}
        }
        
        System.out.print(sb.toString());
    }
    
    static int select(int x, int y) { // 1번 경찰차가 x번까지 해결, 2번 경찰차가 y번까지 해결
    	if (x == W || y == W) {
    		return 0;
    	}
    	
    	if (dp[x][y] != -1) return dp[x][y]; // 재사용
    	
    	if (x == 0) {
    		left[0] = 1;
    		left[1] = 1;
    	} else {
    		left[0] = points[x][0];
    		left[1] = points[x][1];
    	}
    	
    	if (y == 0) {
    		right[0] = N;
    		right[1] = N;
    	} else {
    		right[0] = points[y][0];
    		right[1] = points[y][1];
    	}

    	int next = Math.max(x,  y) + 1; // 해결할 사건 번호
    	int leftDistance = dist(left[0], left[1], points[next][0], points[next][1]);
    	int rightDistance = dist(right[0], right[1], points[next][0], points[next][1]);
    	
    	int l = select(next, y)+leftDistance;
    	int r = select(x, next)+rightDistance;
    	return dp[x][y] = Math.min(l, r);
    }
    
    static int dist(int x1, int y1, int x2, int y2) {
    	return Math.abs(x1-x2) + Math.abs(y1-y2);
    }
}
