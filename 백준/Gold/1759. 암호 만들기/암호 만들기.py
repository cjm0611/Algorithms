import sys

readline = sys.stdin.readline

# 암호: L개의 소문자 - 최소 1개의 모음, 최소 2개의 자음
# 암호는 알파벳이 증가하는 순서로 정렬 bac (X)
# C: 암호 가능성 문자 종류

def check(arr): # 최소 한개의 모음, 최소 2개의 자음인지
    num1 = 0
    num2 = 0
    for a in arr:
        if a in ['a', 'e', 'i', 'o', 'u']:
            num1 += 1
        else:
            num2 += 1
    return num1 >= 1 and num2 >= 2

def back(depth, cur):
    global max_visited_idx
    if depth == L and check(cur):
        print(''.join(cur))
        return
    
    for i, alp in enumerate(alps):
        if i > max_visited_idx:
            tmp = max_visited_idx
            max_visited_idx = i
            cur.append(alp)
            back(depth + 1, cur)
            cur.pop()
            max_visited_idx = tmp
        

L, C = map(int, readline().split()) # 암호의 길이, 문자 종류
alps = list(map(str, readline().split()))
alps.sort()
max_visited_idx = -1
back(0, [])