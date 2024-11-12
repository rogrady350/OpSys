//Robert O'Grady	S1365114

// GuessNumber.java - guess secret number thread

import java.util.concurrent.Semaphore;

public class GuessNumber extends Thread {
   Semaphore sem;
   
   public GuessNumber(Semaphore sem) {
	   this.sem = sem;
   }

   public void run() {
      int guess = 0;

      while(true) {
              int counter = 0;

              while(guess != Shared.secret) {
                 try {
                    Thread.sleep(50);
                 } catch (InterruptedException e) { }

                 guess = (int) (Math.random() * 100 + 1);
                 counter++;
              } // end while

              if (counter > 0) {
            	 try {
            		//entry section
            		System.out.println(this.getName() +  " is waiting for permit"); 
					sem.acquire();
					System.out.println(this.getName() +  " gets permit"); 
					
					//critical section
	                // Signal correct guess and update shared variables:
	                Shared.numThreadsDone++;
	                Shared.totalThreadGuesses += counter;
	                System.out.println("** Correct! " + this.getName() + " in " + counter + " guesses.**");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					//exit section
					sem.release();
					System.out.println(this.getName() + " release permit");
				}
            	 
              }
              
              try {
                 // Let the thread sleep a bit.
                 Thread.sleep(10); // NECESSARY
              } catch (InterruptedException e) { 
            	  System.out.println("thread interupted");
              }
              
      } // end while

   } // end run()

} // end class
