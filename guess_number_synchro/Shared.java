//Robert O'Grady	S1365114

// Shared public static var(s) for thread synchronization
public class Shared
{
   // The secret number that the GuessNumber threads must guess:
   public static int secret = -1;

   // The counter that the GuessNumber threads will increment after
   // they guess correctly; must be reset by ThreadClassDemo::main()
   // to 0 after all four threads guess correctly:
   public static int numThreadsDone = 0;

   // The total number of guesses made by the GuessNumber threads;
   // must be reset by ThreadClassDemo::main() to 0 after all four threads
   // guess correctly:
   public static int totalThreadGuesses = 0;
}
