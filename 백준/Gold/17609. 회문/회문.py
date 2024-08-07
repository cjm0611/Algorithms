import sys

def is_palindrome(s, left, right):
    """ 문자열의 특정 부분이 회문인지 확인합니다. """
    while left < right:
        if s[left] != s[right]:
            return False
        left += 1
        right -= 1
    return True

def check_palindrome(input_str):
    """ 문자열이 회문인지, 유사 회문인지, 또는 둘 다 아닌지를 반환합니다. """
    start_idx = 0
    end_idx = len(input_str) - 1
    
    while start_idx < end_idx:
        if input_str[start_idx] == input_str[end_idx]:
            start_idx += 1
            end_idx -= 1
            continue
        
        # 두 경우 모두 시도해서 하나라도 회문이면 유사 회문
        if is_palindrome(input_str, start_idx + 1, end_idx) or is_palindrome(input_str, start_idx, end_idx - 1):
            return 1
        return 2

    return 0

def check_palindromes(input_strs):
    """ 여러 문자열에 대해 회문 여부를 판단합니다. """
    results = []
    for input_str in input_strs:
        results.append(check_palindrome(input_str))
    return results

readline = sys.stdin.readline
T = int(readline())
input_strs = []
for i in range(T):
    input_strs.append(readline().rstrip('\n'))

for input_str in input_strs:
    print(check_palindrome(input_str))