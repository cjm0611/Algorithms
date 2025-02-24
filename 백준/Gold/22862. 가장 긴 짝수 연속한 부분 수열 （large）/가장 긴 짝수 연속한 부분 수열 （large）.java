import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static boolean[] even;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        even = new boolean[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            even[i] = Integer.parseInt(st.nextToken()) % 2 == 0;
        }

        int cnt = 0; // 현재 범위에서의 짝수의 개수
        int kCnt = 0; // 현재 범위에서 패스한 홀수 개수
        int l = 1, r = 1;
        while (l <= r && r <= N) {
            if (kCnt == K) {
                if (even[r]) {
                    r++;
                    cnt++;
                    answer = Math.max(answer, cnt);
                } else {
                    if (even[l]) {
                        cnt--;
                    } else {
                        kCnt--;
                    }
                    l++;
                }
                continue;
            }

            if (even[r]) {
                r++;
                cnt++;
            } else {
                kCnt++;
                r++;
            }
        }

        answer = Math.max(answer, cnt);

        System.out.println(answer);
    }
}