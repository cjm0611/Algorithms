import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K, M;
    static int[] arr; // 처음에 시청하는 동영상 번호
    static int[][] parent;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        ans = new int[N+1];
        parent = new int[31][K+1]; // K개 동영상의 2^k번째 후의 번호
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=K; i++) {
            parent[0][i] = Integer.parseInt(st.nextToken()); 
        }
        for (int i=1; i<=30; i++) {
            for (int j=1; j<=K; j++) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }
        
        for (int i=1; i<=N; i++) {
            int num = arr[i];
            int k = M-1;
            for (int j=30; j>=0; j--) {
                if (k >= (1 << j)) {
                    k -= (1 << j);
                    num = parent[j][num];
                }
            }
            sb.append(num+" ");
        }
        System.out.println(sb.toString());

    }
}