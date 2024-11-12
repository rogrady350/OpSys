//Robert O'Grady     S1365114
// DisplayMessage.java - a thread

public class DisplayMessage implements Runnable {

   private String message;


   public DisplayMessage(String message) {
      this.message = message;
   }


   public void run() {

      while(true) {

         try {
            // Let the thread sleep a bit.
            Thread.sleep(1000);
            System.out.println(message);
         } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
         }

      }

   }

}
