import sys

readline = sys.stdin.readline

"""
바구니 크기는 무제한
1번행 위에 N번행, N번행 아래에 1번행
1번 열 왼쪽에 N번 열, N번 열 오른쪽에 1번열
"""

N, M = map(int, readline().split()) # 맵 크기, 명령 횟수 M
board = [[] for _ in range(N)]
for i in range(N):
    board[i] = list(map(int, readline().split()))

# is_cloud = [[False for _ in range(N)] for _ in range(N)]
# is_cloud[N-1][0] = is_cloud[N-1][1] = is_cloud[N-2][0] = is_cloud[N-2][1] = True

cloud_points = [(N-1, 0), (N-1, 1), (N-2, 0), (N-2, 1)]
# (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 첫 구름 생김

direcs = [(0, 0), (0, -1), (-1, -1), (-1, 0), (-1, 1), (0, 1), (1, 1), (1, 0), (1, -1)]
for _ in range(M):
    d, s = map(int, readline().split())
    for idx, (x, y) in enumerate(cloud_points):
        dx, dy = (direcs[d][0]*s), (direcs[d][1]*s)
        nx, ny = (x+dx)%N, (y+dy)%N
        cloud_points[idx] = (nx, ny) # 구름 이동
        board[nx][ny] += 1
    
    for (x, y) in cloud_points:
        # 대각선에 물이 있는지 확인하고 그만큼 더함 (경계 못 넘어감)
        water = 0
        dias = [(-1, -1), (-1, 1), (1, -1), (1, 1)]
        for (dx, dy) in dias:
            dia_x, dia_y = (x+dx), (y+dy)
            if 0 <= dia_x < N and 0 <= dia_y < N and board[dia_x][dia_y] > 0:
                water += 1
        board[x][y] += water

    # 다음 턴을 위해 구름 초기화
    new_cloud_points = []
    for i in range(N):
        for j in range(N):
            if board[i][j] >= 2 and (i, j) not in cloud_points:
                board[i][j] -= 2
                new_cloud_points.append((i, j))
    
    cloud_points = new_cloud_points

answer = 0 # 이동이 모두 끝난 후 바구니에 들어있는 물의 총합
for row in board:
    answer += sum(water for water in row)
print(answer)