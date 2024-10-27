N = int(input())
arr = list(map(str, input()))
answer = 0

def check_answer(str_list):
    return 'N' in str_list and str_list.count('N') == 1

a_index_list = []
for i, s in enumerate(arr):
    if s == "A":
        a_index_list.append(i)

if len(a_index_list) <= 1:
    print(0)
    exit()

s = a_index_list[0]
for a_idx in range(1, len(a_index_list)):
    e = a_index_list[a_idx]
    sub_arr = arr[s:e]
    if check_answer(sub_arr):
        answer += 1
    s = e

print(answer)
