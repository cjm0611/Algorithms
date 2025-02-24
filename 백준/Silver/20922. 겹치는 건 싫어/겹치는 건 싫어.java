import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static int[] inputs;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        inputs = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0, r = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        while (l <= r && r < inputs.length) {
            int rKey = inputs[r];
            int rCnt = map.getOrDefault(rKey, 0);
            if (rCnt < K) {
                map.put(rKey, rCnt+1);
                answer = Math.max(answer, r-l+1);
                r++;
                continue;
            }

            int lKey = inputs[l];
            int lCnt = map.get(lKey);
            map.put(lKey, lCnt-1);
            l++;
        }

        System.out.println(answer);
    }
}