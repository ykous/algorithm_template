package sort;

import java.util.Arrays;

public class MergeSort {
    static int[] temp = new int[1000000]; // 辅助数组

    public static void main(String[] args) {
        int[] ints = {12, 312, 3, 1, 312, 412, 4, 123, 5432, 5, 23, 423, 5, 1, 24};
        sort(ints, 0, ints.length);
        System.out.println(Arrays.toString(ints));
    }

    // 左闭右开
    static void sort(int[] nums, int l, int r) {
        if (l >= r - 1) return;
        int m = l + r >> 1;
        sort(nums, l, m);
        sort(nums, m, r);
        merge(nums, l, m, r);
    }

    private static void merge(int[] nums, int l, int m, int r) {
        int p1 = l, p2 = m, s = l;
        while (p1 < m && p2 < r)
            if (nums[p1] <= nums[p2])
                temp[s++] = nums[p1++];
            else
                temp[s++] = nums[p2++];
        while (p1 < m) temp[s++] = nums[p1++];
        while (p2 < r) temp[s++] = nums[p2++];
        for (int i = l; i < r; i++) nums[i] = temp[i];
    }
}
