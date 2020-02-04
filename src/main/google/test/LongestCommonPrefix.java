package main.google.test;

public class LongestCommonPrefix {

	public static void main(String[] args) {
		String[] s= {"flower","flow","flight"};

		//vertical scanning approach
		String out=verticalScanning(s);
		System.out.println(out);
	}

	/*
	 * S is sum of all characters in String
	 * Time Complexity : O(S)
	 * Space Complexity : O(1)
	 * trace
	 * s.length3
s[j].length()4 s[j].charAt(i)f
s[j].length()6 s[j].charAt(i)f
s.length3
s[j].length()4 s[j].charAt(i)l
s[j].length()6 s[j].charAt(i)l
s.length3
s[j].length()4 s[j].charAt(i)o
s[j].length()6 s[j].charAt(i)i
fl
	 */
	private static String verticalScanning(String[] s) {
		if(s == null || s.length ==0) return "";
		for(int i=0; i<s[0].length();i++) {
			char c = s[0].charAt(i);
			System.out.println("s.length"+s.length);
			for(int j=1; j<s.length; j++) {
				System.out.println("s[j].length()"+s[j].length() +" s[j].charAt(i)"+ s[j].charAt(i));
				if(i == s[j].length() || s[j].charAt(i) != c)
					return s[0].substring(0,i);
			}
		}
		return s[0];
	}

}
