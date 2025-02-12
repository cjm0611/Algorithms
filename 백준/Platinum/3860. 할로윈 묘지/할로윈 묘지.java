import java.io.*;
import java.util.*;

public class Main {
	static int W, H, G, E;
	static int[][] map;
	static ArrayList<int[]> edges;
	static int[] dx = new int[] {-1, 0, 1, 0};
	static int[] dy = new int[] {0, -1, 0, 1};
	static int[][] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			if (W == 0 & H == 0) break;
			
			edges = new ArrayList<>();
			
			G = Integer.parseInt(br.readLine());
			map = new int[W][H]; // 1이면 묘비, 2이면 구멍
			for (int i=0; i<G; i++) {
				int x, y;
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				map[x][y] = 1;
			}
			
			E = Integer.parseInt(br.readLine());
			for (int i=0; i<E; i++) {
				int x1, y1, x2, y2, t;
				st = new StringTokenizer(br.readLine());
				x1 = Integer.parseInt(st.nextToken());
				y1 = Integer.parseInt(st.nextToken());
				x2 = Integer.parseInt(st.nextToken());
				y2 = Integer.parseInt(st.nextToken());
				t = Integer.parseInt(st.nextToken());
				map[x1][y1] = 2;
				edges.add(new int[] {x1, y1, x2, y2, t});
			}
			
			for (int i=0; i<W; i++) {
				for (int j=0; j<H; j++) {
					if (map[i][j] != 0) continue; // 현재 위치가 묘비거나 구멍이면 패스
					
					for (int d=0; d<4; d++) {
						int nx, ny;
						nx = i+dx[d];
						ny = j+dy[d];
						
						if (nx >= 0 && nx < W && ny >= 0 && ny < H && map[nx][ny] != 1) {
							edges.add(new int[] {i, j, nx, ny, 1});
						}
					}
				}
			}
			
			if (!bellman(0, 0)) {
				sb.append("Never\n");
				continue;
			}

			
			if (dist[W-1][H-1] == Integer.MAX_VALUE) {
				sb.append("Impossible\n");
			} else {
				sb.append(dist[W-1][H-1] + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}	
	
	static boolean bellman(int x, int y) {
		dist = new int[W+1][H+1];
		for (int i=0; i<W; i++) {
			for (int j=0; j<H; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}

		dist[x][y] = 0;
		
		int curX, curY, nextX, nextY, t;
		for (int v=1; v<=W*H-G; v++) {
			for (int i=0; i<edges.size(); i++) {
				int[] edge = edges.get(i);
				curX = edge[0];
				curY = edge[1];
				nextX = edge[2];
				nextY = edge[3];
				t = edge[4];
				
				if (dist[curX][curY] == Integer.MAX_VALUE) continue; // 시작 정점에서 도달할 수 없는 경우
				
				if (curX == W-1 && curY == H-1) continue;
				
				if (dist[nextX][nextY] > dist[curX][curY] + t) {
					dist[nextX][nextY] = dist[curX][curY] + t;
					if (v == W*H-G) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
}
