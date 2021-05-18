package sort;


import java.util.Arrays;

// 由于快排特殊性，采用闭合区间表示法
// 快速排序有两种思想，这两种思想的大纲都是划分，划分成两个部分，然后对这两个部分再分别划分，从而最终使得序列从任何一个局部看都满足划分的性质

// 第一种思想是，每一次划分将序列划分成比其小，相等和比之大的三个部分，并递归的对小的部分和大的部分再次划分
// 这种思想虽然看上去很直白，但是代码较长，而且代码逻辑更为复杂

// 第二种思想是把序列划分成小于等于，和大于等于两个部分，然后再分别对这两个部分分别进行划分操作
// 这种思想的好处就是代码简短，但代码不够直白，需要辅以注释
public class QuickSort {

    public static void main(String[] args) {
        int[] ints = new int[100000];
        for (int i = 0; i < 100000; i++) {
            ints[i] = i + 1;
        }
        sort(ints);
        System.out.println(Arrays.toString(ints));
    }

    static void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }


    static void sort(int[] nums, int l, int r) {
        if (l >= r) return;
        int p = nums[l + r >> 1] /* 防止极端情况下的栈溢出 */, i = l - 1, j = r + 1;
        while (i < j) {
            do i++; while (nums[i] < p);
            do j--; while (nums[j] > p);
            if (i < j) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
        // 此时，[l,j] 为 <= 区间，[i,r] 为 >= 区间
        // 但是由于 i 可能等于 j 导致死循环，我们必须采用 [l,j] ,[j + 1,r] 区间分别划分
        // 用 [l,i - 1] 和 [i , r] 也不是不行， 但是 p 就不可以取 p[l]（当然也就不能取 l+r >> 1） 了，这都属于边界条件
        sort(nums, l, j);
        sort(nums, j + 1, r);
    }


}
