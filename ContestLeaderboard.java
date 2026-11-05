class Entry {
    String name;
    int marks;

    Entry(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return name + " " + marks;
    }
}

public class ContestLeaderboard {

    public static Entry[] leaderboard(Entry[] entries) {
        int n = entries.length;
        if (n == 0) return new Entry[0];

        int maxMarks = 100;
        int[] count = new int[maxMarks + 1];
        Entry[] output = new Entry[n];

        for (int i = 0; i < n; i++) {
            int key = maxMarks - entries[i].marks;
            count[key]++;
        }

        for (int i = 1; i <= maxMarks; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int key = maxMarks - entries[i].marks;
            output[count[key] - 1] = entries[i];
            count[key]--;
        }

        return output;
    }

    public static void main(String[] args) {
        Entry[] entries = {
            new Entry("Asha", 90),
            new Entry("Ravi", 75),
            new Entry("Neha", 90),
            new Entry("Kiran", 100),
            new Entry("Meera", 75)
        };

        Entry[] result = leaderboard(entries);

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i < result.length - 1 ? ", " : ""));
        }
        System.out.println();
    }
}

Complexity Analysis & Properties:

Time Complexity: O(n + 101) — Iterates through the $n$ entries to count, builds cumulative frequencies over $101$ keys (0 to 100), and traverses $n$ entries backward to 
place them into the output array.

Extra Space Complexity: O(n + 101) auxiliary space — Requires an output array of size n and a frequency count array of size 101.

Stability: Stable — Iterating from right-to-left during output placement preserves the relative input order for students with equal marks.