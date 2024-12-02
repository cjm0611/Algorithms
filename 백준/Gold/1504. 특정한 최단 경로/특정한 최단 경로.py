import sys
import heapq

readline = sys.stdin.readline

N, E = map(int, readline().split()) # 정점 수, 간선 수
edges = [[] for _ in range(N+1)]

def dijkstra(s, e):
    dist = [float('inf')] * (N+1)
    dist[s] = 0
    hq = []
    heapq.heappush(hq, (0, s))

    while hq:
        now_cost, now_node = heapq.heappop(hq)
        if dist[now_node] < now_cost: continue

        for edge in edges[now_node]:
            end_node, weight = edge
            new_cost = now_cost + weight
            if dist[end_node] > new_cost:
                dist[end_node] = new_cost
                heapq.heappush(hq, (new_cost, end_node))

    return dist[e]



for _ in range(E):
    a, b, w = map(int, readline().split())
    edges[a].append((b, w))
    edges[b].append((a, w))

V1, V2 = map(int, readline().split()) # 시작, 끝
# 1 -> V2 -> V1 -> N
# 1에서의 최단 경로, v1에서의 최단 경로, v2에서의 최단 경로
answer1 = dijkstra(1, V2) + dijkstra(V2, V1) + dijkstra(V1, N)
answer2 = dijkstra(1, V1) + dijkstra(V1, V2) + dijkstra(V2, N)

answer = min(answer1, answer2)

if answer == float('inf'):
    print(-1)
else:
    print(answer)
