
public class LongestCommonSubsequence {
	//utility function to compare and get max of 2 values
	int max(int a, int b) {return (a > b) ? a : b;}
	
	/*
	 String X: first String of letters
	 String Y: second string of letters
	 int n: length of string x (number of letters)
	 int m: length of String y (number of letters) 
	*/
	int lcs(String X, String Y, int m, int n) {
		int L[][] = new int[m+1][n+1];	//length array
		
		int i; //index of letter in string X
		int j; //index of letter in string Y
		for(i=0; i<=m; i++) {
			for(j=0; j<=n; j++) {
				if(i==0 || j==0) {
					L[i][j] = 0;
				}
				
				else if (X.charAt(i-1) == Y.charAt(j-1)) {
					L[i][j] = L[i-1][j-1] + 1;
				}
				
				else {
					L[i][j] = max(L[i-1][j], L[i][j-1]);
				}
				
				//debug to build table
				System.out.print(L[i][j] + " ");
			}
			System.out.println();
		}
		
		return L[m][n];
	}

}
