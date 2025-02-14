import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int ans = 0;
    	st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N+1][M+1];
        
        for (int i=1; i<=N; i++) {
        	String s = br.readLine();
        	for (int j=1; j<=M; j++) {
        		board[i][j] = Character.getNumericValue(s.charAt(j-1));
        		if (board[i][j] == 1) {
        			board[i][j] = Math.min(Math.min(board[i-1][j-1], board[i-1][j]), board[i][j-1]) + 1;
            		ans = Math.max(ans, board[i][j]);
        		}
        	}
        }
        
        System.out.println(ans*ans);
    }
}
