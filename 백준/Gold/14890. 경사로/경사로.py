import sys

readline = sys.stdin.readline

"""
1. 한 행 또는 한 열에 속한 모든 칸의 높이가 같거나
2. 경사로를 놓는다. (높이 1, 길이 L, 개수 무제한)
"""

N, M = map(int, readline().split()) # 지도 길이, 경사로 길이
board = [list(map(int, readline().split())) for _ in range(N)]

answer = 0 # 갈 수 있는 경로 수

def is_valid_path(path):
    v = path[0]
    if all(v == i for i in path):
        return True
    
    now = path[0]
    cnt = 1
    is_fail = False
    j = 1
    while j < N:
        if now == path[j]:
            cnt += 1
            j += 1
            continue
        
        if abs(now - path[j]) > 1: # 차이가 1 이상 나면 실패
            is_fail = True
            break
        
        if now < path[j]:
            if cnt < M:
                # 다리를 놓을 수 없어서 실패
                is_fail = True
                break
            
            now = path[j]
            cnt = 1
            j += 1
            continue
        
        # now > path[j]인 경우
        now = path[j]
        cnt = 1
        
        while j < (N-1):
            if path[j+1] != now:
                break
            cnt += 1
            j += 1
        
        if cnt < M:
            # 다리를 놓을 수 없어서 실패
            is_fail = True
            break
        else:
            cnt -= M
            j += 1
        
    if not is_fail:
        return True
    else:
        return False

for row in board:
    if is_valid_path(row):
        answer += 1

for col in zip(*board):
    if is_valid_path(list(col)):
        answer += 1

print(answer)