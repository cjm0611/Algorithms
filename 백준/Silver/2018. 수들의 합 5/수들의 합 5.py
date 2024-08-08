import sys

realine = sys.stdin.readline
N = int(realine())

left = 1 # 왼쪽 포인터
right = 1 # 오른쪽 포인터
sum = 1 # 현재까지의 합
answer_count = 0

while right <= N: # right가 오른쪽 끝에 도달할 때까지 진행
    if sum > N:
        sum -= left
        left += 1
    elif sum < N:
        right += 1
        sum += right
    else: # sum == N
        answer_count += 1
        right += 1
        sum += right

print(answer_count)

