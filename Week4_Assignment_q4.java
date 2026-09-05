```java
class MembershipCard {

    static String libraryName;
    static String validUntil;

    String studentName;

    // Static block
    static {
        libraryName = "SRM Central Library";
        validUntil = "May 2027";

        System.out.println("Library info loaded");
    }

    MembershipCard(String studentName) {
        this.studentName = studentName;
    }
}

public class Main {
    public static void main(String[] args) {

        String[] names = {
            "Ananya",
            "Rohan",
            "Priya",
            "Arjun",
            "Sneha"
        };

        for (int i = 0; i < names.length; i++) {

            MembershipCard card =
                new MembershipCard(names[i]);

            System.out.println(
                "Membership card issued: " +
                card.studentName
            );
        }
    }
}
```

**Output:**

```text
Library info loaded
Membership card issued: Ananya
Membership card issued: Rohan
Membership card issued: Priya
Membership card issued: Arjun
Membership card issued: Sneha
```

Notice 👉 **`Library info loaded` prints only once**, even though 5 objects are created.
