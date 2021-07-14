package multithreading;

public class ThreadImplementation2 implements Runnable {
    public void run() {
        Thread.currentThread().setPriority(1);
        for (int i = 0; i < 5; i++) {
            System.out.println("Thead 2 : \n{\nPriority : " + Thread.currentThread().getPriority() + "\nName : "
                    + Thread.currentThread().getName() + "\nAlive : " + Thread.currentThread().isAlive() + "\n}\n");
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}