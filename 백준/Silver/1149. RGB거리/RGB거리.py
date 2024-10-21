import sys

readline = sys.stdin.readline

N = int(readline())
cost = [[0, 0, 0] for _ in range(N+1)]
dp = [[float('inf'), float('inf'), float('inf')] for _ in range(N+1)]
dp[0] = [0, 0, 0]

for i in range(1, N+1):
    cost[i] = list(map(int, readline().split()))
    dp[i][0] = min(dp[i-1][1], dp[i-1][2]) + cost[i][0]
    dp[i][1] = min(dp[i-1][0], dp[i-1][2]) + cost[i][1]
    dp[i][2] = min(dp[i-1][0], dp[i-1][1]) + cost[i][2]

print(min(dp[N]))
