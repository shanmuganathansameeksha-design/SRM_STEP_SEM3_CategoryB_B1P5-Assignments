interface MembershipPlan {

    String getName();

    double calculateFee();
}

class MonthlyPlan implements MembershipPlan {

    public String getName() {
        return "Monthly";
    }

    public double calculateFee() {
        return 1000;
    }
}

class QuarterlyPlan implements MembershipPlan {

    public String getName() {
        return "Quarterly";
    }

    public double calculateFee() {
        return 1000 * 3 * 0.90;
    }
}

class AnnualPlan implements MembershipPlan {

    public String getName() {
        return "Annual";
    }

    public double calculateFee() {
        return 1000 * 12 * 0.75;
    }
}

class Member {

    private String name;

    Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Membership {

    private Member member;
    private MembershipPlan plan;
    private String status;

    Membership(Member member, MembershipPlan plan) {

        this.member = member;
        this.plan = plan;
        this.status = "Active";
    }

    public void checkIn() {

        if (status.equals("Active")) {

            System.out.println(
                    member.getName() +
                    " checked in successfully."
            );

        } else {

            System.out.println(
                    "Check-in denied: " +
                    member.getName() +
                    "'s membership is " +
                    status + "."
            );
        }
    }

    public void freeze() {

        if (status.equals("Active")) {

            status = "Frozen";

            System.out.println(
                    member.getName() +
                    "'s membership frozen."
            );

            System.out.println(
                    "Status: " + status
            );

        } else if (status.equals("Expired")) {

            System.out.println(
                    "Cannot freeze an Expired membership."
            );

        } else {

            System.out.println(
                    "Membership is already Frozen."
            );
        }
    }

    public void unfreeze() {

        if (status.equals("Frozen")) {

            status = "Active";

            System.out.println(
                    member.getName() +
                    "'s membership unfrozen."
            );

        } else {

            System.out.println(
                    "Cannot unfreeze membership in " +
                    status + " status."
            );
        }
    }

    public void expire() {

        status = "Expired";

        System.out.println(
                member.getName() +
                "'s membership expired."
        );

        System.out.println(
                "Status: " + status
        );
    }

    public String getStatus() {
        return status;
    }

    public void displayMembership() {

        System.out.printf(
                "%s membership created for %s. Fee: ₹%.2f. Status: %s%n",
                plan.getName(),
                member.getName(),
                plan.calculateFee(),
                status
        );
    }
}

public class FitZoneDemo {

    public static void main(String[] args) {

        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership ashaMembership =
                new Membership(
                        asha,
                        new QuarterlyPlan()
                );

        Membership raviMembership =
                new Membership(
                        ravi,
                        new MonthlyPlan()
                );

        ashaMembership.displayMembership();

        raviMembership.displayMembership();

        ashaMembership.checkIn();

        ashaMembership.freeze();

        ashaMembership.checkIn();

        raviMembership.expire();

        raviMembership.freeze();
    }
}