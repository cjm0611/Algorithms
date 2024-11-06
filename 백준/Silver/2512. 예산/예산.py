import sys

readline = sys.stdin.readline
N = int(input())
arr = list(map(int, readline().split())) # 각자 필요한 금액
M = int(input()) # 예산액

def calculate_sum(max_value):
    sum = 0
    for a in arr:
        if a <= max_value:
            sum += a
        else:
            sum += max_value
    return sum

if sum(arr) <= M:
    print(max(arr))
    exit()

left = 1
right = max(arr)
answer = -1 # 최대 상한액
while left <= right:
    mid = (left + right) // 2 # 임시 상한액
    if calculate_sum(mid) > M:
        right = mid - 1
    else:
        answer = mid
        left = mid + 1

print(answer)