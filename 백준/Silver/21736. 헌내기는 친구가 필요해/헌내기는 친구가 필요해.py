import sys
from collections import deque

def bfs(start_x, start_y):
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]
    visited = [[False for _ in range(M)] for _ in range(N)]
    cnt = 0
    visited[start_x][start_y] = True
    q = deque()
    q.append((start_x, start_y))

    while len(q) > 0:
        x, y = q.pop()
        if board[x][y] == 'P':
            cnt += 1

        for i in range(4):
            new_x = x + dx[i]
            new_y = y + dy[i]
            if 0 <= new_x < N and 0 <= new_y < M and not visited[new_x][new_y] and board[new_x][new_y] != 'X':
                q.append((new_x, new_y))
                visited[new_x][new_y] = True
    
    return cnt

readline = sys.stdin.readline
N, M = map(int, readline().split())
board = [[] for _ in range(N)]
start_x, start_y = 0, 0
for i in range(N):
    board[i] = list(map(str, readline().rstrip()))
    if 'I' in board[i]:
        start_x = i
        start_y = board[i].index('I')
answer = bfs(start_x, start_y)
if answer == 0:
    print('TT')
else:
    print(answer)