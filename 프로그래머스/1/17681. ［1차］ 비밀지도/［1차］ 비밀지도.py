def solution(n, arr1, arr2):
    answer = []
    SPACE = " "
    WALL = "#"
    
    for row1, row2 in zip(arr1, arr2):
        # 비트 OR 연산 후 이진수 문자열로 변환
        combined_row = bin(row1 | row2)[2:].rjust(n, '0')
        
        # 리스트 컴프리헨션을 사용하여 정답 지도의 row 생성
        decoded_row = [WALL if bit == "1" else SPACE for bit in combined_row]
        
        answer.append(''.join(decoded_row))
    
    return answer
