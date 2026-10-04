import java.util.*;

interface Seat {
    String getId();
    double getPrice();
}

class RegularSeat implements Seat {

    private String id;

    RegularSeat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return 150;
    }
}

class PremiumSeat implements Seat {

    private String id;

    PremiumSeat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return 250;
    }
}

class ReclinerSeat implements Seat {

    private String id;

    ReclinerSeat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return 400;
    }
}

class Customer {

    private String name;

    Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Show {

    private String showTime;
    private Set<String> bookedSeats;

    Show(String showTime) {
        this.showTime = showTime;
        bookedSeats = new HashSet<>();
    }

    public boolean isAvailable(Seat seat) {
        return !bookedSeats.contains(seat.getId());
    }

    public boolean bookSeat(Seat seat) {

        if (!isAvailable(seat)) {
            return false;
        }

        bookedSeats.add(seat.getId());
        return true;
    }

    public void releaseSeat(Seat seat) {
        bookedSeats.remove(seat.getId());
    }

    public String getShowTime() {
        return showTime;
    }
}

class Booking {

    private Customer customer;
    private Show show;
    private List<Seat> seats;
    private boolean cancelled;

    Booking(Customer customer, Show show) {
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>();
        this.cancelled = false;
    }

    public boolean addSeat(Seat seat) {

        if (seats.size() >= 6) {
            System.out.println(
                    "Cannot book more than 6 seats."
            );
            return false;
        }

        if (!show.bookSeat(seat)) {
            System.out.println(
                    "Seat " + seat.getId() +
                    " is already booked for this show."
            );
            return false;
        }

        seats.add(seat);
        return true;
    }

    public double getTotal() {

        double total = 0;

        for (Seat seat : seats) {
            total += seat.getPrice();
        }

        return total;
    }

    public void confirm() {

        System.out.print(
                "Booking confirmed for " +
                customer.getName() + ": "
        );

        for (int i = 0; i < seats.size(); i++) {

            System.out.print(seats.get(i).getId());

            if (i < seats.size() - 1)
                System.out.print(", ");
        }

        System.out.printf(
                ". Total: ₹%.2f%n",
                getTotal()
        );
    }

    public void cancel(boolean showStarted) {

        if (showStarted) {
            System.out.println(
                    "Cannot cancel: show has already started."
            );
            return;
        }

        if (cancelled)
            return;

        for (Seat seat : seats) {
            show.releaseSeat(seat);
        }

        cancelled = true;

        System.out.println(
                customer.getName() +
                "'s booking cancelled."
        );

        System.out.print("Seats ");

        for (int i = 0; i < seats.size(); i++) {

            System.out.print(seats.get(i).getId());

            if (i < seats.size() - 1)
                System.out.print(", ");
        }

        System.out.println(" released.");
    }
}

public class TicketDemo {

    public static void main(String[] args) {

        Show show = new Show("7 PM");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Seat a1 = new RegularSeat("A1");
        Seat a2 = new RegularSeat("A2");
        Seat f5 = new PremiumSeat("F5");
        Seat r1 = new ReclinerSeat("R1");

        Booking ashaBooking =
                new Booking(asha, show);

        ashaBooking.addSeat(a1);
        ashaBooking.addSeat(a2);
        ashaBooking.addSeat(f5);

        ashaBooking.confirm();

        Booking raviBooking =
                new Booking(ravi, show);

        raviBooking.addSeat(a2);

        raviBooking.addSeat(r1);
        raviBooking.confirm();

        ashaBooking.cancel(false);

        Booking nehaBooking =
                new Booking(neha, show);

        nehaBooking.addSeat(a2);
        nehaBooking.confirm();
    }
}