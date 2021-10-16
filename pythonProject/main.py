# This is a sample Python script.

# Press ⌃R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.
# See PyCharm help at https://www.jetbrains.com/help/pycharm/
import math
from typing import List


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press ⌘F8 to toggle the breakpoint.


def my_add(x):
    if not isinstance(x, (int, float)):
        raise TypeError('bad operand type')
    if x >= 0:
        return x
    else:
        return -x


def product(*x):
    if x:
        p = 1
        for n in x:
            p = p * n
        return p
    else:
        raise TypeError


# number为list 或者 tuple
def cal(numbers):
    sum = 0
    for x in numbers:
        sum += x
    return sum


# 关键字参数
def person(name, age, **kw):
    pass


def move(x, y, step, angle=0):
    nx = x + step * math.cos(angle)
    ny = y + step * math.sin(angle)
    return nx, ny


def quadratic(a, b, c):
    z = math.sqrt(b ^ 2 / (2 * a))
    x = -b + z - 4 * a * c
    y = -b - z - 4 * a * c
    return x, y


# 递归
def fact(x):
    if x == 1:
        return 1
    return x * fact(x - 1)


# 尾递归
def fact_iter(num, product):
    if num == 1:
        return product
    else:
        return fact_iter(num - 1, product * num)

# def maxSubArray(nums: List[int]) -> int:
#     max = 0
#     for
#     for x in nums[i:]:
#         sum = 0
#         sum += x
#         if x == 4:
#             print(8)
#         if sum > max:
#             max = sum
#         for y in nums[i + 1:]:
#             sum += y
#             if sum > max:
#                 max = sum
#     return max


def maxSubArray(nums: List[int]) -> int:
    sum = max_sum = nums[0]
    for n in nums[1:]:
        if sum + n > n:
            sum += n
        else:
            sum = n
        max_sum = max(max_sum, sum)
    return max_sum

# Press the green button in the gutter to run the script.
if __name__ == '__main__':

    b = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
    maxSubArray(b)



    a = [2, 4, 5, 9]

    # print_hi('PyCharm')
    print('aaaa')
    print(1000_1000)
    # name = input()
    # print(name)

    # list
    classmates = ['nick', 'paimeng', 'birdman']
    print(len(classmates))

    # tuple
    classmates_2 = ('michael', 'bob', 'marie')
    print(classmates_2)

    if 3 > 5:
        print('3>1')
    elif 3 > 4:
        print('3>4')
    else:
        print('3')

    a = int('3')

    # for
    sum = 0
    for x in [1, 2, 4, 5]:
        sum += sum
    print(sum)

    ##从0到100累加
    sum = 0
    for x in list(range(101)):
        sum += sum

    # while
    sum = 0
    n = 99
    while n > 0:
        sum += n
        n = n - 2
    print(sum)

    # continue break
    n = 0
    while n < 10:
        n = n + 1
        if n % 2 == 0:
            continue
        if n == 9:
            break
        print(n)

    # dictionary dict
    d = {'mick': 999, 'bob': 45, 'lucy': 85}
    print(d['mick'])

    print(fact(3))

    # 列表生成器
    a = [(n, m) for n in 'abc' for m in 'xyz']
    print(a)
    # for后面的if作为过滤条件
    print([n for n in range(0, 100) if n % 3 != 0])
    # for前面的if else作为表达式
    print([x if x % 2 == 0 else -x for x in range(0, 20)])

    print(isinstance([], Iterable))
