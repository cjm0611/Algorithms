import sys

readline = sys.stdin.readline

N, M = map(int, readline().split()) # 기간, 인출 횟수
costs = [] # N일 동안의 지출 내역
for _ in range(N):
    cost = int(readline())
    costs.append(cost)

left = max(costs)
right = sum(costs)

def check_can(mid): # 문제의 조건을 만족하는지 판단하는 함수
    """
    남은 금액이 그날 사용할 금액보다 많더라도 남은 금액은 통장에 집어넣고 다시 K원을 인출할 수 있다
    => 인출 횟수가 M보다 작거나 같으면 됨.
    """
    cnt = 1 # 통장 인출 횟수
    left_money = mid
    for cost in costs:
        if left_money < cost:
            cnt += 1
            left_money = mid
        
        left_money -= cost
    return cnt <= M

K = 0 # 인출해야 할 최소 금액
while left <= right:
    mid = (left + right) // 2
    if check_can(mid):
        K = mid
        right = mid - 1
    else:
        left = mid + 1

print(K)