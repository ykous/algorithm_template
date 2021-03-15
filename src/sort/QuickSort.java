package sort;

public class QuickSort implements Sort {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 56, 2312, 41, 24, 125, 312, 5, 14, 32, 41, 2, 512, 353, 1, 4};
        new QuickSort().sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int m = partition(nums, left, right);
        quickSort(nums, left, m - 1);
        quickSort(nums, m + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int l = left + 1;
        int r = right;
        while (l <= r) {
            if (nums[l] > pivot) {
                int t = nums[l];
                nums[l] = nums[r];
                nums[r--] = t;
            } else {
                l++;
            }
        }
        nums[left] = nums[r];
        nums[r] = pivot;
        return r;
    }
}
