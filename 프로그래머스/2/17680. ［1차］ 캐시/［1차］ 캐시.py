"""
제주가 캐시에 있어도 캐시 리스트에서 제주를 빼고 다시 넣어야한다는 사실을 잊지 말자
그냥 last index를 기록할까? 대신 index기준으로 sort가 되어있어야 하는데... 흠
"""


def solution(cacheSize, cities):
    cities_in_cache = []
    total_time = 0
    CACHE_MISS_TIME = 5
    CACHE_HIT_TIME = 1
    
    if cacheSize == 0:
        total_time = len(cities) * CACHE_MISS_TIME
        return total_time
    
    for city in cities:
        city = city.lower()
        if city not in cities_in_cache:
            total_time += CACHE_MISS_TIME
            if len(cities_in_cache) >= cacheSize:
                cities_in_cache.pop(0)
            cities_in_cache.append(city)
        else:
            total_time += CACHE_HIT_TIME
            cities_in_cache.remove(city)
            cities_in_cache.append(city)
            
    return total_time