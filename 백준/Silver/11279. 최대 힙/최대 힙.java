import java.util.*;
import java.io.*;


public class Main {
    static int N;
    static PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
        return Integer.compare(o2, o1);
    });

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        for (int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num == 0) {
                if (pq.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(pq.poll());
                }
                continue;
            }

            pq.add(num);
        }
    }
}