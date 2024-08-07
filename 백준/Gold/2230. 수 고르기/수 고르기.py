import sys

readline = sys.stdin.readline

N, M = map(int, readline().split())
numbers = []
for i in range(N):
    numbers.append(int(readline()))

numbers.sort()

start_idx = 0
end_idx = 0
min_diff = sys.maxsize
while end_idx < N:
    diff = numbers[end_idx] - numbers[start_idx]
    if diff == M:
        min_diff = min(min_diff, diff)
        end_idx += 1
    elif diff > M:
        min_diff = min(min_diff, diff)
        start_idx += 1
    else:
        end_idx += 1

print(min_diff)

