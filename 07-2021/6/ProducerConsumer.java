import java.util.LinkedList;

class Setup {
    LinkedList<Integer> list = new LinkedList<Integer>();
    int size = 1;

    public void produce() throws InterruptedException {
        int value = 0;

        while (true) {
            synchronized (this) {
                while (list.size() == size) {
                    wait();
                }
                System.out.println("Value Produced : " + value);
                list.add(value++);
                notify();
                Thread.sleep(1000);
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (list.size() == 0) {
                    wait();
                }
                int value = list.removeFirst();
                System.out.println("Value Consumed : " + value);
                notify();
                Thread.sleep(1000);
            }
        }
    }
}

class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        final Setup setup = new Setup();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    setup.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    setup.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        

    }
}