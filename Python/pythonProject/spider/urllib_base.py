import urllib.request

url = "http://www.baidu.com"

response = urllib.request.urlopen(url)
# response是HTTPResponse的类型
print(type(response))

# 获取响应的页面的源码
# read方法将返回字节形式的二进制数据  需要decode
content = response.read().decode('utf-8')

#response.read(5)  # 读5个字节
#response.readline()  # 读一行
print(response.getheaders())

print(content)
