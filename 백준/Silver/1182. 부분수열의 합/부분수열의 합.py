import sys
from itertools import combinations

readline = sys.stdin.readline
answer = 0
N, TARGET_SUM = map(int, readline().split())
numbers = list(map(int, readline().split()))
for i in range(1, N + 1):
    combs = combinations(numbers, i)
    answers = [comb for comb in combs if sum(comb) == TARGET_SUM]
    answer += len(answers)
print(answer)