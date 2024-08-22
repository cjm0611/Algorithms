import sys

MAP_SIZE = 10
map = [list(map(int, sys.stdin.readline().split())) for _ in range(MAP_SIZE)]
left_paper_each_size = [5, 5, 5, 5, 5]
answer = float('inf')

def can_attach_paper(x_start, x_end, y_start, y_end):
    for i in range(x_start, x_end + 1):
        for j in range(y_start, y_end + 1):
            if map[i][j] == 0:
                return False
    return True

def attach_paper(x_start, x_end, y_start, y_end):
    for i in range(x_start, x_end + 1):
        for j in range(y_start, y_end + 1):
            map[i][j] = 0

def detach_paper(x_start, x_end, y_start, y_end):
    for i in range(x_start, x_end + 1):
        for j in range(y_start, y_end + 1):
            map[i][j] = 1

def back(used_paper_cnt):
    global answer
    for i in range(MAP_SIZE):
        for j in range(MAP_SIZE):
            if map[i][j] == 1:
                for paper_size in range(5):
                    new_i = i + paper_size
                    new_j = j + paper_size
                    if left_paper_each_size[paper_size] > 0 and new_i < MAP_SIZE and new_j < MAP_SIZE : # 붙일 색종이가 있는지, 맵 범위인지 확인
                        if can_attach_paper(i, new_i, j, new_j): # 색종이를 붙일 수 있는지 확인 (색종이 범위 안이 전부 0인지)
                            attach_paper(i, new_i, j, new_j)
                            left_paper_each_size[paper_size] -= 1
                            back(used_paper_cnt + 1)
                            left_paper_each_size[paper_size] += 1
                            detach_paper(i, new_i, j, new_j)
                return
    answer = min(answer, used_paper_cnt)

back(0)

if answer == float('inf'):
    print(-1)
else:
    print(answer)