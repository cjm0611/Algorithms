import sys
import heapq

readline = sys.stdin.readline

N, M = map(int, readline().split()) # 유저 수, 관계 수
edges = [[] for _ in range(N+1)]

def calculate_kevin_dist(start):
    dist = [float('inf')] * (N+1)
    dist[start] = 0
    hq = []
    heapq.heappush(hq, (0, start))

    while hq:
        now_cost, now_node = heapq.heappop(hq)
        if dist[now_node] < now_cost:
            continue

        for edge in edges[now_node]:
            end_node, weight = edge
            new_cost = now_cost + weight
            if dist[end_node] > new_cost:
                dist[end_node] = new_cost
                heapq.heappush(hq, (new_cost, end_node))

    return sum(dist[1:])

for _ in range(M):
    a, b = map(int, readline().split())
    edges[a].append((b, 1))
    edges[b].append((a, 1))

min_dist = float('inf')
answer_node = -1
for i in range(1, N+1):
    kevin_dist = calculate_kevin_dist(i)
    if kevin_dist < min_dist:
        min_dist = kevin_dist
        answer_node = i

print(answer_node)