import java.util.*;
import java.io.*;

public class Main {
    static int A, K;
    static Queue<int[]> q = new LinkedList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       A = Integer.parseInt(st.nextToken());
       K = Integer.parseInt(st.nextToken());

       visited = new boolean[K+1];
       q.add(new int[] {A, 0});
       visited[A] = true;

       int[] first;
       int num, cnt=0;
       while (!q.isEmpty()) {
           first = q.poll();
           num = first[0];
           cnt = first[1];
           visited[num] = true;
           if (num == K) {
               break;
           }

           if (num+1 <= K && !visited[num+1]) {
               q.add(new int[]{num + 1, cnt + 1});
           }

           if (num*2 <= K && !visited[num*2]) {
               q.add(new int[]{num * 2, cnt + 1});
           }
       }

       System.out.println(cnt);
    }
}