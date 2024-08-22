sequence_len = int(input())
answer = float('inf')

def is_good_seq(seq):
    for idx in range(1, len(seq) // 2 + 1):
        p = seq[-idx:]      # 마지막 i개의 문자
        q = seq[-2 * idx:-idx]  # 그 앞의 i개의 문자
        if p == q:
            return False
    return True

def make_sequence(seq):
    global answer
    if len(seq) == sequence_len:
        answer = min(int(seq), answer)
        print(answer)
        exit()

    for num in range(1, 4):
        new_seq = seq + str(num)
        if len(seq) == 0:
            make_sequence(new_seq)
        elif int(seq[-1]) != num and is_good_seq(new_seq):
            make_sequence(new_seq)

make_sequence("")
print(answer)
