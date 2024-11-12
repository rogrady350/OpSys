
public class LcsDriver {

	public static void main(String[] args) {
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		
		String X = "skullandbones";
		String Y = "lullabybabies";
		int m = X.length();
		int n = Y.length();
		
		System.out.println("X: " + X + ", Length: " + m);
		System.out.println("Y: " + Y + ", Length: " + n + "\n");
		System.out.println("\nLength of LCS is " + lcs.lcs(X, Y, m, n));
	}

}
