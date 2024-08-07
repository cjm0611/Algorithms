import sys

def check_palindrome(input_str):
    start_idx = 0
    end_idx = len(input_str) - 1
    is_pseudo_palindrome = False
    while start_idx < end_idx:
        if input_str[start_idx] == input_str[end_idx]:
            start_idx += 1
            end_idx -= 1
            continue
        
        if is_pseudo_palindrome:
            return 2
        
        if input_str[start_idx + 1] == input_str[end_idx] and input_str[start_idx] == input_str[end_idx - 1]:
            tmp_start = start_idx + 1
            tmp_end = end_idx
            is_tmp_pseudo_palindrome = False
            while tmp_start < tmp_end:
                if input_str[tmp_start] == input_str[tmp_end]:
                    tmp_start += 1
                    tmp_end -= 1
                    continue
                    
                is_tmp_pseudo_palindrome = True
                break
            
            if not is_tmp_pseudo_palindrome:
                return 1
            
            tmp_start = start_idx
            tmp_end = end_idx - 1
            is_tmp_pseudo_palindrome = False
            while tmp_start < tmp_end:
                if input_str[tmp_start] == input_str[tmp_end]:
                    tmp_start += 1
                    tmp_end -= 1
                    continue
                    
                is_tmp_pseudo_palindrome = True
                break
            
            if not is_tmp_pseudo_palindrome:
                return 1
            
            return 2
                    
         
        if input_str[start_idx + 1] == input_str[end_idx]:
            start_idx += 1
            is_pseudo_palindrome = True
            continue

        if input_str[start_idx] == input_str[end_idx - 1]:
            is_pseudo_palindrome = True
            end_idx -= 1
            continue
        
        return 2
        
    if is_pseudo_palindrome:
        return 1
    
    return 0

readline = sys.stdin.readline
T = int(readline())
input_strs = []
for i in range(T):
    input_strs.append(readline().rstrip('\n'))

for input_str in input_strs:
    print(check_palindrome(input_str))
