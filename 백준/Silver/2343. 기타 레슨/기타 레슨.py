import sys

readline = sys.stdin.readline
N, M = map(int, readline().split())
arr = list(map(int, readline().split()))

left = max(arr)
right = sum(arr)

answer = right

while left <= right:
    mid = (left + right) // 2
    total = 0
    count = 1
    
    for length in arr:
        if total + length > mid:
            count += 1
            total = length
        else:
            total += length
    
    if count <= M:
        answer = mid
        right = mid - 1
    else:
        left = mid + 1

print(answer)
