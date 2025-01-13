import sys
from collections import deque
from itertools import combinations

readline = sys.stdin.readline

# A파('S') vs B파('Y')
# A파 -> 소문난 칠공주
# 7명의 여학생, 가로세로 인접, 7명 중에 최소 4명 이상 A파
# 소문난 칠공주의 경우의 수

N = 5
board = [[] for _ in range(N)]
dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]
answer = 0

def is_answer(comb):
    dq = deque([comb[0]])
    visited = set([comb[0]])
    s_cnt = 0
    connect_cnt = 1
    
    while dq:
        x, y = dq.popleft()
        if board[x][y] == 'S':
            s_cnt += 1
        
        for i in range(4):
            new_x = x + dx[i]
            new_y = y + dy[i]
            
            if (new_x, new_y) in comb and (new_x, new_y) not in visited:
                visited.add((new_x, new_y))
                dq.append((new_x, new_y))
                connect_cnt += 1
    
    return connect_cnt == 7 and s_cnt >= 4

for i in range(N):
    board[i] = list(map(str, readline().rstrip()))

positions = []
for i in range(N):
    for j in range(N):
        positions.append((i, j))

answer = 0
combs = combinations(positions, 7)
for comb in combs:
    if is_answer(comb):
        answer += 1
print(answer)