N = int(input())
stack = []
answer = []
current = 0
is_seq_possible = True
for _ in range(N):
    target_num = int(input()) - 1
    
    while current <= target_num:
        stack.append(current)
        answer.append("+")
        current += 1
    
    if len(stack) > 0 and stack[-1] == target_num:
        stack.pop()
        answer.append("-")
    else:
        is_seq_possible = False
        break

if is_seq_possible:
    print('\n'.join(answer))
else:
    print("NO")