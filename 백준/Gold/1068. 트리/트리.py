import sys

sys.setrecursionlimit(10**6)
readline = sys.stdin.readline

def dfs(node):
    global answer
    if node == X:
        return

    if not edges[node] or edges[node] == [X]: # 리프노드
        answer += 1
        return

    for end_node in edges[node]:
        dfs(end_node)

N = int(readline()) # 노드의 개수
edges = [[] for _ in range(N)] # 자식 노드의 수
arr = list(map(int, readline().split())) # 각 노드의 부모
X = int(readline()) # 지울 노드 번호
start = -1
for j in range(len(arr)):
    if arr[j] == -1:
        start = j
        continue
    edges[arr[j]].append(j)

answer = 0
dfs(start)
print(answer)