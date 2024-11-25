from collections import deque
import sys

readline = sys.stdin.readline

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

def bfs(x, y):
    global visited
    visited[x][y] = True
    dq = deque()
    dq.append((x, y))

    while dq:
        tmp_x, tmp_y = dq.popleft()

        for i in range(4):
            new_x = tmp_x + dx[i]
            new_y = tmp_y + dy[i]
            if new_x >= 0 and new_x < M and new_y >= 0 and new_y < N and board[new_x][new_y] == 1 and not visited[new_x][new_y]:
                visited[new_x][new_y] = True
                dq.append((new_x, new_y))

T = int(readline())
for _ in range(T):
    M, N, K = map(int, readline().split())
    board = [[0 for _ in range(N)] for _ in range(M)]
    visited = [[False for _ in range(N)] for _ in range(M)]
    for _ in range(K):
        x, y = map(int, readline().split())
        board[x][y] = 1

    answer = 0
    for i in range(M):
        for j in range(N):
            if board[i][j] == 1 and not visited[i][j]:
                answer += 1
                bfs(i, j)

    print(answer)