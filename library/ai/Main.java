package library.ai;

public class Main {
	public static final int[] brainSize = {10,10};
	
	public static final int[] Q = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	public static final int[] A = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	
	public static void main(String[] args) {
		Brain brain = new Brain( brainSize );
		
		System.out.println( Q[0] + ") got " + brain.value( toArray(Q[0]) ) + " | expected " + A[0] );
	}
	
	public static float[] toArray(int i) {
		float[] r = new float[ brainSize[0] ];
		r[i] = 1;
		return r;
	}
}