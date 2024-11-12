//Robert O'Grady	S1365114

// ThreadClassDemo.java

import java.util.concurrent.Semaphore;

public class ThreadClassDemo {
   public static void main(String [] args) {
	   Semaphore sem = new Semaphore(1);

      // Start GuessNumber threads:
      Thread thread1 = new GuessNumber(sem);
      thread1.setDaemon(true); // Must daemonize threads that have while(true).
      thread1.start();

      Thread thread2 = new GuessNumber(sem);
      thread2.setDaemon(true);
      thread2.start();

      Thread thread3 = new GuessNumber(sem);
      thread3.setDaemon(true);
      thread3.start();

      Thread thread4 = new GuessNumber(sem);
      thread4.setDaemon(true);
      thread4.start();

      while(true) {
              Shared.secret = (int) (Math.random() * 100 + 1);
              System.out.println("\nThe secret number is " + Shared.secret + ".\n");
              
              // Spin here until all threads guess correctly.
              while(Shared.numThreadsDone < 4) {
                  try {
                      Thread.sleep(50); // NECESSARY
                  } catch (InterruptedException e) {
                      System.out.println("Thread interrupted.");
                  }
              }

              System.out.println("\nAll four threads finished guessing in " +
                                 Shared.totalThreadGuesses + " guesses.");
              System.out.println("\nStarting the next round.");

              
              try {
                  Thread.sleep(2000);
                  // Reset shared vars for next round.
                  Shared.numThreadsDone = 0;
                  Shared.totalThreadGuesses = 0;
              } catch (InterruptedException e) {
                  System.out.println("Thread interrupted.");
              }
              
      } // end while

   } // end main()

} // end class

