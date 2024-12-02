import sys
import heapq

readline = sys.stdin.readline
V, E = map(int, readline().split()) # 정점, 간선 개수
K = int(readline().rstrip()) # 시작 정점
edges = [[] for _ in range(V+1)]
dist = [float('inf')] * (V+1)

def dijkstra():
    dist[K] = 0
    hq = []
    heapq.heappush(hq, (0, K))

    while hq:
        now_cost, now_node = heapq.heappop(hq)

        if dist[now_node] < now_cost:
            continue

        for edge in edges[now_node]:
            end_node, weight = edge
            new_cost = now_cost + weight
            if dist[end_node] > new_cost:
                heapq.heappush(hq, (new_cost, end_node))
                dist[end_node] = new_cost

for _ in range(E):
    s, e, w = map(int, readline().split())
    edges[s].append((e, w))


dijkstra()

for v in range(1, V+1):
    if dist[v] == float('inf'):
        print("INF")
    else:
        print(dist[v])
