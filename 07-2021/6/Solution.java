import java.util.concurrent.*;
import multithreading.*;

class Solution {
    int count = 0;

    public synchronized void increment() {
        System.out.println("Count : " + ++count + "\n");
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadImplementation1 t1 = new ThreadImplementation1();
        ThreadImplementation2 t = new ThreadImplementation2();

        Thread t2 = new Thread(t);
        t1.start();
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            System.out.println(e);
        }
        t2.start();
        try {
            t2.join();
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println();

        System.out.println(t1.getPriority());
        System.out.println(t2.isAlive());
        System.out.println(t1.getState());
        System.out.println(t2.getThreadGroup());

        System.out.println("-------------------------------------------");

        Solution s = new Solution();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++)
                    s.increment();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++)
                    s.increment();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("-------------------------------------------");

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("i1 : " + i);
            }
        }));
        // ----------------------------------------------
        service.execute(new Thread(() -> {
            for (int i = 10; i < 20; i++)
                System.out.println("i2 : " + i);
        }));
        service.shutdown();
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);

        while(!service.isTerminated()){}

        System.out.println("-------------------------------------------");

        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 20; i < 30; i++) {
            Runnable obj = new GetCount(i);
            pool.execute(obj);
        }
        pool.shutdown();

        while(!service.isTerminated()){}

        System.out.println("-------------------------------------------");
    }
}