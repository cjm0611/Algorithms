from collections import deque

first_seq = [0] + list(input())
second_seq = [0] + list(input())

N = len(first_seq)
M = len(second_seq)
dp = [[(0, 0) for _ in range(M)] for _ in range(N)] # 튜플 리스트 (값, 어디에서 왔는지 - 0, 1, 2 (i-1, j-1, 일치))

for i in range(1, N):
    for j in range(1, M):
        if first_seq[i] == second_seq[j]:
            dp[i][j] = (dp[i-1][j-1][0] + 1, 2)
            continue

        # 일치하지 않으면 first_seq[i] 또는 second_seq[j] 중 하나는 제외
        if dp[i-1][j] > dp[i][j-1]:
            dp[i][j] = (dp[i-1][j][0], 0)
        else:
            dp[i][j] = (dp[i][j-1][0], 1)

print(dp[N-1][M-1][0])

# 역추적
answer = deque()
i, j = N - 1, M - 1
while i > 0 and j > 0:
    origin = dp[i][j][1]
    if origin == 2:
        answer.appendleft(first_seq[i])
        i -= 1
        j -= 1
    elif origin == 1:
        j -= 1
    else:
        i -= 1

print(''.join(answer))