import sys

scores = list(map(int, sys.stdin.readline().split()))

sum = 0
alice_sum = 0
for i in range(len(scores)):
    sum += scores[i]
    if scores[i] == 20:
        # 20이 맨왼쪽인 경우
        if i == 0:
            alice_sum += scores[i] + scores[i+1] + scores[-1]
        # 20이 맨 오른쪽인 경우
        elif i == len(scores) - 1:
            alice_sum += scores[i] + scores[i-1] + scores[0]
        else:
            alice_sum += scores[i] + scores[i-1] + scores[i+1]

bob = sum/20
alice = alice_sum/3

if bob < alice:
    print("Alice")
elif bob > alice:
    print("Bob")
else:
    print("Tie")