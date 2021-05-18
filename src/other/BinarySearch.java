package other;

/**
 * 二分查找，分为整数查找和浮点数查找
 * <p>
 * 整数查找，比较复杂，分为三种情况
 * 1. 在有序数对中寻找指定数（少用，它要求序列元素可以被量化并且有序）
 * 2. 在序列中找满足条件的最小下标元素，要求序列的左半部分不满足条件，右半部分满足条件
 * 3. 在序列中找满足条件的最大下标元素，要求序列的左半部分满足条件，右半部分不满足条件
 * 给这种序列命名为真值单调序列，2 的情况为真值曾序列， 3 的情况为真值减序列
 * <p>
 * 当然 2 3 两种情况是兼容 1 这种情况的，但是 2 3 需要特殊处理 1 中没找到的情况
 * <p>
 * 浮点型不需要处理没找到的情况，因为事实上绝大多数时候我们都无法真的找到对应的数字，只能在一个精度范围内找到
 * 所以浮点型只有 2 3 两种就足够
 */
public class BinarySearch {

    public static int bs1(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] == target) return m;
            if (arr[m] > target) r = m - 1;
            else l = m + 1;
        }
        return -1;
    }

    public static int bs2(int l, int r) {
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (check2(m)) r = m - 1;
            else l = m + 1;
        }
        return l;
    }

    private static boolean check2(int m) {
        return m > 2;
    }

    public static int bs3(int l, int r) {
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (check3(m)) l = m + 1;
            else r = m - 1;
        }
        return r;
    }

    private static boolean check3(int m) {
        return m < 2;
    }

    public static double bs4(double l, double r, double eps) {
        // 浮点二分，只有精度是退出条件，因为 r 永远 >= l
        while (r - l > eps) {
            double m = (l + r) / 2;
            if (check4(m)) r = m;
            else l = m;
        }
        return r; // 此处不再满足 rrll 的口诀，右区间的浮点二分一定要返回 r
    }

    private static boolean check4(double m) {
        return m > 2;
    }

    public static double bs5(double l, double r, double eps) {
        while (r - l > eps) {
            double m = (l + r) / 2;
            if (check5(m)) l = m;
            else r = m;
        }
        return l; // 同理一定要返回 l
    }

    private static boolean check5(double m) {
        return m < 2;
    }

    public static void main(String[] args) {
        System.out.println(bs5(0, 10, 1e-4));
    }
}
