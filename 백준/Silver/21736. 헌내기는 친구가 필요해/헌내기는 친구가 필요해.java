import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static char[][] board;
    static boolean[][] visited;
    static int answer = 0;
    static Queue<int[]> q = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new boolean[N][M];
        int[] start = new int[2];

        for (int i=0; i<N; i++) {
            char[] inputs = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                board[i][j] = inputs[j];
                if (board[i][j] == 'I') {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        visited[start[0]][start[1]] = true;
        q.add(start);

        int nx, ny;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int d=0; d<4; d++) {
                nx = now[0] + dx[d];
                ny = now[1] + dy[d];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && board[nx][ny] != 'X' && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[] {nx, ny});
                    if (board[nx][ny] == 'P') {
                        answer++;
                    }
                }
            }
        }

        System.out.println(answer == 0 ? "TT" : answer);
    }
}