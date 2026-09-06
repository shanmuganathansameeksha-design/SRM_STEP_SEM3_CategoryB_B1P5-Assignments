```java
class LibraryMember {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    // Public no-argument constructor
    public LibraryMember() {
    }

    // Write-once property
    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {

        if (membershipId == null) {
            membershipId = id;
        }
    }

    // Name property
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Premium membership property
    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    // Write-only security answer
    public void setSecurityAnswer(String answer) {

        if (answer != null) {
            securityAnswer = new StringBuilder(answer)
                                .reverse()
                                .toString();
        }
    }
}

public class Main {

    public static void main(String[] args) {

        LibraryMember m = new LibraryMember();

        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);

        System.out.println(m.getMembershipId());

        // Second attempt is ignored
        m.setMembershipId("FAKE-0000");

        System.out.println(m.getMembershipId());

        System.out.println(m.isPremiumMember());

        m.setSecurityAnswer("BlueMountain");
    }
}
```

**Output:**

```text
LIB-8841
LIB-8841
true
```
