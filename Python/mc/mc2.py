from pulp import *
import matplotlib.pyplot as plt
from matplotlib.font_manager import FontProperties

## 图表显示中文的条件
font = FontProperties(fname=r"/Library/Fonts/Arial Unicode.ttf", size=10)

# 已知量
mc_production = [1000, 1200, 1100]  # 氯甲烷产量
silicone_demand = [800, 1000, 1200]  # 有机硅需求量

mc_price = [1.2, 2.3, 1.2]  # 氯甲烷市场价格
# 买出 额外成本企业还需要为销售提供售后服务，包括处理顾客的咨询、投诉和退货等
purchase_additional_cost = 0.3
# 买入额外成本
sale_additional_cost = 0.2

storage_capacity = 5000  # 存储罐最大容量
storage_cost = 0.05  # 存储成本
initial_storage = 0  # 初始存储罐容量
final_storage = 500  # 最终存储罐容量
T = len(mc_production)  # 时间点数量

# 定义问题
prob = LpProblem("The Chloromethane Problem", LpMaximize)

# 定义决策变量
mc_to_store = LpVariable.dicts("A氯甲烷生产后存储", range(T), 0, None)  # 氯甲烷存储量
mc_to_sale = LpVariable.dicts("A氯甲烷生产后外销", range(T), 0, None)  # 氯甲烷销售量
mc_to_silicone = LpVariable.dicts(
    "A氯甲烷生产后生产有机硅", range(T), 0, None)  # 氯甲烷用于生产有机硅的量

purchase_to_silicone = LpVariable.dicts(
    "B外采用于生产", range(T), 0, None)  # 氯甲烷购买量并用于生产有机硅
purchase_to_storage = LpVariable.dicts(
    "B外采用于存储", range(T), 0, None)  # 氯甲烷购买并存入存储罐

storage_to_silicone = LpVariable.dicts(
    "C取存储罐去生产有机硅", range(T), 0, None)  # 为合成有机硅从存储罐中取出的氯甲烷量
storage_to_sale = LpVariable.dicts(
    "C取存储罐去外销", range(T), 0, None)  # 为销售氯甲烷从存储罐中取出的氯甲烷量
storage = LpVariable.dicts("C存储量(回合结束时)", range(T), 0, None)  # 存储罐中的氯甲烷量

# 添加目标函数
prob += lpSum(
    # 生产后直接卖出利益
    [mc_to_sale[t] * (mc_price[t] - sale_additional_cost)
     # 存储罐中取出卖出的利益
     + storage_to_sale[t] * (mc_price[t] - sale_additional_cost)
     # 买入成本（买入后立即用于生产有机硅）
     - purchase_to_silicone[t] * \
     (mc_price[t] + purchase_additional_cost)
     # 买入成本（买入后立即存储的）
     - purchase_to_storage[t] * \
     (mc_price[t] + purchase_additional_cost)
     # 存储成本
     - storage[t] * storage_cost
     for t in range(T)]), "Total Profit"

# 添加约束条件
for t in range(T):
    # 每个时间点，氯甲烷的产量需要被存储、销售或用于生产有机硅
    prob += mc_to_store[t] + mc_to_sale[t] + \
        mc_to_silicone[t] == mc_production[t], f"MC distribution at time {t}"

    # 每个时间点，生产有机硅的氯甲烷需求量必须得到满足
    prob += mc_to_silicone[t] + purchase_to_silicone[t] + \
        storage_to_silicone[t] == silicone_demand[
            t], f"Silicone production at time {t}"

    # 存储罐容量限制
    prob += storage[t] <= storage_capacity, f"Storage capacity at time {t}"

    # 回合结束时：存储罐中的氯甲烷量等于上一次存储罐中的氯甲烷量加上存入的量减去取出的量
    if t == 0:
        prob += initial_storage + mc_to_store[t] + purchase_to_storage[t] - \
            storage_to_silicone[t] - \
            storage_to_sale[t] == storage[t], f"Storage balance at time {t}"
    else:
        prob += storage[t-1] + mc_to_store[t] - \
            storage_to_silicone[t] - \
            storage_to_sale[t] == storage[t], f"Storage balance at time {t}"

# 最后一个时间点，存储量应等于设定的最终存储量
prob += storage[T-1] == final_storage, "Final storage"

# 求解问题
prob.solve()

# 打印结果
print("Status:", LpStatus[prob.status])
print("Total cost =", value(prob.objective))
totalIncome = int(value(prob.objective))
variable_dict = {}

for v in prob.variables():
    name = v.name
    value = v.varValue
    index = int(name.split('_')[-1])  # 获取末尾的数字
    if index not in variable_dict:
        variable_dict[index] = []
    variable_dict[index].append((name, value))

fig, axs = plt.subplots(len(variable_dict.keys()), 1,
                        figsize=(6, 4*len(variable_dict.keys())))
plt.subplots_adjust(hspace=0.5)
# key=int 是为了确保数字按照数值进行排序，而不是按照字符串
for i, index in enumerate(sorted(variable_dict.keys(), key=int)):
    print(f"Day{index}:")
    print(index)
    names = []
    values = []
    for name, value in variable_dict[index]:
        print(name, "=", value)
        names.append(name)
        values.append(value)

    # 创建一个条形图
    axs[i].bar(names, values)
    for j, v in enumerate(values):
        axs[i].text(j, v + 0.01, str(v), color='blue', fontweight='bold')
    axs[i].set_title(
        f'时间{index}, \
            氯甲烷产量: {mc_production[index]}, \
            有机硅对氯甲烷的需求量: {silicone_demand[index]}, \
                氯甲烷市场价格: {mc_price[index]}',
        fontproperties=font)
    axs[i].set_xlabel('决策', fontproperties=font)
    axs[i].set_ylabel('氯甲烷单位量', fontproperties=font)

    # 设置刻度的位置
    axs[i].set_xticks(range(len(names)))
    # 设置x轴刻度的标签，并指定字体
    axs[i].set_xticklabels(names, fontproperties=font)

# 在左上角添加文本
fig.text(0.01, 0.95, f'问题状态：{LpStatus[prob.status]}',
         fontsize=12, ha='left', va='top', fontproperties=font)
fig.text(0.01, 0.93, f'总收入 = {totalIncome}',
         fontsize=12, ha='left', va='top', fontproperties=font)

# 在右上角添加文字
fig.text(0.80, 0.95, f'存储罐初始容量：{initial_storage}',
         fontsize=12, ha='left', va='top', fontproperties=font)
plt.show()
