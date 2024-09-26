first_seq = [0] + list(input())
second_seq = [0] + list(input())

N = len(first_seq)
M = len(second_seq)
dp = [[0 for _ in range(M)] for _ in range(N)]

for i in range(1, N):
    for j in range(1, M):
        if first_seq[i] == second_seq[j]:
            dp[i][j] = dp[i-1][j-1] + 1
            continue

        # 일치하지 않으면 first_seq[i] 또는 second_seq[j] 중 하나는 제외
        dp[i][j] = max(dp[i-1][j], dp[i][j-1])

print(dp[N-1][M-1])