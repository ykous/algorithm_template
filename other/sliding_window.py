"""
@:Author ykous
@:Date 2020/11/15  17:47
"""
from typing import List


def sliding_window_undirectional_stretch(l: List, check):
    """
    单向可伸缩滑动窗口模板，采用左闭右开区间
    :param l: 指定序列
    :param check: 检视窗口的性质，当需要移动右边界就返回 True ，否则返回 False，其他状态的更新，结果的检验也均在函数中完成
    """
    length = len(l)
    left = 0
    right = 1
    while right <= length:
        if check(l, left, right):
            right += 1
        else:
            left += 1

# 利用模板完成 统计序列中和为k的区间 问题
def f(l: List, k):
    length = len(l)
    prefix = [0 for _ in range(length + 1)]
    for i in range(length):
        prefix[i + 1] = prefix[i] + l[i]

    count = [0]

    def check(l, left, right):
        # 这里的判断在任何情况下都应该有
        if left == right:
            return True

        t = prefix[right] - prefix[left]
        if t == k:
            count[0] += 1
        return t < k

    sliding_window_undirectional_stretch(l, check)
    return count[0]


print(f([1, 2, 3, 4, 5, 6, 7], -7))
