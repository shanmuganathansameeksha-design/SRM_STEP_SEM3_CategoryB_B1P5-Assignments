public class AttendanceDisorderScore {

    public static long countDisorderScore(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int[] temp = new int[arr.length];
        return mergeSortAndCount(arr, temp, 0, arr.length - 1);
    }

    private static long mergeSortAndCount(int[] arr, int[] temp, int left, int right) {
        long count = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;

            count += mergeSortAndCount(arr, temp, left, mid);
            count += mergeSortAndCount(arr, temp, mid + 1, right);
            count += mergeAndCount(arr, temp, left, mid, right);
        }
        return count;
    }

    private static long mergeAndCount(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        long swaps = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                swaps += (mid - i + 1);
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }

        return swaps;
    }

    public static void main(String[] args) {
        int[] attendance1 = {8, 4, 2, 1};
        System.out.println("Disorder Score: " + countDisorderScore(attendance1));

        int[] attendance2 = {1, 20, 6, 4, 5};
        System.out.println("Disorder Score: " + countDisorderScore(attendance2));
    }
}

Time Complexity: O(n log n) — Uses modified Merge Sort to count inversions across divide-and-conquer steps efficiently.
Space Complexity: O(n) — Uses an auxiliary array temp for merging sub-arrays.