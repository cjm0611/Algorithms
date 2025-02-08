import java.io.*;
import java.util.*;

public class Main {
	static int N = 1;
	static int MAX = 4000001;
	static int[] prime = new int[MAX]; // 0이면 소수
	static ArrayList<Integer> pSum = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		prime[0] = 1;
		prime[1] = 1;
		
		int sum = 0;
		for (int i=2; i<MAX; i++) {
			if (prime[i] == 0) {
				sum += i;
				pSum.add(sum);
				for (int j=i*2; j<MAX; j+=i) {
					prime[j] = 1;
				}
			}
		}
		
		N = Integer.parseInt(br.readLine());
		int cnt = 0;
		int l = 0, r = 0;
		int tmpSum;
		while (l <= r && r < pSum.size()) {
			if (l == 0) {
				tmpSum = pSum.get(r);
			} else {
				tmpSum = pSum.get(r) - pSum.get(l-1);
			}
			
			if (tmpSum == N) {
				cnt++;
				r++;
			} else if (tmpSum > N) {
				l++;
			} else {
				r++;
			}
		}
		System.out.println(cnt);
	}
}