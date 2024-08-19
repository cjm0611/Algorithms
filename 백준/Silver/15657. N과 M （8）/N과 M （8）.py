import sys

N, M = 0, 0
sequence = []
answer = []

def back(cnt, start):
    global N, M, sequence, answer

    if cnt == M:
        print(' '.join(map(str, answer)))
        return
    
    for i in range(start, N):
        answer.append(sequence[i])
        back(cnt + 1, i)
        answer.pop()

readline = sys.stdin.readline
N, M = map(int, readline().split())
sequence = list(map(int, readline().split()))
sequence.sort()

back(0, 0)