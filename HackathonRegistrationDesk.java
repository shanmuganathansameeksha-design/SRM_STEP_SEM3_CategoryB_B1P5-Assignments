class Team {
    String name;
    int size;

    Team(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String toString() {
        return name + "(" + size + ")";
    }
}

public class HackathonRegistrationDesk {

    public static void sortTeams(Team[] teams) {
        int n = teams.length;
        for (int i = 1; i < n; i++) {
            Team key = teams[i];
            int j = i - 1;

            while (j >= 0 && teams[j].size > key.size) {
                teams[j + 1] = teams[j];
                j--;
            }
            teams[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Team[] teams = {
            new Team("Pixel", 4),
            new Team("Byte", 2),
            new Team("Nova", 4),
            new Team("Echo", 3),
            new Team("Atom", 2)
        };

        sortTeams(teams);

        for (int i = 0; i < teams.length; i++) {
            System.out.print(teams[i] + (i < teams.length - 1 ? ", " : ""));
        }
        System.out.println();
    }
}

Time Complexity:

Best Case: O(n) (when the array is already sorted)
Average Case: O(n^2)
Worst Case: O(n^2) (when the array is sorted in reverse order)

Extra Space Complexity: O(1) auxiliary space (in-place sort)
Stability: Stable — By using teams[j].size > key.size strictly, equal elements are not swapped, preserving their initial registration order.