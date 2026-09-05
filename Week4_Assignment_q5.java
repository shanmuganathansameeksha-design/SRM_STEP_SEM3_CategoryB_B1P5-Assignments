```java
class Payment {

    void pay(double amount) {
        System.out.println("Paid (cash): Rs " + amount);
    }
}

class CardPayment extends Payment {

    void payWithProcessingFee(double amount) {
        double total = amount + (amount * 0.02);

        System.out.println(
            "Charged (card, incl. fee): Rs " + total
        );
    }
}

public class Main {

    static double totalCollected = 0;

    static void processTransaction(
        Payment payment,
        double amount
    ) {

        if (payment instanceof CardPayment) {

            CardPayment card =
                (CardPayment) payment;

            card.payWithProcessingFee(amount);

            totalCollected =
                totalCollected + (amount * 1.02);

        } else {

            payment.pay(amount);

            totalCollected =
                totalCollected + amount;
        }
    }

    public static void main(String[] args) {

        Payment[] payments = {
            new CardPayment(),
            new Payment(),
            new CardPayment(),
            new Payment(),
            new CardPayment()
        };

        double[] amounts = {
            100, 50, 200, 75, 120
        };

        for (int i = 0; i < payments.length; i++) {

            processTransaction(
                payments[i],
                amounts[i]
            );
        }

        System.out.println(
            "Total Collected: Rs " +
            totalCollected
        );
    }
}
```

**Output:**

```text
Charged (card, incl. fee): Rs 102.0
Paid (cash): Rs 50.0
Charged (card, incl. fee): Rs 204.0
Paid (cash): Rs 75.0
Charged (card, incl. fee): Rs 122.4
Total Collected: Rs 553.4
```
