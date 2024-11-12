import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Job {
	//job attributes
	private int start, finish, benefit; //start time, finish time, benefit of job
	private String name; //name of job
	
	static List<String> optJobList = new ArrayList<String>();
	
	//Job object
	public Job(String name, int start, int finish, int benefit) {
		this.name = name;
		this.start = start;
		this.finish = finish;
		this.benefit = benefit;
	}
	
	//loop through job array. compare finish time of preveious job to start time of next job
	//jobARR[]: array of jobs sorted by finish time
	//i: index of the current job (at index i, i starts at end of list) you are comparing to the previous job (at index j, j starts at i-1)
	static int nonConflict(Job jobArr[], int i) {
		//finish before next is started
		for (int j=i-1; j>=0; j--) {
			//System.out.print("job iteration " + i + " "); //debug to keep track of order
			
			if (jobArr[j].finish <= jobArr[i].start) {
				System.out.println(jobArr[j] + "non conflict" + " with " + jobArr[i]);
				
				//optJobList.set(i, jobArr[i].name);
				
				return j;
			}
			else {
				System.out.println(jobArr[j] + "conflicts" + " with " + jobArr[i] + "\n");
			}
		}
		//if no compatible jobs, return -1
		return -1;
	}
	
	static int findMaxBen(Job jobArr[], int n) {
		int[] benArr = new int[n]; //array to store benefit of jobs
		benArr[0] = jobArr[0].benefit;
		
		for (int i=1; i<n; i++) {			
			int curBen = jobArr[i].benefit;
			int nonConflictJob = nonConflict(jobArr, i);
			
			if (nonConflictJob != -1) {
				System.out.print(curBen + " + " + benArr[nonConflictJob] + " = ");
				curBen = curBen + benArr[nonConflictJob];
				System.out.print(curBen + ", ");
			}
			
			benArr[i] = Math.max(curBen, benArr[i-1]);
			if (benArr[i] == curBen) {
				System.out.println("increase, new max benefit value\n");
			}
			else {
				System.out.println("decrease, no update\n");
			}
		}
		
		int result = benArr[n-1];
		
		System.out.println();
		return result;
	}
	
	static int sortMaxBen (Job jobArr[], int n) {
		//sort job array by finish times
		Arrays.sort(jobArr, new Comparator<Job>() {
			public int compare(Job j1, Job j2)
			{
				return j1.finish - j2.finish;
			}
		});
		
		System.out.print("Sorted job array: ");
		for (int i=0; i<jobArr.length; i++) {
			System.out.print(jobArr[i] + " ");
		}
		System.out.println("\n");
		
		return findMaxBen(jobArr, n);
	}
	
	//getters, setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}

	public int getFinish() {
		return finish;
	}
	public void setFinish(int finish) {
		this.finish = finish;
	}

	public int getBenefit() {
		return benefit;
	}
	public void setBenefit(int benefit) {
		this.benefit = benefit;
	}
	
	//toString
	@Override
	public String toString() {
		return name + ":(" + start + ", " + finish +", " +  benefit + "), ";
	}
	
}
