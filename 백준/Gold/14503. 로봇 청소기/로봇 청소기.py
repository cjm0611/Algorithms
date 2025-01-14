import sys
from collections import deque

readline = sys.stdin.readline

"""
현재 칸 청소
1. 주변 4칸 중 빈칸이 없는 경우
방향 유지 후 후진
불가능하다면 작동 정지

2. 빈칸이 있는 경우
반시계 방향으로 90도 회전
앞쪽 칸이 청소되지 않은 빈칸이라면 한 칸 전진
"""

N, M = map(int, readline().split()) # N x M
r, c, d = map(int, readline().split()) # 시작 위치, 방향 d

board = [[] for _ in range(N)]
for i in range(N):
    board[i] = list(map(int, readline().split()))

visited = [[False for _ in range(M)] for _ in range(N)]

dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]

# 북: 0, 동: 1, 남: 2, 서: 3
go = [(-1, 0), (0, 1), (1, 0), (0, -1)]
back = [(1, 0), (0, -1), (-1, 0), (0, 1)]

def check_blank(x, y): # 현재 칸의 주변 4칸 중 청소되지 않은 빈칸이 있는지 확인
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        
        if 0 <= nx < N and 0 <= ny < M and board[nx][ny] == 0 and not visited[nx][ny]:
            return True
    
    return False

def can_go_back(x, y): # 한 칸 후진할 수 있는지 확인
    # 북: 0, 동: 1, 남: 2, 서: 3
    nx, ny = x + back[d%4][0], y + back[d%4][1]
    if 0 <= nx < N and 0 <= ny < M and board[nx][ny] == 0:
        return True
    else:
        return False

answer = 0 # 청소하는 칸의 개수
now_x, now_y = r, c
while True:
    if board[now_x][now_y] == 0 and not visited[now_x][now_y]:
        answer += 1
        visited[now_x][now_y] = True
    
    if check_blank(now_x, now_y):
        # 반 시계 방향으로 회전
        # 북 -> 서, 동 -> 북, 남 -> 동, 서 -> 남
        d = (d+3) % 4
        
        # 앞쪽 칸이 청소되지 않은 경우 한 칸 전진
        nx = now_x + go[d][0]
        ny = now_y + go[d][1]
        if 0 <= nx < N and 0 <= ny < M and board[nx][ny] == 0 and not visited[nx][ny]:
            now_x, now_y = nx, ny
    else:
        if can_go_back(now_x, now_y):
            now_x += back[d][0]
            now_y += back[d][1]
        else: # 후진 못하면 작동 멈춤
            break

print(answer)