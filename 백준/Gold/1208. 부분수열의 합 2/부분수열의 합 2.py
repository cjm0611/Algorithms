import sys
from itertools import combinations

readline = sys.stdin.readline
answer = 0
N, TARGET_SUM = map(int, readline().split())
inputs = list(map(int, readline().split()))
front_half = inputs[:N//2]
back_half = inputs[N//2:]

front_half_sequence = []
back_half_sequence = []

def generate_sequence(arr, IS_REVER_SORT):
    sequence = []
    for i in range(len(arr) + 1):
        combs = combinations(arr, i)
        for comb in combs:
            sequence.append(sum(comb))
        
        if IS_REVER_SORT:
            sequence.sort(reverse=True)
        else:
            sequence.sort()
    
    return sequence, len(sequence)

front_half_sequence, front_half_sequence_len = generate_sequence(front_half, False)
back_half_sequence, back_half_sequence_len = generate_sequence(back_half, True)

answer, front_idx, back_idx = 0, 0, 0
 
while front_idx < front_half_sequence_len and back_idx < back_half_sequence_len:
    front_num = front_half_sequence[front_idx]
    back_num = back_half_sequence[back_idx]
    sum = front_num + back_num

    if sum > TARGET_SUM:
        back_idx += 1
    elif sum < TARGET_SUM:
        front_idx += 1
    else: # sum == TARGET_SUM
        temp_front_idx = front_idx
        temp_back_idx = back_idx
        while temp_front_idx < front_half_sequence_len and front_half_sequence[temp_front_idx] == front_num:
            temp_front_idx += 1
        
        while temp_back_idx < back_half_sequence_len and back_half_sequence[temp_back_idx] == back_num:
            temp_back_idx += 1

        answer += (temp_back_idx - back_idx) * (temp_front_idx - front_idx)
        front_idx = temp_front_idx
        back_idx = temp_back_idx

if TARGET_SUM == 0:
    answer -= 1 # 공집합 제거

print(answer)
