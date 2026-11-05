public class NoticeBoardShuffle {

    public static int[] bubbleStats(int[] scores) {
        int passes = 0;
        int totalSwaps = 0;
        int n = scores.length;

        for (int i = 0; i < n - 1; i++) {
            passes++;
            boolean swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                if (scores[j] > scores[j + 1]) {
                    int temp = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = temp;

                    swapped = true;
                    totalSwaps++;
                }
            }

            if (!swapped) {
                break;
            }
        }

        if (n <= 1) {
            passes = (n == 0) ? 0 : 1;
        }

        return new int[]{passes, totalSwaps};
    }

    public static void main(String[] args) {
        int[] scores1 = {5, 1, 4, 2, 8};
        int[] stats1 = bubbleStats(scores1);
        System.out.print("Sorted Array: ");
        printArray(scores1);
        System.out.println("passes = " + stats1[0] + ", swaps = " + stats1[1]);

        int[] scores2 = {1, 2, 3, 4};
        int[] stats2 = bubbleStats(scores2);
        System.out.print("Sorted Array: ");
        printArray(scores2);
        System.out.println("passes = " + stats2[0] + ", swaps = " + stats2[1]);
    }

    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i < arr.length - 1 ? ", " : ""));
        }
        System.out.print("] ");
    }
}