def getRandomArray(n):
    from random import random
    d = {}
    i = 0
    while i < n:
        rNum = int(random()*n)
        if rNum not in d:
            d[rNum] = 1
            i = i + 1
    return list(d.keys())

def getSortedArray(n):
    lst = []
    for i in range(n,0,-1):
        lst.append(i)
    return lst
