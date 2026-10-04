interface WashType {
    String getName();
    int getDuration();
    double getCharge();
}

class QuickWash implements WashType {
    public String getName() {
        return "Quick";
    }

    public int getDuration() {
        return 30;
    }

    public double getCharge() {
        return 20;
    }
}

class NormalWash implements WashType {
    public String getName() {
        return "Normal";
    }

    public int getDuration() {
        return 45;
    }

    public double getCharge() {
        return 30;
    }
}

class HeavyWash implements WashType {
    public String getName() {
        return "Heavy";
    }

    public int getDuration() {
        return 60;
    }

    public double getCharge() {
        return 45;
    }
}

class Student {
    private String name;

    Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;

    WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public void start() {
        System.out.println(washType.getName() + " wash started on "
                + machine.getId() + " for " + student.getName()
                + " (" + washType.getDuration() + " min).");

        System.out.printf("Charge: ₹%.2f%n", washType.getCharge());
    }
}

class WashingMachine {
    private String id;
    private boolean busy;

    WashingMachine(String id) {
        this.id = id;
        busy = false;
    }

    public String getId() {
        return id;
    }

    public boolean isFree() {
        return !busy;
    }

    public void startWash(Student student, WashType washType) {

        if (busy) {
            System.out.println("Machine " + id + " is currently busy.");
            return;
        }

        busy = true;

        WashCycle cycle = new WashCycle(student, this, washType);
        cycle.start();
    }

    public void completeWash() {

        if (busy) {
            busy = false;
            System.out.println(id + " cycle completed.");
            System.out.println(id + " is now free.");
        }
    }
}

public class LaundryDemo {
    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        WashType quick = new QuickWash();
        WashType heavy = new HeavyWash();
        WashType normal = new NormalWash();

        m1.startWash(asha, quick);

        m1.startWash(ravi, heavy);

        m2.startWash(ravi, heavy);

        m1.completeWash();

        m1.startWash(neha, normal);
    }
}