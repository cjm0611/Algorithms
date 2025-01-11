import sys

readline = sys.stdin.readline

board = [list(map(int, readline().split())) for _ in range(9)]
blanks = []
is_answer = False

def back(depth):
    if depth == len(blanks):
        global is_answer
        is_answer = True
        return
    
    i, j = blanks[depth]
    for k in range(1, 10):
        if not check_can_put(i, j, k):
            continue
        board[i][j] = k
        back(depth + 1)
        if is_answer:
            return
        board[i][j] = 0

def check_can_put(x, y, k): # (x, y) 칸에 k를 놓을 수 있는지
    # 가로줄, 세로줄 중복 확인
    for i in range(9):
        if y != i and board[x][i] == k:
            return False
        
        if i != x and board[i][y] == k:
            return False
    
    # 3x3 중복 확인
    i_start = (x // 3) * 3
    j_start = (y // 3) * 3
    
    for i in range(i_start, i_start+3):
        for j in range(j_start, j_start+3):
            if i == x and j == y:
                continue
            if board[i][j] == k:
                return False

    return True

for i in range(9):
    for j in range(9):
        if board[i][j] == 0:
            blanks.append((i, j))

back(0)

for i in range(9):
    print(*board[i])
