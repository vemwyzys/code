from pulp import *

# 已知量
mc_production = [1000, 1200, 1100] # 氯甲烷产量
silicone_demand = [800, 1000, 900] # 有机硅需求量
mc_price = [11.2, 11.3, 11.2] # 氯甲烷市场价格
storage_capacity = 5000 # 存储罐最大容量
storage_cost = 0.1 # 存储成本

T = len(mc_production) # 时间点数量

# 定义问题
prob = LpProblem("The Chloromethane Problem", LpMinimize)

# 定义决策变量
mc_to_store = LpVariable.dicts("MC_to_store", range(T), 0, None) # 氯甲烷存储量
mc_to_sale = LpVariable.dicts("MC_to_sale", range(T), 0, None) # 氯甲烷销售量
mc_to_purchase = LpVariable.dicts("MC_to_purchase", range(T), 0, None) # 氯甲烷购买量
mc_to_silicone = LpVariable.dicts("MC_to_silicone", range(T), 0, None) # 氯甲烷用于生产有机硅的量

# 添加目标函数
prob += lpSum([mc_to_purchase[t] * mc_price[t] + mc_to_store[t] * storage_cost - mc_to_sale[t] * mc_price[t] for t in range(T)]), "Total Cost"

# 添加约束条件
for t in range(T):
    # 每个时间点，氯甲烷的产量需要被存储、销售或用于生产有机硅
    prob += mc_to_store[t] + mc_to_sale[t] + mc_to_silicone[t] == mc_production[t], f"MC distribution at time {t}"

    # 每个时间点，生产有机硅的氯甲烷需求量必须得到满足
    prob += mc_to_silicone[t] == silicone_demand[t], f"Silicone production at time {t}"

    # 存储罐容量限制
    prob += mc_to_store[t] <= storage_capacity, f"Storage capacity at time {t}"

    # 销售量不能超过产量
    prob += mc_to_sale[t] <= mc_production[t], f"Sale capacity at time {t}"

# 求解问题
prob.solve()

# 打印结果
print("Status:", LpStatus[prob.status])
for v in prob.variables():
    print(v.name, "=", v.varValue)
print("Total cost =", value(prob.objective))
