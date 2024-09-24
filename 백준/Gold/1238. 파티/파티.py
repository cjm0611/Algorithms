"""
- 문제 요약
N명의 학생들이 X번 마을에 모임.
해당 마을에 갔다가, 되돌아 와야 하는데 총 비용이 가장 큰 경우를 찾아라.
(s -> e, e -> s 따로 구해서 그 합이 가장 큰 값을 찾아야 함)


- 입력
학생 수 N, 간선 수 M, 특정 마을 X
M개 만큼 s, e, c

- 출력
갔다 오는데 가장 오래 걸리는 시간

- 각 s 지점에서 X까지 걸리는 시간 각각 계산
- 특정 마을 X에서 가는 시간도 계산

놓쳤던 부분: 파티가 진행되는 곳에 사는 학생의 dist는 0으로 만들어야 함.
"""

import sys
import heapq

def dijkstra(start, X, edges): # 시작 지점부터 끝 지점
    dist = [float('inf') for _ in range(N+1)]
    if start == X:
        dist[X] = 0
    hq = []
    heapq.heappush(hq, (0, start))

    while len(hq) > 0:
        now_cost, now = heapq.heappop(hq)
        if dist[now] < now_cost:
            continue

        for edge in edges[now]:
            e, c = edge
            new_dist = now_cost + c
            if dist[e] > new_dist:
                dist[e] = new_dist
                heapq.heappush(hq, (new_dist, e))

    return dist

readline = sys.stdin.readline
N, M, X = map(int, readline().split()) # 학생, 간선, 도착지
edges = [[] for _ in range(N+1)]
for _ in range(M):
    s, e, c = map(int, readline().split())
    edges[s].append((e, c))

# 도착 지점 -> 각 노드
dist_from_start = dijkstra(X, X, edges)

# 각 노드 -> 도착 지점
answer = -1
for s in range(1, N+1):
    dist_to_end = dijkstra(s, X, edges)
    answer = max(answer, dist_from_start[s] + dist_to_end[X])

print(answer)