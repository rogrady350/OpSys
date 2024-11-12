// JavaSemaphoreTest1.java

import java.util.concurrent.Semaphore;
 
class Shared {
    static int count = 0;
}
 
class TestThread extends Thread {
    Semaphore sem;
    String threadName;
    
    public TestThread(Semaphore sem, String threadName) {
        super(threadName);
        this.sem = sem;
        this.threadName = threadName;
    }
 
    @Override
    public void run() {         
        if(this.getName().equals("Test1")) {
            System.out.println("Starting " + threadName);
            try {
                System.out.println(threadName + " is waiting for a permit.");
                //Acquiring the lock (Entry section)
                sem.acquire(); // same as "waiting" for a semaphore
                System.out.println(threadName + " gets a permit.");               
                
                // Critical section:
                for(int i=0; i < 30; i++) {
                    Shared.count++;
                    System.out.println(threadName + ": " + Shared.count);
                    Thread.sleep(10);
                }
                
            } catch (InterruptedException exc) {
                    System.out.println(exc);
            }
 
            //Release the permit.
            System.out.println(threadName + " releases the permit.");
            // Exit section:
            sem.release(); // same as "signaling" the semaphore
        } else { // thread's name is not "Test1"
            System.out.println("Starting " + threadName);
            try {
                System.out.println(threadName + " is waiting for a permit.");
                // acquiring the lock
                sem.acquire();
                System.out.println(threadName + " gets a permit.");
                for(int i=0; i < 30; i++)
                {
                    Shared.count--;
                    System.out.println(threadName + ": " + Shared.count);
                    Thread.sleep(10);
                }
            } catch (InterruptedException exc) {
                System.out.println(exc);
            }
            // Release the permit.
            System.out.println(threadName + " releases the permit.");
            sem.release();
        }
    }
}
 
public class JavaSemaphoreTest1 {
    public static void main(String args[]) throws InterruptedException {
        Semaphore sem = new Semaphore(1); // permit = 1 creates a binary semaphore (mutex)
        TestThread testThread1 = new TestThread(sem, "Test1");
        TestThread testThread2 = new TestThread(sem, "Test2");
 
        testThread1.start();
        testThread2.start();
 
        testThread1.join();
        testThread2.join();
 
        System.out.println("count: " + Shared.count);
    }
}
