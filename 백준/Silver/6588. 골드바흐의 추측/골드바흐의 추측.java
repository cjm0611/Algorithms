import java.io.*;
import java.util.*;

public class Main {
	static int N = 1;
	static int MAX = 1000001;
	static int[] prime = new int[MAX]; // 0이면 소수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		prime[0] = 1;
		prime[1] = 1;
		for (int i=2; i<MAX; i++) {
			if (prime[i] == 0) {
				for (int j=i*2; j<MAX; j+=i) {
					prime[j] = 1;
				}
			}
		}

		prime[2] = 1;
		
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) break;
			findNum();
		}
	}
	
	static void findNum() { 
		for (int i=N-1; i>1; i--) {
			int left = N-i;
			if (prime[i] == 0 && prime[left] == 0) {
				System.out.println(N + " = " + left + " + " + i);
				return;
			}
		}

		System.out.println("Goldbach's conjecture is wrong.");
	}
}