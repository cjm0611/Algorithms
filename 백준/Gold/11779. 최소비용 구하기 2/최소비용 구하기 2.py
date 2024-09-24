"""
heapq에 넣을 때 해당 노드까지의 리스트도 업데이트
깊은 복사 + edge 돌아가면서 리스트에 요소 append

출력
1. 최소 비용
2. 최소 비용에 속해있는 도시의 수
3. 최소 비용에 속해있는 도시 (순서대로, 시작과 도착 노드 포함)

디버깅한 것
시작 노드에서의 dist는 0
"""

import sys
import heapq
import copy
readline = sys.stdin.readline
n = int(readline()) # 노드의 수
m = int(readline()) # 간선의 수

edges = [[] for _ in range(n+1)] # 특정 노드에서 갈 수 있는 간선 정보를 저장하는 (node, cost) 튜플 리스트
for _ in range(m):
    s, e, c = map(int, readline().split())
    edges[s].append((e, c))

start, end = map(int, readline().split()) # 최종 출발점, 도착점
dist = [float('inf') for _ in range(n+1)]
path = [[] for _ in range(n+1)] # 시작점에서 해당 노드까지 갔을 때의 경로
hq = []
hq.append((0, start))
dist[start] = 0
path[start].append(start)

while len(hq) > 0:
    now_cost, now = heapq.heappop(hq)
    if dist[now] < now_cost: continue

    for edge in edges[now]:
        e, c = edge 
        new_cost = now_cost + c
        if dist[e] > new_cost:
            dist[e] = new_cost
            heapq.heappush(hq, (new_cost, e))
            path[e] = copy.deepcopy(path[now])
            path[e].append(e)

print(dist[end])
print(len(path[end]))
print(*path[end])