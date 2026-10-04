import java.util.*;

interface NotificationChannel {

    void send(Student student, Notice notice);
}

class EmailChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {

        System.out.println(
                "[Email → " +
                student.getName() +
                "] " +
                notice.getTitle()
        );
    }
}

class SmsChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {

        System.out.println(
                "[SMS → " +
                student.getName() +
                "] " +
                notice.getTitle()
        );
    }
}

class AppChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {

        System.out.println(
                "[App → " +
                student.getName() +
                "] " +
                notice.getTitle()
        );
    }
}

class Student {

    private String name;
    private String department;

    private List<NotificationChannel> channels;

    Student(String name, String department) {

        this.name = name;
        this.department = department;

        channels = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void addChannel(NotificationChannel channel) {
        channels.add(channel);
    }

    public void receiveNotice(Notice notice) {

        for (NotificationChannel channel : channels) {
            channel.send(this, notice);
        }
    }
}

class Notice {

    private String title;
    private Set<String> departments;

    Notice(String title, String... departments) {

        this.title = title;

        this.departments =
                new HashSet<>();

        for (String dept : departments) {
            this.departments.add(dept);
        }
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getDepartments() {
        return departments;
    }

    public boolean isValid() {

        return title != null &&
               !title.trim().isEmpty() &&
               !departments.isEmpty();
    }
}

class NoticeBoard {

    private List<Student> students;

    NoticeBoard() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void postNotice(Notice notice) {

        if (!notice.isValid()) {

            System.out.println(
                    "Cannot post notice: " +
                    "At least one target department is required."
            );

            return;
        }

        System.out.print(
                "Notice '" +
                notice.getTitle() +
                "' posted to "
        );

        int count = 0;

        for (String dept :
                notice.getDepartments()) {

            System.out.print(dept);

            count++;

            if (count < notice.getDepartments().size())
                System.out.print(", ");
        }

        System.out.println();

        for (Student student : students) {

            if (notice.getDepartments()
                    .contains(student.getDepartment())) {

                student.receiveNotice(notice);
            }
        }
    }
}

public class NoticeDemo {

    public static void main(String[] args) {

        Student asha =
                new Student("Asha", "CSE");

        Student ravi =
                new Student("Ravi", "ECE");

        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());

        ravi.addChannel(new SmsChannel());

        NoticeBoard board =
                new NoticeBoard();

        board.addStudent(asha);
        board.addStudent(ravi);

        Notice notice1 =
                new Notice(
                        "Lab Closed Tomorrow",
                        "CSE"
                );

        board.postNotice(notice1);

        System.out.println();

        Notice notice2 =
                new Notice(
                        "Fee Deadline Extended",
                        "CSE",
                        "ECE"
                );

        board.postNotice(notice2);

        System.out.println();

        Notice notice3 =
                new Notice("Sports Day");

        board.postNotice(notice3);
    }
}