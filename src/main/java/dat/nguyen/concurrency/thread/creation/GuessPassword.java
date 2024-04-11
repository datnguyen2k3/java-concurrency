package dat.nguyen.concurrency.thread.creation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GuessPassword {

  public static final int MAX_GUESSES = 9999;

  public static void main(String[] args) {
    PasswordChecker passwordChecker = new PasswordChecker();

    List<Thread> threads = new ArrayList<>();
    threads.add(new AscendingPasswordGuesser(passwordChecker));
    threads.add(new DescendingPasswordGuesser(passwordChecker));
    threads.add(new CountdownTime());

    for (Thread thread : threads) {
      thread.start();
    }
  }

  private static class PasswordChecker {
    private final int password;

    public PasswordChecker() {
      Random random = new Random();
      this.password = random.nextInt(9999) + 1;
      System.out.println("Password is: " + password);
    }

    public boolean isPasswordCorrect(int guess) {
      try {
        Thread.sleep(5);
      } catch (InterruptedException e) {
        System.out.println("Thread interrupted: " + Thread.currentThread().getName() + " with error: " + e.getMessage());
      }
      return guess == password;
    }
  }

  private static abstract class PasswordGuesser extends Thread {
    protected final PasswordChecker passwordChecker;

    public PasswordGuesser(PasswordChecker passwordChecker) {
      this.passwordChecker = passwordChecker;
      this.setName(this.getClass().getSimpleName());
      this.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public void start() {
      System.out.println("Starting thread: " + this.getName());
      super.start();
    }
  }

  private static class AscendingPasswordGuesser extends PasswordGuesser {
    public AscendingPasswordGuesser(PasswordChecker passwordChecker) {
      super(passwordChecker);
    }

    @Override
    public void run() {
      for (int guess = 1; guess <= MAX_GUESSES; guess++) {
        if (this.passwordChecker.isPasswordCorrect(guess)) {
          System.out.println(this.getName() + " found the password: " + guess);
          System.exit(0);
        }
      }
    }
  }

  private static class DescendingPasswordGuesser extends PasswordGuesser {
    public DescendingPasswordGuesser(PasswordChecker passwordChecker) {
      super(passwordChecker);
    }

    @Override
    public void run() {
      for (int guess = MAX_GUESSES; guess > 0; guess--) {
        if (this.passwordChecker.isPasswordCorrect(guess)) {
          System.out.println(this.getName() + " found the password: " + guess);
          System.exit(0);
        }
      }
    }
  }

  private static class CountdownTime extends Thread {
    @Override
    public void run() {
      for (int step = 10; step > 0; step--) {
        System.out.println("Time left: " + step + " seconds");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          System.out.println("Thread interrupted: " + Thread.currentThread().getName() + " with error: " + e.getMessage());
        }
      }
      System.out.println("Time's up!");
      System.exit(0);
    }
  }
}
