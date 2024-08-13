import sys
from itertools import permutations

readline = sys.stdin.readline

N, M = map(int, readline().split())
sequence = list(map(int, readline().split()))
sequence.sort()

if M == 1:
    output = '\n'.join(map(str, sequence))
    print(output)
else:
    perms = permutations(sequence, M)
    for perm in perms:
        output = ' '.join(map(str, perm))
        print(output)