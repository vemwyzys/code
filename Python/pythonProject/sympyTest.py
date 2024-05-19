from sympy import *

if __name__ == '__main__':
    x = Symbol('x')
    y = Symbol('y')
    print(solve([2 * x - y - 3, 3 * x + y - 7], [x, y]))
    print(solve([pow(x, 2) + pow(y, 2) - 20, (x - 2 * y) * (x - 3 * y)], [x, y]))
