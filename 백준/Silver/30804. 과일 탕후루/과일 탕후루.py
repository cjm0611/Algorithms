import sys
from collections import defaultdict

def calculate_max_fruits_len(numbers):
    fruits_dic = defaultdict(int)
    left_idx = 0
    right_idx = 0
    max_fruits_len = -1
    for idx in range(len(numbers)):
        fruit = numbers[idx]
        fruits_dic[fruit] += 1

        while len(fruits_dic) > 2:
            fruits_dic[numbers[left_idx]] -= 1
            if fruits_dic[numbers[left_idx]] == 0:
                del fruits_dic[numbers[left_idx]]
            left_idx += 1

        max_fruits_len = max(max_fruits_len, idx - left_idx + 1)
    
    return max_fruits_len


readline = sys.stdin.readline
N = int(readline())
numbers = list(map(int, readline().split()))
answer = calculate_max_fruits_len(numbers)
print(answer)
