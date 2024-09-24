"""
입력
도시의 개수 N
버스의 개수 M
M개의 버스 정부 s, e, c
s -> e까지 가는 최소 비용 출력
"""
import sys
import heapq

readline = sys.stdin.readline

N = int(readline())
M = int(readline())

edges = [[] for _ in range(N+1)] # 각 노드가 갈 수 있는 정점과 비용
distance = [float('inf')] * (N+1) # 시작 노드에서 각 노드까지의 거리

for _ in range(M):
    s, e, c = map(int, readline().split())
    edges[s].append((e, c))

start, end = map(int, readline().split())
hq = []
hq.append((0, start))

while len(hq) > 0:
    now_cost, now = heapq.heappop(hq)
    if distance[now] < now_cost: # 이미 적은 가중치로 갱신되었음.
        continue
    for edge in edges[now]:
        e, cost = map(int, edge)
        new_cost = now_cost + cost
        if distance[e] > new_cost:
            distance[e] = new_cost
            heapq.heappush(hq, (new_cost, e))

print(distance[end])