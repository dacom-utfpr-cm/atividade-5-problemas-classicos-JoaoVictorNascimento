import java.util.concurrent.Semaphore;
import java.util.ArrayList;

class DiningPhilosopher implements Resource {

    int n = 0;
    Semaphore[] fork = null;

    public DiningPhilosopher(int initN) {
        n = initN;
        fork = new Semaphore[n];
        for (int i = 0; i < n; i++) {
            fork[i] = new Semaphore(1);
        }
    }

    @Override
    public void pickup(int i) {
        try {
            fork[i].acquire();
            fork[(i + 1) % n].acquire();
        } catch (InterruptedException ex) {
        }
    }

    @Override
    public void drop(int i) {
        fork[i].release();
        fork[(i + 1) % n].release();
    }

    public static void main(String[] args) {
        Integer qntPhilo = 5;
        Semaphore semaphore = new Semaphore(qntPhilo-1);
        DiningPhilosopher philosopher = new DiningPhilosopher(qntPhilo);
        for (int i = 0; i < qntPhilo; i++) {
            new Thread(new Philosopher(i, philosopher, semaphore)).start();
        }
    }
}
