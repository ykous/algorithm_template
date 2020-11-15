"""
二分查找模板，均采用左闭右闭的区间
@:Author ykous
@:Date 2020/11/15  19:19
"""


def check(m):
    return m < 10


def binary_search_right(lower, upper):
    """
    右区间为可行解的二分查找，记忆口诀 rrll
    :param lower: 上界
    :param upper: 下届
    :return: 最小可行解
    """
    l = lower
    r = upper
    while l <= r:
        m = l + ((r - l) >> 1)
        if check(m):
            r = m - 1
        else:
            l = m + 1
    return l


def binary_search_left(lower, upper):
    """
    左区间为可行解的二分查找，记忆口诀 llrr
    :param lower: 上界
    :param upper: 下界
    :return: 最大可行解
    """
    l = lower
    r = upper
    while l <= r:
        m = l + ((r - l) >> 1)
        if check(m):
            l = m + 1
        else:
            r = m - 1

    return r


def binary_search_list(arr: list, target) -> int:
    """
    最少用的模板，只能用于在有序序列中找到指定值
    """
    l = 0
    r = len(arr) - 1
    while l <= r:
        m = l + ((r - l) >> 1)
        if arr[m] == target:
            return m
        elif arr[m] < target:
            l = m + 1
        else:
            r = m - 1

    return -1


if __name__ == '__main__':
    print(binary_search_left(0, 1100))
