import sys

def dp(w, v):
    if(w>k):
        return -1
    for i in reversed(range(w, k+1)):
        if(solution[i] == 0):
            solution[i] = v
        else:
            solution[i] = max(solution[i], solution[i-w]+v)


#물품의 개수, 총 무게
n, k = map(int, sys.stdin.readline().split())

weight = []
value = []
solution = [0]*(k+1)

for i in range(n) :
    w, v = map(int, sys.stdin.readline().split())
    weight.append(w)
    value.append(v)
    dp(weight[i], value[i])

print(solution[k])
