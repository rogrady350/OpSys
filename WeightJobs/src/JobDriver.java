
public class JobDriver {

	public static void main(String[] args) {
		final int m = 8;
		
		String a = "a";
		String b = "b";
		String c = "c";
		String d = "d";
		String e = "e";
		String f = "f";
		String g = "g";
		String h = "h";
		
		Job jobArr[] = new Job[m];
		
		jobArr[0] = new Job(a, 1, 2, 5);
		jobArr[1] = new Job(b, 1, 3, 6);
		jobArr[2] = new Job(c, 2, 4, 7);
		jobArr[3] = new Job(d, 3, 5, 2);
		jobArr[4] = new Job(e, 1, 6, 7);
		jobArr[5] = new Job(f, 4, 7, 5);
		jobArr[6] = new Job(g, 6, 8, 7);
		jobArr[7] = new Job(h, 7, 8, 4);
	
		int n = jobArr.length;
		
		System.out.println("The optimal benefit is " + Job.sortMaxBen(jobArr, n));
		
		System.out.print("Optimum Job List: ");
		for (int i=0; i<Job.optJobList.size(); i++) {
			System.out.print(Job.optJobList.get(i) + " ");
		}

	}

}
