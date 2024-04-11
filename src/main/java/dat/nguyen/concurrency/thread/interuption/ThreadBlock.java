package dat.nguyen.concurrency.thread.interuption;

public class ThreadBlock {

  public static void main(String[] args) {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(1000000);
        } catch (InterruptedException e) {
          System.out.println("Thread interrupted: " + Thread.currentThread().getName() + " with error: " + e.getMessage());
        }
      }
    });

    thread.start();
    thread.interrupt();
  }
}
