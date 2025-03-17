
import java.util.*;
import java.io.*;

public class Main {
    static int N, M; // 카드 수, 카드 합체 수
    static PriorityQueue<Long> pq = new PriorityQueue<>((o1, o2) -> {
        return Long.compare(o1, o2);
    });
    static long answer = 0L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            pq.add(Long.parseLong(st.nextToken()));
        }

        for (int i=0; i<M; i++) {
            long sum = pq.poll() + pq.poll();
            pq.add(sum);
            pq.add(sum);
        }

        while (!pq.isEmpty()) {
            answer += pq.poll();
        }

        System.out.println(answer);
    }
}
