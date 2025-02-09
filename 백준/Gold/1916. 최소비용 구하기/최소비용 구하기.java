import java.io.*;
import java.util.*;

public class Main {
    static int N, M, S, E;
    static ArrayList<ArrayList<int[]>> edges;
    static int[] dist;
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        edges = new ArrayList<>();
        for (int i=0; i<=N; i++) {
            edges.add(new ArrayList<int[]>());
        }
        for (int i=0; i<M; i++) {
            int u, v, w;
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            edges.get(u).add(new int[] {v, w});
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dijkstra();

        System.out.println(dist[E]);
    }

    static void dijkstra() {
        dist[S] = 0;

        pq = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1[1], o2[1]); // 가중치 오름차순 정렬
        });

        pq.add(new int[]{S, 0});

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int v = now[0];
            int w = now[1];

            if (dist[v] < w) {
                continue;
            }

            for (int i=0; i<edges.get(v).size(); i++) {
                int[] next = edges.get(v).get(i);
                int nv = next[0];
                int nw = next[1];
                int newCost = w + nw;
                if (dist[nv] > newCost) {
                    dist[nv] = newCost;
                    pq.add(new int[] {nv, newCost});
                }
            }
        }
    }
}
