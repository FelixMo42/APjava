package library.ai;

public class Main {
	public static final int[] brainSize = {10,10};
	
	public static final int[] Q = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	public static final int[] A = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	
	public static void main(String[] args) {
		Brain brain = new Brain( brainSize );
		
		int l = 100; // number of loops
		int s = 2; // number of digits to use
		
		for (int i = 1; i < s * l - 1 + s; i++) {
			System.out.println( Q[i % s] + ") got " + brain.value( toArray(Q[i % s]) ) + " | expected " + A[i % s] );
			brain.error( toArray(A[i % s]) );
			if (i % s == 0) {
				brain.correct();
				System.out.println();
			}
		}
	}
	
	public static float[] toArray(int i) {
		float[] r = new float[ brainSize[0] ];
		r[i] = 1;
		return r;
	}
}