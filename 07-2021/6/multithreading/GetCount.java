package multithreading;

public class GetCount implements Runnable {
    int count = 0;

    public GetCount(int a) {
        this.count += a;
    }

    public void run() {
        synchronized (this) {
            System.out.println("Thread Name : " + Thread.currentThread().getName());
            System.out.println("Thread Id : " + Thread.currentThread().getId());
            System.out.println("Value : " + this.count + "\n");
        }
    }
}