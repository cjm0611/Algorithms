import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static int[][] board;
    static int[][] l; // 왼쪽 위에서 연속으로 이어진 길이
    static int[][] r;  // 오른쪽 위에서 연속에서 이어진 길이

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
    	st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        l = new int[R+1][C+2];
        r = new int[R+1][C+2];
        board = new int[R+1][C+1];
        for (int i=1; i<=R; i++) {
        	String s = br.readLine();
        	for (int j=1; j<=C; j++) {
        		board[i][j] = Character.getNumericValue(s.charAt(j-1));
        		if (board[i][j] == 1) {
        			l[i][j] = l[i-1][j-1] + 1;
        			r[i][j] = r[i-1][j+1] + 1;
        		}
        	}
        }
        
        int ans = 0;
        for (int i=1; i<=R; i++) {
        	for (int j=1; j<=C; j++) {
        		int size = Math.min(l[i][j],  r[i][j]); // 가장 큰 다이아몬드 후보
        		// 이게 없으면, 이것보다 작은 사이즈도 확인해야 함.
        		while (size >= 1) {
            		int idxDiff = size - 1;
            		if (r[i-idxDiff][j-idxDiff] >= size && l[i-idxDiff][j+idxDiff] >= size) {
            			ans = Math.max(ans, size);
            			break;
            		}
            		
            		size--;
        		}
        	}
        }

    	System.out.println(ans);
    }
}
