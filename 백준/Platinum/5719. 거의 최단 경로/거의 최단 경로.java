
import java.io.*;
import java.util.*;

public class Main {
    static int V, E, S, D;
    static ArrayList<ArrayList<int[]>> edges;
    static ArrayList<ArrayList<int[]>> reverseEdges;
    static int[] dist;
    static boolean[] used;
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            used = new boolean[E];

            if (V == 0 && E == 0) break;

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken()); // 시작 정점
            D = Integer.parseInt(st.nextToken()); // 도착 정점

            edges = new ArrayList<>();
            reverseEdges = new ArrayList<>();
            for (int i=0; i<V; i++) {
                edges.add(new ArrayList<>());
                reverseEdges.add(new ArrayList<>());
            }

            for (int i=0; i<E; i++) {
                int u, v, w;

                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                w = Integer.parseInt(st.nextToken());

                edges.get(u).add(new int[] {v, w, i});
                reverseEdges.get(v).add(new int[] {u, w, i});
            }

            pq = new PriorityQueue<>((o1, o2) -> {
                return Integer.compare(o1[1], o2[1]); // 가중치 오름차순 정렬
            });

            dijkstra(S);

            bfs(D); // 간선 삭제

            dijkstra(S);
            if (dist[D] == Integer.MAX_VALUE) {
                sb.append(-1 + "\n");
            } else {
                sb.append(dist[D] + "\n");
            }
        }

        System.out.print(sb.toString());
    }

    static void dijkstra(int start) {
        dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0;
        pq.add(new int[] {start, 0});

        int cur, cost, next, nextCost, nextIdx, newCost;
        while (!pq.isEmpty()) {
            cur = pq.peek()[0];
            cost = pq.peek()[1];
            pq.poll();

            if (dist[cur] < cost) {
                continue;
            }

            for (int i=0; i<edges.get(cur).size(); i++) {
                next = edges.get(cur).get(i)[0];
                nextCost = edges.get(cur).get(i)[1];
                nextIdx = edges.get(cur).get(i)[2];

                newCost = cost + nextCost;
                if (dist[next] > newCost && !used[nextIdx]) {
                    dist[next] = newCost;
                    pq.add(new int[] {next, newCost});
                }
            }
        }
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        int cur, curCost, next, nextCost, nextIdx;
        while (!q.isEmpty()) {
            cur = q.poll();

            for (int i=0; i<reverseEdges.get(cur).size(); i++) {
                next = reverseEdges.get(cur).get(i)[0];
                nextCost = reverseEdges.get(cur).get(i)[1];
                nextIdx = reverseEdges.get(cur).get(i)[2];

                if (used[nextIdx]) {
                    continue;
                }

                if (dist[cur] == dist[next] + nextCost) { // 해당 간선은 삭제
                    used[nextIdx] = true;
                    q.add(next);
                }
            }
        }
    }
}
