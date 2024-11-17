import sys
import heapq

readline = sys.stdin.readline

N = int(readline()) # 도시 개수
M = int(readline()) # 버스 개수
dist = [float('inf')] * (N+1)
edges = [[] for _ in range(N+1)] # 각 노드가 갈 수 있는 간선 (간선, 비용)

def dijkstra(start):
    global dist
    dist[start] = 0
    hq = []
    heapq.heappush(hq, (0, start))

    while len(hq) > 0:
        now_cost, now_node = heapq.heappop(hq)

        if dist[now_node] < now_cost:
            continue

        for edge in edges[now_node]:
            end_node, cost = edge
            new_cost = now_cost + cost
            if dist[end_node] > new_cost:
                dist[end_node] = new_cost
                heapq.heappush(hq, (new_cost, end_node))

for _ in range(M):
    s, e, c = map(int, readline().split()) # 출발 도시, 도착 도시, 비용
    edges[s].append((e, c))

S, E = map(int, readline().split()) # 최종 시작, 도착 도시
dijkstra(S)
print(dist[E]) # 출발 도시에서 도착 도시까지 가는데 드는 최소 비용