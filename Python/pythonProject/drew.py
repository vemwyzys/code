#!/usr/bin/env python3
# -*- coding: utf-8 -*-

' a test module '

__author__ = 'WangSong'

from urllib import request
import Python.Baisc.Paimeng as Paimeng
import app

if __name__ == '__main__':
    try:
        with request.urlopen('http://staff.ustc.edu.cn/~llxx/') as f:
            data = f.read()
            print('Status:', f.status, f.reason)
            for k, v in f.getheaders():
                print('%s: %s' % (k, v))
            print('Data:', data.decode('utf-8'))
    except BaseException as e:
        print(e)

    app = app.Application()
    # 设置窗口标题:
    app.master.title('Hello World')
    # 主消息循环:
    app.mainloop()
