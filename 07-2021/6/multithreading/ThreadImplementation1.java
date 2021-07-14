package multithreading;

public class ThreadImplementation1 extends Thread {
    public void run() {
        Thread.currentThread().setPriority(10);
        for (int i = 0; i < 5; i++) {
            System.out.println("Thead 1 : \n{\nPriority : " + Thread.currentThread().getPriority() + "\nName : "
                    + Thread.currentThread().getName() + "\nAlive : " + Thread.currentThread().isAlive() + "\n}\n");
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}