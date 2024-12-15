import sys
from collections import deque

readline = sys.stdin.readline

def bfs(start):
    visited = [False] * (N+1)
    visited[start] = True
    answer = 0
    q = deque()
    q.append(start)

    while q:
        now_node = q.popleft()
        for end_node in edges[now_node]:
            if not visited[end_node]:
                visited[end_node] = True
                q.append(end_node)
                answer += 1

    return answer


N = int(readline()) # 컴퓨터 수
edges = [[] for _ in range(N+1)]
M = int(readline()) # 컴퓨터 쌍의 수
for _ in range(M):
    a, b = map(int, readline().split()) # 양방향
    edges[a].append(b)
    edges[b].append(a)

print(bfs(1)) # 1번 컴퓨터가 바이러스에 걸렸을 때, 바이러스에 걸리는 컴퓨터 수 (1번 제외)