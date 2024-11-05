import sys

readline = sys.stdin.readline
N = int(input())
arr = set(list(map(int, readline().split())))
M = int(input())
brr = list(map(int, readline().split()))

for b in brr:
    if b in arr:
        print(1, end=" ")
    else:
        print(0, end=" ")