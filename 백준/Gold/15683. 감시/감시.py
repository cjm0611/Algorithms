import sys
from copy import deepcopy

readline = sys.stdin.readline

direcs = {
    # 상: (-1, 0) / 하: (1, 0) / 좌: (0, -1) / 우: (0, 1)
    1: [
        # 상 / 하 / 좌 / 우
        [(-1, 0)],
        [(1, 0)],
        [(0, -1)],
        [(0, 1)]
    ],
    2: [
        # 상 하 / 좌 우
        [(-1, 0), (1, 0)],
        [(0, -1), (0, 1)]
    ],
    3: [
        #  상 우 / 우 하 / 하 좌 / 좌 상
        [(-1, 0), (0, 1)],
        [(0, 1), (1, 0)],
        [(1, 0), (0, -1)],
        [(0, -1), (-1, 0)],
    ],
    4: [
        # 상하좌우에서 한번씩 빼기
        # 상: (-1, 0) / 하: (1, 0) / 좌: (0, -1) / 우: (0, 1)
        [(1, 0), (0, -1), (0, 1)],
        [(-1, 0), (0, -1), (0, 1)],
        [(-1, 0), (1, 0), (0, 1)],
        [(-1, 0), (1, 0), (0, -1)]
    ],
    5: [
        # 상 하 좌 우
        [(-1, 0), (1, 0), (0, -1), (0, 1)]
    ]
}

def dfs(depth, tmp_board):
    global answer
    
    if depth == len(cctvs):
        blind_cnt = sum(row.count(0) for row in tmp_board)
        answer = min(answer, blind_cnt)
        return
    
    x, y, cctv_num = cctvs[depth]
    for direc in direcs[cctv_num]:
        new_board = deepcopy(tmp_board)
        
        for dx, dy in direc:
            nx, ny = x + dx, y + dy
            while 0 <= nx < N and 0 <= ny < M and new_board[nx][ny] != 6:
                if new_board[nx][ny] == 0:
                    new_board[nx][ny] = '#'
                nx += dx
                ny += dy
        
        dfs(depth+1, new_board)

N, M = map(int, readline().split())
board = [list(map(int, readline().split())) for _ in range(N)]   # 0: 빈칸, 1~5: CCTV 번호, 6: 벽

cctvs = [] # cctv 위치, 번호
for i in range(N):
    for j in range(M):
        if 1 <= board[i][j] <= 5:
            cctvs.append((i, j, board[i][j]))

answer = float('inf') # 최소 사각지대 수
dfs(0, board)
print(answer)