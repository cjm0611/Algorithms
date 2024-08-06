import sys

readline = sys.stdin.readline

N, M = map(int, readline().split())
arr = list(map(int, readline().split()))
brr = list(map(int, readline().split()))

arr_idx = 0
brr_idx = 0
answer = []

while arr_idx < len(arr) and brr_idx < len(brr):
    a = arr[arr_idx]
    b = brr[brr_idx]
    if a < b:
        answer.append(a)
        arr_idx += 1
    else:
        answer.append(b)
        brr_idx += 1

while arr_idx < len(arr):
    answer.append(arr[arr_idx])
    arr_idx += 1

while brr_idx < len(brr):
    answer.append(brr[brr_idx])
    brr_idx += 1

output = ' '.join(map(str, answer))
print(output)