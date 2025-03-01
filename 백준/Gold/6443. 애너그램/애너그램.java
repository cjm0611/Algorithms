
import java.util.*;
import java.io.*;

public class Main {
    static int T;
    static char[] inputs;
//    static boolean[] visited;
    static int[] alpha;
    static HashMap<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st;
       StringBuilder sb = new StringBuilder();

       T = Integer.parseInt(br.readLine());

       for (int t=0; t<T; t++) {
           inputs = br.readLine().toCharArray();
           alpha = new int[26];
           for (char input : inputs) {
               alpha[input - 'a']++;
           }
//           Arrays.sort(inputs);

//           visited = new boolean[inputs.length];


           back(new StringBuilder(), 0);
       }
    }

    static void back(StringBuilder cur, int depth) throws IOException {
        if (depth == inputs.length) {
            System.out.println(cur.toString());
            return;
        }

        for (int i=0; i<26; i++) {
            if (alpha[i] > 0) {
                alpha[i]--;
                cur.append((char) (i+'a'));
                back(cur, depth+1);
                cur.deleteCharAt(cur.length() - 1);
                alpha[i]++;
            }
        }

//        for (int i=0; i<inputs.length; i++) {
//            if (i > 0 && inputs[i] == inputs[i-1] && visited[i-1]) continue;
//
//            if (!visited[i]) {
//                visited[i] = true;
//                cur.append(String.valueOf(inputs[i]));
//                back(cur, depth+1);
//                cur.deleteCharAt(cur.length() - 1);
//                visited[i] = false;
//            }
//        }
    }
}