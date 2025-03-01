import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int treeNum = 1000001;
	static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        		
    	int a, b, c;
        N = Integer.parseInt(br.readLine());
        tree = new int[4*treeNum];
//         init(1, 0, N-1);
        for (int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	a = Integer.parseInt(st.nextToken());

        	if (a == 1) {
        		b = Integer.parseInt(st.nextToken());
        		int candyIdx = query(1, 0, treeNum-1, b);
        		sb.append((candyIdx+1) + "\n");
        		update(1, 0, treeNum-1, candyIdx, -1);
        	} else {
        		b = Integer.parseInt(st.nextToken());
        		c = Integer.parseInt(st.nextToken());
        		update(1, 0, treeNum-1, b-1, c);
        	}
        }
        
        System.out.print(sb.toString());
    }
    
    static int init(int node, int start, int end) {
    	if (start == end) {
    		// tree[node] = arr[start];
    		return 0;
    	}
    	
    	int mid = (start+end)/2;
    	return tree[node] = init(node*2, start, mid) + init(node*2+1, mid+1, end);
    }
    
    static int query(int node, int start, int end, int target) {
    	if (start == end) {
    		return start;
    	}

    	int mid = (start+end)/2;
    	if (tree[node*2] >= target) {
    		return query(node*2, start, mid, target);
    	} else {
    		return query(node*2+1, mid+1, end, target-tree[node*2]);
    	}    			
    }
    
    static void update(int node, int start, int end, int idx, int diff) {
    	if (idx < start || idx > end) {
    		return;
    	}
    	
    	tree[node] += diff;
    	
    	if (start != end) {
    		int mid = (start+end)/2;
    		update(node*2, start, mid, idx, diff);
    		update(node*2+1, mid+1, end, idx, diff);
    	}
    }
}
