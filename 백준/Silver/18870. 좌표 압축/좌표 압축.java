import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] inputs;
    static PriorityQueue<int[]> sortedByRank = new PriorityQueue<>((o1, o2) -> { // (index, 원래 좌표)의 쌍을 좌표의 오름차순으로 정렬
        return Integer.compare(o1[1], o2[1]);
    });

    static HashMap<Integer, Integer> map = new HashMap<>(); // key: 좌표, value: 압축된 좌표의 순위

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        inputs = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            int num = Integer.parseInt(st.nextToken());
            inputs[i] = num;
            sortedByRank.add(new int[] {i, num});
        }

        int rank = 0;
        while (!sortedByRank.isEmpty()) {
            int[] cur = sortedByRank.poll();

            int curRank = map.getOrDefault(cur[1], -1);
            if (curRank != -1) {
                // 이미 rank가 부여된 경우
                continue;
            }

            map.put(cur[1], rank);
            rank++;
        }

        for (int i=0; i<N; i++) {
            sb.append(map.get(inputs[i]) + " ");
        }
        
        System.out.print(sb.toString());
    }
}