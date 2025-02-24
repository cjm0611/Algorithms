import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder(); // 임시 문자열 저장소
        String[] arr = br.readLine().split("");

        int answer = 0;
        String beforeAction = "";
        for (int i=0; i<arr.length; i++) {
            if (!arr[i].equals("-") && !arr[i].equals("+")) {
                sb.append(arr[i]);
                continue;
            }

           if (beforeAction.equals("")) {
                answer += Integer.parseInt(sb.toString());
                sb = new StringBuilder();
                beforeAction = arr[i];
                continue;
            }

           if (beforeAction.equals("-")) {
               answer += -1 * Integer.parseInt(sb.toString());
               sb = new StringBuilder();
               continue;
           }

           // 이전이 "+" 기호였던 경우
            answer += Integer.parseInt(sb.toString());
            sb = new StringBuilder();
            if (arr[i].equals("-")) {
                beforeAction = "-";
            }
        }

        // 마지막 숫자
        if (beforeAction.equals("-")) {
            answer += -1 * Integer.parseInt(sb.toString());
        } else {
            answer += Integer.parseInt(sb.toString());
        }

        sb = new StringBuilder();

        System.out.println(answer);
    }
}