"""
소괄호, 대괄호 짝 이루는지 확인
"""

import sys

readline = sys.stdin.readline
while True:
    sentence = readline().rstrip()
    if sentence == ".":
        break

    brackets = []
    is_balanced = True
    for word in sentence:
        if word not in ["(", ")", "[", "]"]:
            continue
        
        if word in ["(", "["]:
            brackets.append(word)
        elif word == ")":
            if len(brackets) == 0 or brackets[-1] != "(":
                is_balanced = False
                break
            brackets.pop()
        else:
            if len(brackets) == 0 or brackets[-1] != "[":
                is_balanced = False
                break
            brackets.pop()
        
    if len(brackets) > 0:
        is_balanced = False

    if is_balanced:
        print("yes")
    else:
        print("no")
