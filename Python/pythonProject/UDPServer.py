from socket import *

if __name__ == '__main__':
    # 设置服务器的端口号
    severPort = 12000
    # 第一个参数是地址簇(底层网络使用IPv4)
    # 第二个参数指示该socket使用SOCK_DGRAM类型, 是一个UDP套接字
    serverSocket = socket(AF_INET, SOCK_DGRAM)
    # 服务器端显式地将端口绑定, 当任何人向此地址的此端口发送分组时, 该分组将导向该socket
    serverSocket.bind(('', severPort))
    print("The server is ready to receive")
    # 在while循环中, 此线程将等待一个分组的到达
    while True:
        message, clientAddress = serverSocket.recvfrom(2048)
        modifiedMessage = message.decode().upper()
        serverSocket.sendto(modifiedMessage.encode(), clientAddress)
