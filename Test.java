public class Test {
	public static void main(String[] args) {
		int[] scores = {5, 8, 15};
		int t = 0;
		for (int s : scores) {
			t += s;
		}
		System.out.println("Sum: " + t);
		System.out.println("Average: " + (t / scores.length));
	}
}

/**

	if (str.length() <= 1) {
		return 0;
	}
	if (str.substring(0,2).equals("11") ) {
	    return 1 + count11( str.substring(2) );
	}
	return 0 + count11( str.substring(1) );



	if (str.length() <= 1) {
		return str;
	}
	if (str.substring(0,2).equals("11") ) {
	    return charAt(0) + count11( str.substring(2) );
	}
	return charAt(0) + count11( str.substring(1) );
	
**/