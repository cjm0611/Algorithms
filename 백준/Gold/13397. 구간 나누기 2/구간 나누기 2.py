import sys

readline = sys.stdin.readline

"""
N개의 수를 M개 이하의 구간으로 나눔.
각 구간에서 점수를 구함 (최댓값-최솟값)
구간의 점수의 최댓값의 최솟값을 구해라.
"""

N, M = map(int, readline().split()) # N개의 수, M개 이하
nums = list(map(int, readline().split()))

def check(mid): # 구간이 M개 이하로 나눠지고, 각 구간의 차이값이 mid 이하인지
    cnt = 1
    min_v, max_v = float('inf'), -1
    for num in nums:
        min_v = min(min_v, num)
        max_v = max(max_v, num)
        
        if (max_v - min_v) > mid:
            cnt += 1
            min_v = max_v = num
            if cnt > M:
                return False
        
    return True
        
l, r = 0, (max(nums)-min(nums))
answer = r
while l <= r:
    mid = (l+r) // 2
    
    if check(mid):
        answer = mid
        r = mid - 1
    else:
        l = mid + 1

print(answer)
