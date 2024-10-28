# import sys

# readline = sys.stdin.readline

# X, Y = map(int, readline().split())
# if X == Y:
#     print(-1)
#     exit()

# rate = int((Y*100)/X)

# left = 1
# right = X
# is_change = False
# answer = -1
# while left <= right:
#     mid = (left + right) // 2 # 게임을 더 하는 횟수
#     tmp_X = X + mid
#     tmp_Y = Y + mid
#     tmp_rate = int((tmp_Y*100)/tmp_X)
#     if tmp_rate > rate:
#         answer = mid
#         right = mid - 1
#     else:
#         left = mid + 1

# print(answer)


import sys
from decimal import Decimal

readline = sys.stdin.readline

X, Y = map(int, readline().split())
if X == Y:
    print(-1)
    exit()

rate = int((Y*100)/X)

left = 1
right = X
is_change = False
while left <= right:
    mid = (left + right) // 2 # 게임을 더 하는 횟수
    tmp_X = X + mid
    tmp_Y = Y + mid
    tmp_rate = int((tmp_Y*100)/tmp_X)
    if tmp_rate == rate:
        left = mid + 1
    else:
        is_change = True
        right = mid - 1

if not is_change:
    print(-1)
else:
    left_rate = int(((Y+left)*100)/(X+left))
    if rate != left_rate:
        print(left)
    else:
        print(right)
