import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] inputs;
    static HashMap<Integer, Integer> map = new HashMap<>();
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        inputs = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }

        int l = 0, r = 0;
        while (r < N) {
            int rKey = inputs[r];
            map.put(rKey, map.getOrDefault(rKey, 0) + 1);

            while (map.size() > 2) {
                int lKey = inputs[l];
                map.put(lKey, map.get(lKey) - 1);
                if (map.get(lKey) == 0) {
                    map.remove(lKey);
                }
                l++;
            }

            answer = Math.max(answer, r - l + 1);
            r++;
        }
        
        System.out.println(answer);
    }
}