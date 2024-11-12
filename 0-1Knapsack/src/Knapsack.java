
public class Knapsack {
	//funcion to compare 2 values
	static int max(int a, int b) {return (a > b) ? a : b;}
		/* means teh same thing as this in a more consise form
		if (a>b) {
			return a;
		}
		else {
			return b;
		}*/
	
	/*int W: total weight available in knapsack
	  int n: total number of items in the set (size of set)
	  int wt[]: array to hold weight values
	  int ben[]; array to hold benefit values
	*/
	static int knapsack(int W, int wt[], int ben[], int n) {
		int k; //index of item in set
		int w; //weight value
		int B[][] = new int[n+1][W+1]; //array to store benefit values
		
		for(k=0; k<=n; k++) {
			if(k-1<0) {System.out.println("Building initial set");}
			else {
				System.out.println("Building benefit list for item " + k + ": (" + ben[k-1] + "," + wt[k-1] + ")");
			}
			
			for(w=0; w<=W; w++) {
				//null condition				
				if(k==0 || w==0) {
					B[k][w] = 0;
				}
				//check for update max benefit list with greater value
				else if (wt[k-1] <= w) {
					int kBen = ben[k-1] + B[k-1][w-wt[k-1]];
					int curMaxBen = B[k-1][w];
					B[k][w] = max(kBen, curMaxBen);
					
					if(B[k][w] == kBen) {
						System.out.print(">B[w]: yes, " + ben[k-1] + " + " + B[k-1][w-wt[k-1]] + " = " + kBen + ", ");
					}
					else {
						System.out.print(">B[w]: no, " + ben[k-1] + " + " + B[k-1][w-wt[k-1]] + " = " + kBen + ", ");
					}
				}
				//no check for benefit list update since w-wt[k-1] is less than 0 so no need to check
				else {
					System.out.print("no check, ");
					B[k][w] = B[k-1][w];
				}
				
				System.out.println("B["+k+"]["+w+"] = " + B[k][w]);
			}
			
			System.out.println();
		}
		
		//start value for loop at maximum benefit value
		int knapItemBen = B[n][W];
		System.out.print("weights of items in knapsack: ");
		
		w=W;
		for(k=n; k>0 && knapItemBen > 0; k--) {
			if (knapItemBen == B[k-1][w]) {
				continue;
			}
			else {
				System.out.print(wt[k-1] + " ");
				
				knapItemBen = knapItemBen - ben[k-1];
				w = w - wt[k-1];
			}
		}
		
		System.out.println();
		
		return B[n][W];
	}

}
