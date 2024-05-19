import matplotlib.pyplot as plt
from matplotlib.font_manager import FontProperties

# 创建一个FontProperties对象，并指定字体文件的路径
font = FontProperties(fname=r"/Library/Fonts/Arial Unicode.ttf", size=14)

x = [1, 2, 3, 4, 5]
y = [6, 7, 8, 9, 10]
plt.plot(x, y)

# 在设置标题的地方，使用fontproperties参数指定字体
plt.title('标题', fontproperties=font)  # 使用中文标题
plt.show()
