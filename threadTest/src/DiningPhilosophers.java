// DiningPhilosophers.java -
// Study the class Semaphore and the class Philosopher given below.

// A Semaphore object maintains a private integer which can only be
// accessed by the operations P (wait) and V (signal). These are declared
// as synchronized which means the procedures (methods) execute
// indivisibly (atomically) on the semaphore's value when they are invoked
// by different threads. The Philosopher class is a runnable object class
// and when an object of this class is created and then started, it starts
// executing a method called run().

// See https://www.baeldung.com/java-wait-notify for explanation of the
// wait() and notify() Java thread methods for concurrency.

// Note: The simulation could deadlock if the threads were scheduled in such a
//       way that all 5 philosophers picked up one chopstick each.



// The class DiningPhilosophers contains the main program and this is
// where execution begins.

class DiningPhilosophers {
    public static void main(String args[]) {
        Semaphore chopSticks[];
        Philosopher person[];

        // Create an array of five Semaphore Object Reference Handles
        chopSticks = new Semaphore[5];

        // Create five Semaphore Objects and assign to the array
        for (int i=0; i<5; i++)
            chopSticks[i] = new Semaphore(1); // Semaphore initial value = 1;
                                              // a binary semaphore

        // Create an array of five Philosopher Thread Object Reference Handles
        person = new Philosopher[5];

 
        // Create and initiate five Philosopher Thread Objects
        for (int i=0; i<5; i++) {
            person[i] = new Philosopher(i, chopSticks);
            person[i].start();
        }
    }
}

 

 

// The Semaphore class contains methods declared as synchronized (atomic).
// A monitor therefore, will ensure that access to Semaphore methods is
// mutually exclusive among threads.

class Semaphore {

    private int value;

    public Semaphore(int value) {
        this.value = value;
    }

   // The wait operation for the Semaphore
    public synchronized void p() {
        while (value == 0) {
            try {
                System.out.println("ChopStick in use");
                wait();   // The calling thread waits until semaphore
                          // becomes free
            } catch(InterruptedException e) {}
        }

        value = value - 1;
    }

    // The signal operation for the Semaphore
    public synchronized void v() {
        value = value + 1;
        notify(); // Wakes up other Philosopher threads in case they're waiting for a chopstick.
    }
}

 

 


class Philosopher extends Thread {
    private int myName;
    private Semaphore chopSticks[];

    public Philosopher(int myName, Semaphore chopSticks[]) {
        this.myName = myName;
        this.chopSticks = chopSticks;
    }

    public void run() {
        while (true) {
            System.out.println("Philosopher " + myName + " thinking.");

            // Think for a random amount of time:
            try {
                sleep ((int)(Math.random()*20000));
            } catch (InterruptedException e) {}

            System.out.println("Philosopher " + myName + " hungry.");

            chopSticks[myName].p();       // Acquire left
         
            chopSticks[(myName+1)%5].p(); // Acquire right

            System.out.println("Philosopher " + myName + " eating.");

            // Eat for a random amount of time:
            try {
                sleep ((int)(Math.random()*10000));
            } catch (InterruptedException e) {}

            chopSticks[myName].v();       // Release left

            chopSticks[(myName+1)%5].v(); // Release right
        }
    }
}
