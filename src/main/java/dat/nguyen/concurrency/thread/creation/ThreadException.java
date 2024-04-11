package dat.nguyen.concurrency.thread.creation;

public class ThreadException {

  public static void main(String[] args) {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        throw new RuntimeException("Intentional Exception");
      }
    });

    thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
      @Override
      public void uncaughtException(Thread t, Throwable e) {
        System.out.println("A critical error happened in thread: " + t.getName() + " with error: " + e.getMessage());
      }
    });

    thread.start();
  }
}
