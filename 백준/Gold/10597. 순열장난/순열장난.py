import sys

seq = sys.stdin.readline().rstrip()
visited = [False] * len(seq)
restored_seq = []

def back(idx):
    if idx == len(seq):
        print(*restored_seq)
        exit()
    
    if seq[idx] != "0":
        single_digit = int(seq[idx])
        two_digit = int(seq[idx : idx + 2])
        if single_digit <= max_n and single_digit not in restored_seq:
            restored_seq.append(single_digit)
            back(idx + 1)
            restored_seq.pop()
        
        if two_digit <= max_n and two_digit not in restored_seq: # 한 자리 숫자 검사한 후, 두 자리 숫자도 검사해야 함
            restored_seq.append(two_digit)
            back(idx + 2)
            restored_seq.pop()            

if len(seq) < 10:
    max_n = len(seq)
else:
    max_n = (len(seq) - 9) // 2 + 9 # N이 최대 50이므로 세자릿수가 나올 가능성은 없음

back(0)