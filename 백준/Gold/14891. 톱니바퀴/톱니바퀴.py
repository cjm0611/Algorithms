import sys
from collections import deque

readline = sys.stdin.readline

"""
[접합부 인덱스 정리]
1번 바퀴 오: 2
2번 바퀴 왼: 6, 오:2
3번 바퀴 왼: 6, 오:2
4번 바퀴 왼: 6

시계 방향 회전 -> deque의 pop -> appendleft
반시계 방향 회전 -> deque의 popleft -> append

회전 방향
1, 3 / 2, 4 => 2로 나눴을 때의 나머지가 같다면 같은 방향
"""

ws = [deque([])] + [deque(list(map(int, readline().rstrip()))) for _ in range(4)]


def get_effected_wheels(w_num): # 현재 w_num이 움직일 때 영향을 받을 바퀴
    effected_wheels = [w_num]
    if w_num == 1 and ws[1][2] != ws[2][6]:
        effected_wheels.append(2)
        if ws[2][2] != ws[3][6]:
            effected_wheels.append(3)
            if ws[3][2] != ws[4][6]:
                effected_wheels.append(4)

    elif w_num == 2:
        if ws[2][6] != ws[1][2]:
            effected_wheels.append(1)
        if ws[2][2] != ws[3][6]:
            effected_wheels.append(3)
            if ws[3][2] != ws[4][6]:
                effected_wheels.append(4)
    
    elif w_num == 3:
        if ws[4][6] != ws[3][2]:
            effected_wheels.append(4)
        if ws[3][6] != ws[2][2]:
            effected_wheels.append(2)
            if ws[2][6] != ws[1][2]:
                effected_wheels.append(1)
    
    elif w_num == 4 and ws[4][6] != ws[3][2]:
        effected_wheels.append(3)
        if ws[3][6] != ws[2][2]:
            effected_wheels.append(2)
            if ws[2][6] != ws[1][2]:
                effected_wheels.append(1)
    
    return effected_wheels

def rotate_wheel(w_num, direc):
    global ws
    if direc == -1: # 반시계 회전
        a = ws[w_num].popleft()
        ws[w_num].append(a)
        
    else: # 시계 회전
        a = ws[w_num].pop()
        ws[w_num].appendleft(a)

def rotate_wheels(w_num, effected_wheels, direc):
    for wheel in effected_wheels:
        if wheel % 2 == w_num % 2:
            # 같은 방향으로 회전
            rotate_wheel(wheel, direc)
        else:
            # 다른 방향으로 회전
            if direc == -1:
                rotate_wheel(wheel, 1)
            else:
                rotate_wheel(wheel, -1)
                
K = int(readline()) # 회전 수
for _ in range(K):
    w_num, direc = map(int, readline().split())
    effected_wheels = get_effected_wheels(w_num)
    rotate_wheels(w_num, effected_wheels, direc)

answer = 0
score = [0, 1, 2, 4, 8]
for i in range(1, 5):
    if ws[i][0] == 1:
        answer += score[i]
print(answer)