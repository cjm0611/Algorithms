"""
노드 번호 중 가장 큰 번호를 찾는다.
노드 정점 별로 들어오는 간선 수, 나가는 간선 수를 계산한다.
그래프 개수를 찾을 때는, 각자 대표되는 점을 기준으로 개수를 센다. (도넛 모양은 대표되는 점이 없기 때문에 소거법으로 구함)
1. 나가는 간선 수가 0개다 => 막대 모양
2. 나가는 간선 수가 2개 이상, 들어오는 간선 수도 2개 이상 => 8자 모양
3. 나가는 간선 수가 2개 이상, 들어오는 간선 수는 0개 => 추가된 정점
1~3의 케이스에 해당하지 않는다면 패스

추가된 정점에서 나가는 간선의 개수 (즉, 그래프의 개수)에서 막대 모양 개수와 8자 모양 개수를 제외하면 도넛 모양
"""

def solution(edges):
    out_in_edges = {} # 각 노드 별로 나가는 간선 수, 들어오는 간선 수 계산

    for edge in edges:
        s, e = edge
        if not out_in_edges.get(s):
            out_in_edges[s] = [0, 0]
        if not out_in_edges.get(e):
            out_in_edges[e] = [0, 0]
        
        out_in_edges[s][0] += 1 # 나가는 간선 수 추가
        out_in_edges[e][1] += 1 # 들어오는 간선 수 추가
    
    added_node = graph_1_cnt = graph_2_cnt = graph_3_cnt = 0

    for node, cnts in out_in_edges.items():
        out_cnt, in_cnt = cnts
        if out_cnt == 0:
            graph_2_cnt += 1
        elif out_cnt >= 2 and in_cnt == 0:
            added_node = node
        elif out_cnt >= 2 and in_cnt >= 2:
            graph_3_cnt += 1
    
    graph_1_cnt = out_in_edges[added_node][0] - graph_2_cnt - graph_3_cnt
    return([added_node, graph_1_cnt, graph_2_cnt, graph_3_cnt])
    