import sys

def is_deq_empty(deq):
    return len(deq) == 0

readline = sys.stdin.readline
action_num = int(input())
deq = []
for _ in range(action_num):
    inputs = list(map(str, readline().split()))
    action = inputs[0]
    if len(inputs) == 2:
        digit = int(inputs[1])
        if action == "push_front":
            deq.insert(0, digit)
        else: # "push_back" 
            deq.append(digit)
    else:
        if action == "pop_front":
            if is_deq_empty(deq):
                print(-1)
            else:
                print(deq[0])
                deq.pop(0)
        elif action == "pop_back":
            if is_deq_empty(deq):
                print(-1)
            else:
                print(deq[-1])
                deq = deq[:-1]
        elif action == "size":
            print(len(deq))
        elif action == "empty":
            if is_deq_empty(deq):
                print(1)
            else:
                print(0)
        elif action == "front":
            if is_deq_empty(deq):
                print(-1)
            else:
                print(deq[0])
        else: # back
            if is_deq_empty(deq):
                print(-1)
            else:
                print(deq[-1])
