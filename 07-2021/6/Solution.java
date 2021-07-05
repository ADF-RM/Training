class ThreadImplementation extends Thread {
    public void run() {
        Thread.currentThread().setPriority(10);
        for (int i = 0; i < 5; i++) {
            System.out.println("Thead 1 : \n{\nPriority : " + 
            Thread.currentThread().getPriority() + 
            "\nName : " + 
            Thread.currentThread().getName() +
            "\nAlive : " +
            Thread.currentThread().isAlive() +
            "\n}\n"
            );
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

class ThreadImplementation2 implements Runnable {
    public void run() {
        Thread.currentThread().setPriority(1);
        for (int i = 0; i < 5; i++) {
            System.out.println("Thead 2 : \n{\nPriority : " + 
            Thread.currentThread().getPriority() + 
            "\nName : " + 
            Thread.currentThread().getName() +
            "\nAlive : " +
            Thread.currentThread().isAlive() +
            "\n}\n"
            );
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

class Solution {
    int count = 0;
    public synchronized void increment(){
        System.out.println("Count : " + ++count + "\n");
    }
    public static void main(String[] args) {
        ThreadImplementation t1 = new ThreadImplementation();
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
        Thread thread1 = new Thread(
            new Runnable() {
                public void run(){
                    for ( int i = 0; i < 10; i++ )
                    s.increment();
                }
            }
        );
        Thread thread2 = new Thread(
            new Runnable() {
                public void run(){
                    for ( int i = 0; i < 10; i++ )
                    s.increment();
                }
            }
        );

        thread1.start();
        thread2.start();
    }
}