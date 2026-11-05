public class PlacementCutoff {

    public static int kthHighest(int[] marks, int k) {
        int n = marks.length;
        int targetIndex = n - k;
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int pivotIndex = partition(marks, left, right);

            if (pivotIndex == targetIndex) {
                return marks[pivotIndex];
            } else if (pivotIndex < targetIndex) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }

        return -1;
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] marks1 = {72, 95, 60, 88, 79};
        System.out.println(kthHighest(marks1, 2));

        int[] marks2 = {50, 90, 90, 70};
        System.out.println(kthHighest(marks2, 2));
    }
}

Time Complexity:

Average Case: O(n) — Quickselect eliminates roughly half the search space at each step.
Worst Case: O(n^2) — Occurs if bad pivots are consistently selected (e.g., already sorted array with end pivot).
Extra Space Complexity: O(1) — Iterative selection modifies the array in-place without auxiliary memory or recursion stack.
Stability: Unstable — Swapping elements relative to the pivot disrupts the relative order of duplicate items.