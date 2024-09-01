"""
R: 뒤집기
D: 첫번째 수 버리기 -> 배열이 있는 경우 에러

입력
첫째줄 테스트 케이스 개수
둘째줄부터 함수 p 리스트 e.g. RDD
배열의 길이 n
배열에 들어있는 수

출력
각 테스트 케이스에 대해 함수를 수행한 결과를 출력
에러가 발생한 경우 error 출력
"""

import sys
from collections import deque

readline = sys.stdin.readline
T = int(readline())

for _ in range(T):
    actions = list(readline().rstrip())
    nums_len = int(readline())
    nums_input = readline()
    if nums_len == 0:
        if len([action for action in actions if action == "D"]) > 0:
            print("error")
        else:
            print("[]")
        continue

    deq = deque(list(map(int, nums_input[1:-2].split(","))))
    is_left = True
    is_error = False
    for action in actions:
        if action == 'R':
            is_left = not is_left
        else:
            if len(deq) == 0:
                is_error = True
                break

            if is_left:
                deq.popleft()
            else:
                deq.pop()
    
    if is_error:
        print("error")
    else:
        if is_left:
            output = ",".join(list(map(str, deq)))
            print(f"[{output}]")
        else:
            output = ",".join(list(map(str, list(deq)[::-1])))
            print(f"[{output}]")

    