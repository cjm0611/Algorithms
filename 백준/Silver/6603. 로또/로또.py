import sys
from itertools import combinations

readline = sys.stdin.readline
input = readline().rstrip('\n')
while input != '0':
    arr = list(map(int, input.split()))
    k = arr[0]
    arr = arr[1:]
    # print(f"arr: {arr} / k: {k}")
    LOTTO_LEN = 6
    combs = combinations(arr, LOTTO_LEN)
    for comb in combs:
        output = ' '.join(map(str, comb))
        print(output)
    print()
    input = readline().rstrip('\n')