def solution(n, arr1, arr2):
    answer = []
    SPACE = " "
    WALL = "#"
    for row_idx in range(n):
        decoded_row_arr1 = list(bin(arr1[row_idx])[2:])
        decoded_row_arr2 = list(bin(arr2[row_idx])[2:])
        if len(decoded_row_arr1) < n:
            len_diff = n - len(decoded_row_arr1)
            decoded_row_arr1 = [0] * len_diff + decoded_row_arr1
        
        if len(decoded_row_arr2) < n:
            len_diff = n - len(decoded_row_arr2)
            decoded_row_arr2 = [0] * len_diff + decoded_row_arr2
        
        row = []
        for col_idx in range(n):
            if decoded_row_arr1[col_idx] == "1" or decoded_row_arr2[col_idx] == "1":
                row.append(WALL)
                continue
            
            row.append(SPACE)
        answer.append(''.join(row))
    return answer