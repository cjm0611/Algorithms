import sys

readline = sys.stdin.readline

"""
인접의 기준
학생 N^2 => 각자 4명씩 선호도 조사
|r1 - r2| + |c1 - c2| = 1 => 상하좌우
"""

N = int(readline())
faves = [[] for _ in range(N*N + 1)] # i번째 학생이 좋아하는 리스트
order = []
for _ in range(N*N):
    arr = list(map(int, readline().split()))
    order.append(arr[0])
    faves[arr[0]] = arr[1:]

board = [[0 for _ in range(N)] for _ in range(N)]
dx = [-1, 0, 1, 0]
dy = [0, -1, 0, 1]
direcs = [(-1, 0), (0, -1), (1, 0), (0, 1)]

def calculate_cnt(x, y, stu):
    like_cnt = 0 # 주변의 좋아하는 학생 수
    blank_cnt = 0 # 비어있는 칸이 많은 칸
    for dx, dy in direcs:
        nx, ny = x + dx, y + dy
        if 0 <= nx < N and 0 <= ny < N:
            if board[nx][ny] in faves[stu]:
                like_cnt += 1
            elif board[nx][ny] == 0:
                blank_cnt += 1
    return like_cnt, blank_cnt

def calculate_answer():
    total_score = 0
    # 인접한 칸에 좋아하는 학생의 수 => 0명: 0, 1명: 1, 2명: 10, 3명: 100, 4명: 1000
    score = [0, 1, 10, 100, 1000]
    for i in range(N):
        for j in range(N):
            like_cnt = 0 # 주변의 좋아하는 학생 수
            for dx, dy in direcs:
                nx, ny = i + dx, j + dy
                if 0 <= nx < N and 0 <= ny < N:
                    stu_num = board[i][j]
                    if board[nx][ny] in faves[stu_num]:
                        like_cnt += 1
            total_score += score[like_cnt]
    return total_score    
        

for stu in order:
    most_like_cnt = -1
    most_blank_cnt = -1 # 좋아하는 사람이 가장 많은 경우의 인접한 빈칸 수
    position = (-1, -1)
    for i in range(N):
        for j in range(N):
            if board[i][j] != 0:
                continue
            
            like_cnt, blank_cnt = calculate_cnt(i, j, stu)
        
            if like_cnt < most_like_cnt:
                continue
            
            if like_cnt > most_like_cnt:
                position = (i, j)
                most_like_cnt, most_blank_cnt = like_cnt, blank_cnt
                continue
            
            if like_cnt == most_like_cnt and blank_cnt > most_blank_cnt:
                position = (i, j)
                most_blank_cnt = blank_cnt
                continue
            
            if like_cnt == most_like_cnt and blank_cnt == most_blank_cnt:
                if i < position[0]:
                    position = (i, j)
                elif i == position[0] and j < position[1]:
                    psoition = (i, j)
    
    board[position[0]][position[1]] = stu

answer = calculate_answer() # 학생의 만족도의 총 합
print(answer)