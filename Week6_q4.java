class GymMember {

    String memberId;
    int monthlyFee;
    private int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {

        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid Member ID");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        sessionsAttended = 0;
    }

    void attendSession() {
        sessionsAttended++;
    }

    int getSessionsAttended() {
        return sessionsAttended;
    }

    void displayInfo() {
        System.out.print(
            "Standard | Sessions: "
            + sessionsAttended
        );
    }
}

class PremiumMember extends GymMember {

    String trainerName;

    public PremiumMember(String memberId, int monthlyFee,
                          String trainerName) {

        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    void displayInfo() {

        System.out.print(
            "Premium | Trainer: "
            + trainerName
            + " | Sessions: "
            + getSessionsAttended()
        );
    }

    String getTrainerName() {
        return trainerName;
    }
}

public class Main {

    static String batchPrint(GymMember[] members) {

        StringBuilder result =
            new StringBuilder();

        for (GymMember member : members) {

            StringBuilder current =
                new StringBuilder();

            if (member instanceof PremiumMember) {

                PremiumMember premium =
                    (PremiumMember) member;

                current.append(
                    "Premium | Trainer: "
                    + premium.getTrainerName()
                    + " | Sessions: "
                    + premium.getSessionsAttended()
                    + " [Trainer via downcast: "
                    + premium.getTrainerName()
                    + "]"
                );

            } else {

                current.append(
                    "Standard | Sessions: "
                    + member.getSessionsAttended()
                );
            }

            result.append(current);
            result.append(" | ");
        }

        return result.toString();
    }

    public static void main(String[] args) {

        GymMember m1 =
            new GymMember("MEM6", 1000);

        PremiumMember m2 =
            new PremiumMember(
                "MEM7", 2000, "Coach Riya");

        GymMember[] members = {
            m1, m2
        };

        System.out.println(
            batchPrint(members)
        );

        /*
        GymMember plain =
            new GymMember("MEM8", 1000);

        PremiumMember bad =
            (PremiumMember) plain;

        This causes ClassCastException.
        */
    }
}