
public class KnapsackDriver {

	public static void main(String[] args) {
		int benefit[] = {12, 10, 8, 11, 14, 7, 9}; //array of benefit values
		int weight[]  = {4,  6,  5, 7,  3,  1, 6}; //array of weight values
		int W = 18; //max weight knapsack can hold
		int n = benefit.length; //size of weight/benefit value set
		
		System.out.println("Max benefit = " + Knapsack.knapsack(W, weight, benefit, n));
	}

}
