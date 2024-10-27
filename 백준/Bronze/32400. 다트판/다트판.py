import sys

scores = list(map(int, sys.stdin.readline().split()))

bob_sum = sum(scores)
alice_sum = 0
idx = scores.index(20)
# 20이 맨왼쪽인 경우
if idx == 0:
    alice_sum += scores[idx] + scores[idx+1] + scores[-1]
# 20이 맨 오른쪽인 경우
elif idx == len(scores) - 1:
    alice_sum += scores[idx] + scores[idx-1] + scores[0]
else:
    alice_sum += scores[idx] + scores[idx-1] + scores[idx+1]

bob_avg = bob_sum/20
alice_avg = alice_sum/3

if bob_avg < alice_avg:
    print("Alice")
elif bob_avg > alice_avg:
    print("Bob")
else:
    print("Tie")