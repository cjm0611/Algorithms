import sys
from collections import deque

readline = sys.stdin.readline

def bfs(start_x, start_y):
    visited = [[False for _ in range(M+1)] for _ in range(N+1)]
    visited[start_x][start_y] = True
    dx = [-1, 0, 1, 0]
    dy = [0, -1, 0, 1]
    dq = deque([])
    dq.append((start_x, start_y, 1))

    while dq:
        x, y, d = dq.popleft()  # 튜플을 언팩

        if x == N and y == M:
            return d

        for i in range(4):
            new_x = x + dx[i]
            new_y = y + dy[i]
            if 1 <= new_x <= N and 1 <= new_y <= M and board[new_x][new_y] == 1 and not visited[new_x][new_y]:
                dq.append((new_x, new_y, d+1))
                visited[new_x][new_y] = True

N, M = map(int, readline().split()) # 도착 지점
board = [[] for _ in range(N+1)]

for i in range(1, N+1):
    board[i] = [0] + list(map(int, readline().rstrip()))

answer = bfs(1, 1)
print(answer)

# (1, 1)에서 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수 출력
# 인접한 칸으로만 이동할 수 있음.
# bfs
# 거리는 시작, 도착 위치 포함
