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

    a = 0
    b = 0

    if board[x][y] == 'o':
        a += 1
    elif board[x][y] == 'v':
        b += 1

    while dq:
        tmp_x, tmp_y = dq.popleft()

        for i in range(4):
            new_x = tmp_x + dx[i]
            new_y = tmp_y + dy[i]
            if new_x >= 0 and new_x < R and new_y >= 0 and new_y < C and board[new_x][new_y] != '#' and not visited[new_x][new_y]:
                visited[new_x][new_y] = True
                dq.append((new_x, new_y))
                if board[new_x][new_y] == 'o':
                    a += 1
                elif board[new_x][new_y] == 'v':
                    b += 1

    if a == 0 and b == 0:
        return 0, 0
    elif a > b:
        return a, 0
    else:
        return 0, b

R, C = map(int, readline().split())
board = [[] for _ in range(R)]
for i in range(R):
    arr = list(readline().rstrip())
    board[i] = arr

visited = [[False for _ in range(C)] for _ in range(R)]

s_cnt = 0
w_cnt = 0

for i in range(R):
    for j in range(C):
        if board[i][j] != '#' and not visited[i][j]:
            tmp_s_cnt, tmp_w_cnt = bfs(i, j)
            s_cnt += tmp_s_cnt
            w_cnt += tmp_w_cnt

print(f"{s_cnt} {w_cnt}")