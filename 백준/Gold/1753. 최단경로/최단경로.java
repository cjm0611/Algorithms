import java.io.*;
import java.util.*;

public class Main {
    static int V, E, K;
    static ArrayList<ArrayList<int[]>> edges;
    static int[] dist;
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        K = Integer.parseInt(br.readLine()); // 시작 정점
        edges = new ArrayList<>();
        for (int i=0; i<=V; i++) {
            edges.add(new ArrayList<int[]>());
        }
        for (int i=0; i<E; i++) {
            int u, v, w;
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            edges.get(u).add(new int[] {v, w});
        }

        dijkstra();

        for (int i=1; i<=V; i++) {
            System.out.println(dist[i] != Integer.MAX_VALUE ? dist[i] : "INF");
        }
    }

    static void dijkstra() {
        dist[K] = 0;

        pq = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1[1], o2[1]); // 가중치 오름차순 정렬
        });

        pq.add(new int[]{K, 0});

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
