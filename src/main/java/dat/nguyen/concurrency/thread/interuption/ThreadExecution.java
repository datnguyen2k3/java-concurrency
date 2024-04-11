package dat.nguyen.concurrency.thread.interuption;

public class ThreadExecution {

public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 1_000_000; i++) {
          System.out.println("Number: " + i);
        }
      }
    });

    thread.setDaemon(true);

    thread.start();
    Thread.sleep(10);
    thread.interrupt();
  }
}
