import sys

readline = sys.stdin.readline
N = int(readline())
seq = list(map(int, readline().split()))
dp = [1] * N
answer = 0

for i in range(N):
    max_len = 0
    for j in range(i+1):
        if seq[i] > seq[j]:
            dp[i] = max(dp[i], dp[j] + 1)
    answer = max(answer, dp[i])

print(answer)
