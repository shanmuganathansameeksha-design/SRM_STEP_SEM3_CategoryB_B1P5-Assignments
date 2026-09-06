```java
class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {

        this.memberId = memberId;

        // Defensive copy while entering
        this.bookIds = bookIds.clone();
    }

    public String[] getBookIds() {

        // Defensive copy while leaving
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {

        String[] newBookIds = bookIds.clone();

        newBookIds[index] = newId;

        return new LoanReceipt(memberId, newBookIds);
    }

    public String getMemberId() {
        return memberId;
    }
}


class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}


class CirculationLedger {

    private static String branchCode;

    // Static block
    static {
        branchCode = "PT-001";
    }

    static String processNightlyCirculation(LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (int i = 0; i < receipts.length; i++) {

            LoanReceipt receipt = receipts[i];

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               referenceOnly + " reference-only | " +
               regular + " regular";
    }
}


public class Main {

    public static void main(String[] args) {

        LoanReceipt r = new LoanReceipt(
            "LIB-8841",
            new String[]{"BK-100", "BK-101"}
        );

        String[] ids = r.getBookIds();

        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);

        LoanReceipt corrected =
            r.withCorrectedBookId(1, "BK-102");

        System.out.println(r.getBookIds()[0] + ", " +
                           r.getBookIds()[1]);

        System.out.println(corrected.getBookIds()[0] + ", " +
                           corrected.getBookIds()[1]);


        LoanReceipt[] receipts = {

            new ReferenceOnlyLoanReceipt(
                "LIB-001",
                new String[]{"BK-200"},
                "Reading Room 3"
            ),

            null,

            new LoanReceipt(
                "LIB-002",
                new String[]{"BK-201"}
            )
        };

        System.out.println(
            CirculationLedger.processNightlyCirculation(receipts)
        );
    }
}
```

**Output:**

```text
BK-100
BK-100, BK-101
BK-100, BK-102
2 processed | 1 null skipped | 1 reference-only | 1 regular
```
