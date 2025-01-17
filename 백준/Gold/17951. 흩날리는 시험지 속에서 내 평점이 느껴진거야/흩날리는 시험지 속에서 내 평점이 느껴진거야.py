import sys

readline = sys.stdin.readline

"""
K개의 그룹으로 나눈 뒤,
각 그룹에서 맞은 문제의 합을 구한다.
합 중의 최솟값이 최종 점수가 된다.
답: 받을 수 있는 최대 점수
"""

N, K = map(int, readline().split()) # 시험지 수, 그룹 수
scores = list(map(int, readline().split())) # 각 시험지마다 맞은 문제 수

def check_answer(mid):
    cnt = 0
    sum_score = 0
    for score in scores:
        sum_score += score
        if sum_score >= mid:
            cnt += 1
            sum_score = 0
            
    return cnt >= K

l, r = min(scores), sum(scores)
answer = l
while l <= r:
    mid = (l+r) // 2
    if check_answer(mid):
        answer = mid
        l = mid + 1
    else:
        r = mid - 1
print(answer)
