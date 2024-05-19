from functools import reduce


# 切片操作
def trim(s):
    l = len(s)
    i = 0
    j = l - 1
    if s == '':
        return ''
    while s[i] == ' ':
        i = i + 1
        if i == l:
            return ''
    while s[j] == ' ':
        j = j - 1
    return s[i:j + 1]


def findMinAndMax(L):
    if len(L) == 0:
        return None, None
    min = max = L[0]
    for x in L:
        if x > max:
            max = x
        if x < min:
            min = x
    return min, max


# 杨辉三角
def triangles():
    l = [1]
    while True:
        yield l
        l = [0] + l + [0]
        l = [l[i] + l[i + 1] for i in range(len(l) - 1)]


# 打印出前num位斐波那契数列
def fib(num):
    n, a, b = 1, 1, 1
    while n < num + 1:
        print(a)
        a, b = b, a + b
        n = n + 1
    print("done")


def f(x):
    return x * x


def add(x, y):
    return x + y


if __name__ == '__main__':
    print(trim('    hello   '))

    if trim('hello  ') != 'hello':
        print('测试失败!')
    elif trim('  hello') != 'hello':
        print('测试失败!')
    elif trim('  hello  ') != 'hello':
        print('测试失败!')
    elif trim('  hello  world  ') != 'hello  world':
        print('测试失败!')
    elif trim('') != '':
        print('测试失败!')
    elif trim('    ') != '':
        print('测试失败!')
    else:
        print('测试成功!')

    if findMinAndMax([]) != (None, None):
        print('测试失败!')
    elif findMinAndMax([7]) != (7, 7):
        print('测试失败!')
    elif findMinAndMax([7, 1]) != (1, 7):
        print('测试失败!')
    elif findMinAndMax([7, 1, 3, 9, 5]) != (1, 9):
        print('测试失败!')
    else:
        print('测试成功!')

    fib(4)

    n = 0
    result = []
    for t in triangles():
        result.append(t)
        n = n + 1
        if n == 10:
            break

    for t in result:
        print(t)

    r = map(f, [1, 2, 3, 4, 5, 6, 7, 8])
    print(list(r))

    sum = reduce(add, [1, 2, 3, 4])
    print(sum)
