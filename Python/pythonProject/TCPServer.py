from socket import *

if __name__ == '__main__':
    # 设置服务器的端口号
    severPort = 12000
    # 第一个参数是地址簇(底层网络使用IPv4)
    # 第二个参数指示该socket使用SOCK_DGRAM类型, 是一个UDP套接字
    serverSocket = socket(AF_INET, SOCK_STREAM)
    # 服务器端显式地将端口绑定, 当任何人向此地址的此端口发送分组时, 该分组将导向该socket
    serverSocket.bind(('', severPort))
    # 让服务端聆听客户的TCP连接请求. 参数指定了连接请求的最大数
    serverSocket.listen(1)
    # 这个socket是服务器的欢迎套接字, 创建欢迎之门后, 服务器等待并聆听客户端敲门
    print("The server is ready to receive")
    # 在while循环中, 此线程将等待一个分组的到达
    while True:
        # 当有客户敲门时, 程序为serverSocket调用accept()方法
        # 创建了新的一个套接字: connectionSocket, 为这个特定的客户专用
        # CS之间完成了握手, 彼此之间建立了TCP连接
        connectionSocket, addr = serverSocket.accept()
        sentence = connectionSocket.recv(2048).decode()
        capitalizedSentence = sentence.upper()
        # TCP连接内彼此发送字节确保能打到, 并且确保时序
        serverSocket.send(capitalizedSentence.encode())
        # 完成任务后关闭了专用套接字, 但接客套接字仍然存在, 并等待新的客户敲门
        connectionSocket.close()
