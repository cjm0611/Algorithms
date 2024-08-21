boardSize = int(input())
queens = []
answer = 0
columns = [False] * boardSize

def can_put_chess(now_i, now_j):
    for queen in queens:
        i, j = queen
        diff_i = abs(now_i - i)
        diff_j = abs(now_j - j)
        if diff_i == diff_j:
            return False

    return True

def put_chess(row):
    global answer, queens
    if row ==  boardSize:
        answer += 1
        return
    
    for j in range(boardSize):
        if not columns[j] and can_put_chess(row, j):
            queens.append((row, j))
            columns[j] = True
            put_chess(row + 1)
            queens.remove((row, j))
            columns[j] = False

put_chess(0)
print(answer)