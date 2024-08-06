import sys

"""
5 1 3 5 10 7 4 9 2 8
5 6 9 14 24 31 35 44 46 54
"""

readline = sys.stdin.readline

N, S = map(int, readline().split())

input_numbers = list(map(int, readline().split()))
accumulated_sum = [0] # 누적합
now_sum = 0
for number in input_numbers:
    now_sum += number
    accumulated_sum.append(now_sum)

start_idx = 0
end_idx = 0
min_length = sys.maxsize

while end_idx <= N:
    temp_sum = accumulated_sum[end_idx] - accumulated_sum[start_idx]
    if temp_sum < S:
        end_idx += 1
    elif temp_sum > S:
        min_length = min(min_length, end_idx - start_idx)
        start_idx += 1
    else:
        min_length = min(min_length, end_idx - start_idx)
        end_idx += 1

if min_length == sys.maxsize:
    print(0)
else:
    print(min_length)