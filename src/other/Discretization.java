package other;

import java.util.TreeSet;

/**
 * 离散化是指把范围很广但是数量很少的坐标映射成紧凑的坐标从而使得定义域范围变得很小
 * <p>
 * 绝大多数情况下，映射后的坐标需要保持原有坐标的大小关系（有些问题比较特殊不需要）
 * 因此此处的映射为保持先后次序的映射
 */
public class Discretization {

    static TreeSet<Integer> set = new TreeSet<>(); // 红黑树可以帮助我们去重和维护先后次序
    static int[] mapping;

    // 添加坐标
    static void add(int x) {
        set.add(x);
    }

    // 执行映射，将当前添加的坐标映射到紧凑坐标
    // 由此可见使用离散化的前提是坐标集合不能频繁变动
    static void map() {
        mapping = set.stream().mapToInt(t -> t).toArray();
    }

    // 获取虚拟坐标，一般来说不需要通过虚拟坐标获取真实坐标
    // 模板中采用二分处理，也可以使用 HashMap 但不会比二分更快，而且更复杂
    static int transform(int x) {
        int l = 0, r = mapping.length - 1;
        while (l <= r) {
            int m = l + r >> 1;
            if (mapping[m] <= x) l = m + 1;
            else r = m - 1;
        }
        return r;
    }
}
