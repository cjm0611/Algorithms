import sys

readline = sys.stdin.readline
row_len, column_len = map(int, readline().split())
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]
map = [[0 for _ in range(column_len)] for _ in range(row_len)]
alpha_visited = [False] * 26

for row_idx in range(row_len):
    map[row_idx] = list(readline())

answer = 0

def get_alpha_idx(alpha):
    return ord(alpha) - 65

def can_go(x, y):
    return x >= 0 and x < row_len and y >= 0 and y < column_len and not alpha_visited[get_alpha_idx(map[x][y])]

def back(x, y, cnt):
    global answer   
    
    IS_CAN_GO = False 
    for i in range(4):
        new_x = x + dx[i]
        new_y = y + dy[i]

        if can_go(new_x, new_y):
            IS_CAN_GO = True
            alpha_visited[get_alpha_idx(map[new_x][new_y])] = True
            back(new_x, new_y, cnt + 1)
            alpha_visited[get_alpha_idx(map[new_x][new_y])] = False
        
    if not IS_CAN_GO:
        answer = max(answer, cnt)

alpha_visited[get_alpha_idx(map[0][0])] = True
back(0, 0, 1)
print(answer)