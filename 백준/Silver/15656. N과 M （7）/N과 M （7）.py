import sys

N, M = 0, 0
sequence = []
visited = []
answer = []

def back(cnt):
    global N, M, sequence, visited, answer

    if cnt == M:
        print(' '.join(map(str, answer)))
        return
    
    for i in range(N):
        answer.append(sequence[i])
        back(cnt + 1)
        answer.pop()

readline = sys.stdin.readline
N, M = map(int, readline().split())
sequence = list(map(int, readline().split()))
sequence.sort()

back(0)