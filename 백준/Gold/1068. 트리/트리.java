import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayList<ArrayList<Integer>> edges;
    static int startNode, removeNode;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        edges = new ArrayList<>();
        for (int i=0; i<N; i++) {
            edges.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            int p = Integer.parseInt(st.nextToken());
            if (p == -1) {
                startNode = i;
                continue;
            }

            edges.get(p).add(i);
        }

        removeNode = Integer.parseInt(br.readLine());

        dfs(startNode);

        System.out.println(answer);
    }

    static void dfs(int curNode) {
        if (curNode == removeNode) return;

        if (edges.get(curNode).isEmpty()) {
            answer += 1;
            return;
        }

        if (edges.get(curNode).size() == 1 && edges.get(curNode).get(0) == removeNode) {
            answer += 1;
            return;
        }

        for (int i=0; i<edges.get(curNode).size(); i++) {
            dfs(edges.get(curNode).get(i));
        }
    }
}