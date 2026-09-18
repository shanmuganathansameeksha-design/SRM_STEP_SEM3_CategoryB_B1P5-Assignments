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
        System.out.println("Standard Member | Sessions: "
                + sessionsAttended);
    }

    static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0;
        int rejected = 0;

        for (String id : memberIds) {
            try {
                GymMember member = new GymMember(id, monthlyFee);
                signedUp++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
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
        System.out.println("Premium Member | Trainer: "
                + trainerName + " | Sessions: "
                + getSessionsAttended());
    }
}

public class Main {
    public static void main(String[] args) {

        PremiumMember p = new PremiumMember(
                "MEM01", 2000, "Coach Riya");

        p.attendSession();
        p.attendSession();

        System.out.println(p.getSessionsAttended());

        String[] members = {
            "MEM1", "GM1", "MEM2", " ", "MEM3"
        };

        System.out.println(
            GymMember.signUpBatch(members, 1000)
        );
    }
}