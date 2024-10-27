"""
W: 앞으로 1만큼 이동
A: 왼쪽으로 1 이동
S: 뒤로 1만큼 이동
D: 오른쪽으로 1만큼 이동

MR: 오른쪽 회전
ML: 왼쪽 회전

출력:
주인공과 카메라의 위치
"""

def rotate_dir(now_dir, dir):
    if now_dir == "U":
        if dir == "MR":
            return "R", (-1, 1)
        elif dir == "ML":
            return "L", (1, 1)
        
    elif now_dir == "R":
        if dir == "MR":
            return "D", (1, 1)
        elif dir == "ML":
            return "U", (1, -1)
    
    elif now_dir == "D":
        if dir == "MR":
            return "L", (1, -1)
        elif dir == "ML":
            return "R", (-1, -1)
        
    elif now_dir == "L":
        if dir == "MR":
            return "U", (-1, -1)
        elif dir == "ML":
            return "D", (-1, 1)


def get_move(now_dir, dir):
    if now_dir == "U":
        if dir == "W":
            return (0, 1)
        elif dir == "A":
            return (-1, 0)
        elif dir == "S":
            return (0, -1)
        elif dir == "D":
            return (1, 0)
    
    elif now_dir == "R":
        if dir == "W":
            return (1, 0)
        elif dir == "A":
            return (0, 1)
        elif dir == "S":
            return (-1, 0)
        elif dir == "D":
            return (0, -1)

    elif now_dir == "D":
        if dir == "W":
            return (0, -1)
        elif dir == "A":
            return (1, 0)
        elif dir == "S":
            return (0, 1)
        elif dir == "D":
            return (-1, 0)
    
    elif now_dir == "L":
        if dir == "W":
            return (-1, 0)
        elif dir == "A":
            return (0, -1)
        elif dir == "S":
            return (1, 0)
        elif dir == "D":
            return (0, 1)
    
main = (0, 0) # 주인공의 위치
camera = (0, -1) # 카메라의 위치
now_dir = "U" # 시점의 방향 - U, R, D, L

input_len = int(input())
for _ in range(input_len):
    dir = input()
    if dir not in ["MR", "ML"]:
        next_position = get_move(now_dir, dir)
        main = (main[0] + next_position[0], main[1] + next_position[1])
        camera = (camera[0] + next_position[0], camera[1] + next_position[1])
    else:
        now_dir, next_camera_position = rotate_dir(now_dir, dir)
        camera = (camera[0] + next_camera_position[0], camera[1] + next_camera_position[1])
    print(f"{main[0]} {main[1]} {camera[0]} {camera[1]}")