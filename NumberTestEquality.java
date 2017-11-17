public class NumberTestEquality {
	public static void main(String[] args) {
		final double COST = 3;
		final double EPSILON = 1E-12; //The maximum acceptable variable sway
		
		double COST_sqroot = Math.sqrt(COST); //get the root of the number
		double COST2 = Math.pow(COST_sqroot,2); //get the root square (theoretical the original)

		if ( Math.abs( COST2 - COST ) < EPSILON) { //see if the difference is smaller than epsilon
			System.out.println("Almost the same: " + COST2); //print out the new cost
		} else {
			System.out.println("Different by: " + Math.abs(COST - COST2)); //print out how far apart they are
		}
	}
}