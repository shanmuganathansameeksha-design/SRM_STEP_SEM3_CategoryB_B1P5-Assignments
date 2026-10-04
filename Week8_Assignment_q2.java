import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Assignment {
    protected String title;
    protected int maxMarks;
    protected LocalDate dueDate;

    Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    abstract double applyPenalty(double marks, long lateDays);
}

class CodingAssignment extends Assignment {

    CodingAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    public double applyPenalty(double marks, long lateDays) {
        double penalty = lateDays * 0.10;
        return marks * (1 - penalty);
    }
}

class WrittenAssignment extends Assignment {

    WrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    public double applyPenalty(double marks, long lateDays) {
        double penalty = lateDays * 0.20;
        return marks * (1 - penalty);
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

class Submission {

    private Student student;
    private Assignment assignment;
    private LocalDate submissionDate;

    private String status;
    private double finalMarks;

    Submission(Student student, Assignment assignment,
               LocalDate submissionDate) {

        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
        this.status = "Submitted";
    }

    public void grade(double awardedMarks) {

        if (!status.equals("Submitted")) {
            System.out.println("Cannot grade this submission.");
            return;
        }

        long lateDays = 0;

        if (submissionDate.isAfter(assignment.getDueDate())) {
            lateDays = ChronoUnit.DAYS.between(
                    assignment.getDueDate(),
                    submissionDate
            );
        }

        finalMarks = assignment.applyPenalty(
                awardedMarks,
                lateDays
        );

        status = "Graded";

        System.out.printf(
                "%s graded: %.0f/%d",
                student.getName(),
                finalMarks,
                assignment.getMaxMarks()
        );

        if (lateDays > 0) {
            double percentagePenalty;

            if (assignment instanceof CodingAssignment)
                percentagePenalty = lateDays * 10;
            else
                percentagePenalty = lateDays * 20;

            System.out.printf(
                    " after %.0f%% late penalty",
                    percentagePenalty
            );
        }

        System.out.println();
        System.out.println("Status: " + status);
    }

    public void resubmit(LocalDate newDate) {

        if (status.equals("Graded")) {
            System.out.println(
                    "Cannot resubmit: '" +
                    assignment.getTitle() +
                    "' has already been graded."
            );
        }
    }

    public String getStatus() {
        return status;
    }
}

public class AssignmentDemo {

    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding = new CodingAssignment(
                "Linked List Lab",
                50,
                LocalDate.of(2026, 3, 10)
        );

        Assignment written = new WrittenAssignment(
                "Design Essay",
                50,
                LocalDate.of(2026, 3, 12)
        );

        Submission s1 = new Submission(
                asha,
                coding,
                LocalDate.of(2026, 3, 10)
        );

        Submission s2 = new Submission(
                ravi,
                written,
                LocalDate.of(2026, 3, 14)
        );

        System.out.println(
                "Asha's submission for 'Linked List Lab' received (on time)."
        );
        System.out.println("Status: " + s1.getStatus());

        System.out.println();

        System.out.println(
                "Ravi's submission for 'Design Essay' received (2 days late)."
        );
        System.out.println("Status: " + s2.getStatus());

        System.out.println();

        s1.grade(45);

        System.out.println();

        s2.grade(40);

        System.out.println();

        s1.resubmit(LocalDate.of(2026, 3, 11));
    }
}