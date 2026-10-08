public class TicketBooking {
    static int tickets = 100;
    static void bookTicket() {
        if (tickets > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            tickets--;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread[] users = new Thread[250];
        for (int i = 0; i < users.length; i++) {
            users[i] = new Thread(() -> {
                bookTicket();
            });
            users[i].start();
        }
        for (Thread user : users) {
            user.join();
        }
        System.out.println("Tickets available: " + tickets);
    }
}
