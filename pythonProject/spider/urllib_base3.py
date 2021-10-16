import urllib.request
import urllib.parse

# 增加User-Agent    针对UA反爬
url = "https://www.baidu.com"
headers = {
    'User-Agent':
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36'
}
request = urllib.request.Request(url, headers=headers)

content = urllib.request.urlopen(request).read().decode('utf8')
print(content)

# url编码
url = 'https://www.baidu.com/s?wd='
headers = {
    'User-Agent':
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36'
}
name = urllib.parse.quote('毛不易')  # 将字符串变成unicode编码格式

url = url + name

urllib.request.Request(url=url, headers=headers)

response = urllib.request.urlopen(request)

content = response.read().decode('utf-8')

print(content)
