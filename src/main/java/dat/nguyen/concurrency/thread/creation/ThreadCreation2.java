package dat.nguyen.concurrency.thread.creation;

public class ThreadCreation2 {

  public static void main(String[] args) {
    Thread thread = new NewThread();

    thread.start();
  }

  private static class NewThread extends Thread {
    @Override
    public void run() {
      System.out.println("We are in thread: " + Thread.currentThread().getName());
    }
  }
}
