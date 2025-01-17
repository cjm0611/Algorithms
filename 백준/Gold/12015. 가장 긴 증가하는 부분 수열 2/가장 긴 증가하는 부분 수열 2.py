import sys
import bisect

readline = sys.stdin.readline

N = int(readline())
nums = list(map(int, readline().split()))

LIS = []
for num in nums:
    idx = bisect.bisect_left(LIS, num)
    if idx == len(LIS):
        LIS.append(num)
    else:
        LIS[idx] = num

print(len(LIS))