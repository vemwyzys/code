from socket import *

if __name__ == '__main__':
    # 服务器主机名或者IP地址, 使用主机名将会自动执行DNS lookup从而获取IP地址
    severName = 'localhost'
    # 设置服务器的端口号
    severPort = 12000
    # 第一个参数是地址簇(底层网络使用IPv4)
    # 第二个参数指示该socket使用SOCK_STREAM类型, 是一个TCP套接字
    # 没有指定客户端自己的套接字端口号, 操作系统帮忙完成了
    clientSocket = socket(AF_INET, SOCK_STREAM)
    # 发起CS之间的连接, 参数是服务器端的地址, 当此行执行完,执行三次握手, 并建立TCP连接
    clientSocket.connect((severName, severPort))
    # 生成报文, input是py内置功能:提示下面括号这句话, 并使用用户的之后的键盘输入 直到回车结束
    sentence = input('Input lowercase sentence:')
    # 将报文由字符串转为字节类型(因为套接字发送字节),
    # 向进程的socket发送结果分组(源地址和端口由操作系统自己附加完成)
    clientSocket.send(sentence.encode())
    # 接收到的分组       源地址                         2048作为缓存长度
    modifiedSentence = clientSocket.recv(1024)
    # 打印结果
    print('From Server: ', modifiedSentence.decode())
    # 关闭套接字
    clientSocket.close()
