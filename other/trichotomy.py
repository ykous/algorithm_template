"""
三分法用于可以抽象成求凸函数（凹函数）的最大值（最小值）点的问题

三分法不适合用于整数区间求解

三分法要求题目必须能抽象成一个具体的凸（凹）函数，
因此用法具有和序列二分查找一样的局限性（序列二分查找即在指定单调函数中找到指定值（零点），这同样要求题目能够抽象成一个具体的单调函数）

当然由于已经必然要抽象成具体函数，那先求导再二分也行

@:Author ykous
@:Date 2020/12/13  16:11
"""


def trichotomy_max(lower: float, upper: float, val, esp: float) -> float:
    """
    凸函数区间求极值
    :param lower: 左区间
    :param upper: 右区间
    :param val: 凸函数
    :param esp: 阈值
    :return: 极大值点
    """
    while upper - lower > esp:
        ml = lower + ((upper - lower) / 3)
        mr = lower + ((upper - lower) * 2 / 3)
        if val(ml) > val(mr):
            upper = mr
        else:
            lower = ml

    return lower


def trichotomy_min(lower: float, upper: float, val, esp: float) -> float:
    """
    凹函数区间求极值
    :param lower: 左区间
    :param upper: 右区间
    :param val: 凸函数
    :param esp: 阈值
    :return: 极小值点
    """
    while upper - lower > esp:
        ml = lower + ((upper - lower) / 3)
        mr = lower + ((upper - lower) * 2 / 3)
        if val(ml) > val(mr):
            lower = ml
        else:
            upper = mr

    return lower


def func(x: float) -> float:
    return -x * x + 2 * x - 1


def func2(x: float) -> float:
    return x * x + 2 * x + 1


if __name__ == '__main__':
    print(trichotomy_min(-100, 100, func2, 10 ** -6))
