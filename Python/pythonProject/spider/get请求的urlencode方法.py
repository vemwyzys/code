# urlencode应用场景：多个参数的时候

# https://www.baidu.com/s?wd=周杰伦&sex=男

import urllib.parse
import urllib.request

base_url = "https://www.baidu.com/s?"
 
data = {
    'wd': '周杰伦',
    'sex': '男',
    'location': '中国台湾省'
}

new_data = urllib.parse.urlencode(data)

# 请求资源路径
url = base_url + new_data
print(url)

headers = {
    'User-Agent':
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36'
}

# 请求对象的定制
request = urllib.request.Request(url=url, headers=headers)

response = urllib.request.urlopen(request)

content = response.read().decode('utf-8')

print(content)
