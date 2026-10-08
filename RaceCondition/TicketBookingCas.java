import java.util.concurrent.atomic.AtomicInteger;
public class TicketBookingCas {
    static AtomicInteger tickets = new AtomicInteger(100);
    static void bookTicket() {
        while (true) {
            int current = tickets.get();
            if (current == 0) {
                return;
            }
            int updated = current - 1;
            if (tickets.compareAndSet(current, updated)) {
                return;
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread[] users = new Thread[200];
        for (int i = 0; i < users.length; i++) {
            users[i] = new Thread(() -> {
                bookTicket();
            });
            users[i].start();
        }
        for (Thread user : users) {
            user.join();
        }
        System.out.println("Tickets available: " + tickets.get());
    }
}