//Robert O'Grady	S1365114

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SleepingBarber1 extends Thread {

	  /* PREREQUISITES */

	  /* we create the semaphores. First there are no customers and 
	   the barber is asleep so we call the constructor with parameter
	   0 thus creating semaphores with zero initial permits. 
	   Semaphore(1) constructs a binary semaphore, as desired. */
	    public static Semaphore customers = new Semaphore(0);
	    public static Semaphore barber = new Semaphore(0);
	    public static Semaphore accessSeats = new Semaphore(1);
	    public static Semaphore listAccess = new Semaphore(1);	//new semaphore to keep start and end times synchronized

	  /* we denote that the number of chairs in this barbershop is 5. */
	    //a. implement 5 chairs
	    public static final int CHAIRS = 5;

	  /* we create the integer numberOfFreeSeats so that the customers
	   can either sit on a free seat or leave the barbershop if there
	   are no seats available */
	   public static int numberOfFreeSeats = CHAIRS;
	   
	   //Lists to store time data
	   public static List<Long> enterTimes = new ArrayList<Long>();
	   public static List<Long> cuttingStartTimes = new ArrayList<Long>();
	   public static List<Long> cuttingEndTimes = new ArrayList<Long>();
	   public static List<Integer> custHairCuts = new ArrayList<Integer>();
	   
	/* THE CUSTOMER THREAD */
	class Customer extends Thread {
	  
	  /* we create the integer iD which is a unique ID number for every customer
	     and a boolean notCut which is used in the Customer waiting loop */
	  int iD;
	  boolean notCut=true;

	  /* Constructor for the Customer */ 
	  public Customer(int i) {
	    iD = i;
	  }

	  public void run() {   
	    while (notCut) {  // as long as the customer is not cut 
	      try {
	      System.out.println("Customer " + this.iD + " enters");	//e. shows customers entering
	      accessSeats.acquire();  //tries to get access to the chairs
	      		enterTimes.add(System.currentTimeMillis()); //e. adds time of entry to list
	      if (numberOfFreeSeats > 0) {  //if there are any free seats
	        System.out.println("Customer " + this.iD + " just sat down.");	//e. shows customer in waiting room
	        	custHairCuts.add(this.iD); //if customer sits down add id to list of those getting hair cut
	        numberOfFreeSeats--;  //sitting down on a chair
	        customers.release();  //notify the barber that there is a customer
	        accessSeats.release();  // don't need to lock the chairs anymore  
	        try {
		barber.acquire();  // now it's this customers turn but we have to wait if the barber is busy
	        notCut = false;  // this customer will now leave after the procedure
	        this.get_haircut();  //cutting...
	        } catch (InterruptedException ex) {}
	      }   
	      else  {  // there are no free seats
	        System.out.println("There are no free seats. Customer " + this.iD + " has left the barbershop."); //e. shows customer leaving because no empty chairs
	        accessSeats.release();  //release the lock on the seats
	        notCut=false; // the customer will leave since there are no spots in the queue left.
	      }
	     }
	      catch (InterruptedException ex) {}
	    }
	  }

	  /* this method will simulate getting a hair-cut */
	  public void get_haircut(){
		 try {
			listAccess.acquire();	//semaphore for list access
			cuttingStartTimes.add(System.currentTimeMillis());	//g. add start time of to list
		    System.out.println("Customer " + this.iD + " is getting their hair cut"); //e. shows customer getting hair cut
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			listAccess.release();
		}
		  
	    try {
	    sleep(5850);	//f. increased hair cut length to achieve 10 customers getting hair cuts
	    } catch (InterruptedException ex) {}
	    
	    try {
			listAccess.acquire();	//semaphore for list access
			cuttingEndTimes.add(System.currentTimeMillis());	//g. add start time of to list
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			listAccess.release();
		}
	  }
	}

	/* THE BARBER THREAD */
	class Barber extends Thread {
		  
	  public Barber() {}
	  
	  public void run() {
	    while(true) {  // runs in an infinite loop
	      try {
	      customers.acquire(); // tries to acquire a customer - if none is available he goes to sleep
	      accessSeats.acquire(); // at this time he has been awaken -> want to modify the number of available seats
	        numberOfFreeSeats++; // one chair gets free
	      barber.release();  // the barber is ready to cut
	      accessSeats.release(); // we don't need the lock on the chairs anymore
	      this.cutHair();  //cutting...
	    } catch (InterruptedException ex) {}
	    }
	  }

	    /* this method will simulate cutting hair */
	   
	  public void cutHair(){
		System.out.println("The barber is cutting hair");
	    try {
	      sleep(5800);	//f. Increased cutHair length the same amount as get_haircut
	    } catch (InterruptedException ex){ }
	  }
	}       
	  
	  /* main method */
	  public static void main(String args[]) {
	    //a. implement 1 barber
	    SleepingBarber1 barberShop = new SleepingBarber1();  //Creates a new barbershop
	    barberShop.start();  // Let the simulation begin
	  }

	  public void run(){   
	   Barber giovanni = new Barber();  //Giovanni is the best barber ever 
	   giovanni.start();  //Ready for another day of work
	   List<Customer> customerList = new ArrayList<Customer>(); //array list to keep track of all customers

	   /* This method will create new customers for a while */
	   //b. implement  for 20 customers
	   for (int i=1; i<21; i++) {
	     Customer aCustomer = new Customer(i);
	     customerList.add(aCustomer);
	     aCustomer.start();
	     try {
	       sleep(1400);	//f. shortened arrival interval to achieve 10 customer haircuts
	     } catch(InterruptedException ex) {};
	   }
	   
	   //g. wait for all customers to finish hair cuts before printing times
	   for(Customer customer : customerList) {
		   try {
			customer.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	   }
	   
	   //Results:
	   //g. show when customers entered
	   System.out.println("\nEntry times of customers:");
	   for(int i=0; i<enterTimes.size(); i++) {
		   System.out.println("customer " + (i+1) + " entered at " + enterTimes.get(i));
	   }
	   
	   //g. show when they got their hair cuts
	   System.out.println("\nHair cut times of customers:");
	   for(int i=0; i<cuttingStartTimes.size(); i++) {
		   System.out.println("customer " + custHairCuts.get(i) + " started hair cut at " + cuttingStartTimes.get(i) + " and ended at " + cuttingEndTimes.get(i));
	   }
	   
	   //g. calculate intervals of hair cuts
	   System.out.println("\nHair cut time intervals:");
	   for(int i=0; i<cuttingStartTimes.size(); i++) {
		   System.out.println("customer " + custHairCuts.get(i) + " hair cut length: " + (cuttingEndTimes.get(i) - cuttingStartTimes.get(i)));
	   }
	   
	   //g. calculate arrival intervals
	   System.out.println("\nEnter time intervals:");
	   for(int i=0; i<enterTimes.size()-1; i++) {
		   System.out.println("Interval " + (i+1) + ": " + (enterTimes.get(i+1) - enterTimes.get(i)));
	   }
	   
	   //totals
	   System.out.println("\nTotal time customers are entering: " + (enterTimes.get(enterTimes.size()-1) - enterTimes.get(0)));
	   System.out.println("Total time hair is being cut: " + (cuttingEndTimes.get(cuttingEndTimes.size()-1) - cuttingStartTimes.get(0)));
	   System.out.println("Total customers who recieved hair cuts: " + custHairCuts.size());
	  } 
	}