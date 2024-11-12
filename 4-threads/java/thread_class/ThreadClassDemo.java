//Robert O'Grady     S1365114
// ThreadClassDemo.java

public class ThreadClassDemo {

   public static void main(String [] args) {
	   int guessNum = (int) (Math.random() * 100 + 1);
	   
	   Thread thread0 = new GuessNumber(guessNum);
	   thread0.start();
	 
	   
	   Thread thread1 = new GuessNumber(guessNum);
	   thread1.start();
	   
	   Thread thread2 = new GuessNumber(guessNum);
	   thread2.start();

	   Thread thread3 = new GuessNumber(guessNum);
	   thread3.start();
      
	   try {
		thread0.join();
		thread1.join();
		thread2.join();
		thread3.join();
	   } catch (InterruptedException e) {
		   System.out.println("Thread interrupted.");
		   e.printStackTrace();
	   }
	   
      System.out.println("All GuessNumber threads complete after " + Shared.totalThreadGuesses + " guesses."); 
   }
}
