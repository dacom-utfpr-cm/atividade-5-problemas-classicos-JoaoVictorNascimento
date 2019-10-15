import java.util.concurrent.Semaphore;

class Philosopher implements Runnable {
    int id = 0;
    Resource fork = null;
    private Semaphore semaphore;

    public Philosopher(int initId, Resource initr, Semaphore semaphore) {
        id = initId;
        fork = initr;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while (true) {
            try {
                semaphore.acquire();
                System.out.println("Phil " + id + " thinking");
                Thread.sleep(1000);
                System.out.println("Phil " + id + " hungry");
                fork.pickup(id);
                System.out.println("Phil " + id + " eating");
                Thread.sleep(1000);
                fork.drop(id);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}