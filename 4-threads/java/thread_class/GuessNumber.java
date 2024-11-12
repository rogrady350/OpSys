//Robert O'Grady     S1365114
// GuessNumber.java - another thread

public class GuessNumber extends Thread {
   private int number;

   public GuessNumber(int number) {
      this.number = number;
   }

   public void run() {
      int counter = 0;
      int guess = 0;

      do {
         try {
            // Let the thread sleep a bit.
            Thread.sleep(500);
         } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
         }

         guess = (int) (Math.random() * 100 + 1);
         if (guess == number) {
        	 Shared.threadsDoneCount++;
         }
         counter++;
      } while(guess != number);

      System.out.println("** Correct!" + this.getName() + " in " + counter + " guesses.**");
      Shared.totalThreadGuesses = Shared.totalThreadGuesses + counter;
   }

}
