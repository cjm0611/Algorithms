import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] inputs;
    static ArrayList<Integer> d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        inputs = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
        	inputs[i] = Integer.parseInt(st.nextToken());
        }
        
        d = new ArrayList<>();
        d.add(inputs[1]);
        
        for (int i=2; i<=N; i++) {
        	int idx = lowerBound(d, inputs[i]);
        	if (idx == d.size()) {
        		d.add(inputs[i]);
        	} else {
        		d.set(idx,  inputs[i]);
        	}
        }
        
        sb.append(d.size() + "\n");
        System.out.print(sb.toString());
    }
    
    static int lowerBound(ArrayList<Integer> arr, int target) {
    	int l, r;
    	l = 0;
    	r = arr.size();
    	
    	while (l < r) {
    		int mid = (l+r) / 2;
    		if (arr.get(mid) >= target) {
    			r = mid;
    		} else {
    			l = mid+1;
    		}
    	}
    	
    	return l;
    }
}
