def solution(n, arr1, arr2):
    answer = []
    SPACE = " "
    WALL = "#"
    
    for row_idx in range(n):
        # 비트 OR 연산 후 이진수 문자열로 변환
        combined_row = bin(arr1[row_idx] | arr2[row_idx])[2:].rjust(n, '0')
        
        # 리스트 컴프리헨션을 사용하여 행 생성
        row = [WALL if bit == "1" else SPACE for bit in combined_row]
        
        answer.append(''.join(row))
    
    return answer
