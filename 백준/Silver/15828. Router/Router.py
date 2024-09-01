"""
패킷은 라우터를 여러 번 거침.
라우터 안의 버퍼: FIFO로 패킷 처리
라우터가 패킷을 처리하는 속도 < 패킷이 들어오는 속도 => 버퍼에 공간이 생길 때까지 입력받는 패킷은 모두 버려짐

입력
N: 버퍼 크기
둘째줄~: 라우터가 처리해야 할 정보
양의 정수: 해당하는 번호의 패킷
0은: 라우터가 패킷 하나를 처리
비어있을 때는 0이 입력 X
-1은 입력 끝

출력
라우터에 남아있는 패킷 출력
비어있으면 empty
"""

import sys

buffer_size = int(input())
buffer = []

while True:
    pack = int(input())
    if pack == -1:
        break

    if pack == 0:
        buffer.pop(0)
        continue

    if len(buffer) >= buffer_size:
        continue

    buffer.append(pack)

if len(buffer) > 0:
    print(*buffer)
else:
    print("empty")
