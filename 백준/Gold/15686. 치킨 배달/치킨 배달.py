
import sys
from itertools import combinations

readline = sys.stdin.readline

def calculate_dist(a, b, c, d): # (a, b), (c, d)
    return abs(a-c) + abs(b-d)

def get_min_dist(limited_stores):
    dist_sum = 0
    for house in houses:
        a, b = house
        min_dist = float('inf')
        for store in limited_stores:
            x, y = store
            min_dist = min(min_dist, calculate_dist(a, b, x, y))
        dist_sum += min_dist
    return dist_sum

N, M = map(int, readline().split()) # 도시 크기, 치킨집 최대 M개
board = [[] for _ in range(N)]

for i in range(N):
    board[i] = list(map(int, readline().split()))

houses = []
stores = []
for i in range(N):
    for j in range(N):
        if board[i][j] == 1:
            houses.append((i, j))
        elif board[i][j] == 2:
            stores.append((i, j))

if len(stores) == M: # 2의 개수가 M이랑 같다면, 그냥 합을 구하면 됨
    answer = get_min_dist(stores)
    print(answer)
    exit()

answer = float('inf')
combs = combinations(stores, M)
for comb in combs:
    answer = min(answer, get_min_dist(comb))
print(answer)