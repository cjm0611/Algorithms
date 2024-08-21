import sys

readline = sys.stdin.readline
eggNum = int(readline())
egg_hps = []
egg_weights = []
answer = 0

def back(cnt):
    global answer
    if cnt == eggNum:
        broken_eggs = [hp for hp in egg_hps if hp <= 0]
        answer = max(answer, len(broken_eggs))
        return
    
    if egg_hps[cnt] <= 0:
        back(cnt + 1)
    else:
        is_hit = False
        for idx in range(eggNum):
            if egg_hps[idx] > 0 and egg_hps[cnt] > 0 and idx != cnt:
                is_hit = True
                egg_hps[cnt] -= egg_weights[idx]
                egg_hps[idx] -= egg_weights[cnt]
                back(cnt + 1)
                egg_hps[cnt] += egg_weights[idx]
                egg_hps[idx] += egg_weights[cnt]
        if not is_hit:
            back(cnt + 1)
            
for _ in range(eggNum):
    hp, weight = map(int, readline().split())
    egg_hps.append(hp)
    egg_weights.append(weight)

back(0)
print(answer)
