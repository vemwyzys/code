import tkinter

import matplotlib.pyplot as plt
from tkinter import *
import json


def analyse():
    # messagebox.showinfo("Hello Python", "Hello Runoob")
    print(E1.get())
    data = E1.get()
    E1.delete(0, END)

    x = []
    y = []
    json_list = data.splitlines()
    for item in json_list:
        l = json.loads(item)
        x.append(l['longitude'])
        y.append(l['latitude'])

    # plot函数作图
    plt.plot(x, y)

    # show函数展示出这个图，如果没有这行代码，则程序完成绘图，但看不到
    plt.show()


if __name__ == '__main__':
    top = tkinter.Tk()
    L1 = tkinter.Label(top, text="json数据")
    L1.pack(side=tkinter.LEFT)
    E1 = Entry(top, bd=5)
    E1.pack(side=RIGHT)

    B = tkinter.Button(top, text="分析", command=analyse)

    B.pack()
    # 进入消息循环
    top.mainloop()

    # x = [120.216137990467, 120.217221473468, 120.217407531966, 120.217446454223]
    # y = [30.209264033502, 30.210965262833, 30.211044591377, 30.211083010059]
    #
    # json = ''
    # # plot函数作图
    # plt.plot(x, y)
    #
    # # show函数展示出这个图，如果没有这行代码，则程序完成绘图，但看不到
    # plt.show()

    # x = [1, 2, 3, 4]
    # y = [1.2, 2.5, 4.5, 7.3]
    #
    # # plot函数作图
    # plt.plot(x, y)
    #
    # # show函数展示出这个图，如果没有这行代码，则程序完成绘图，但看不到
    # plt.show()


def delete():
    # E1.delete(0, END)
    pass