package library.ai;

public class Main {
	public static final int[] brainSize = {20,20};
	
	public static int l = 100; // number of loops
	public static int s = 17; // number of digits to use
	
	//public static int[] Q = {0,1,2,3,4,5,6,7,8,9};
	//public static int[] A = {0,1,2,3,4,5,6,7,8,9};
	//public static void init() {}
	
	public static String[] Q;
	public static int[] A;
			
	
	public static void init() {
		Q = new String[100];
		A = new int[100];
		int i = 0;
		for (int f = 0; f < 10; f++) {
			for (int s = 0; s < 10; s++) {
				Q[i] = "" + f + s;
				A[i] = f + s;
				i++;
			}
		}
	}
	
    // ------------------------------------------------------------------------------------------------- \\
 // --------------------------------- // do not change these variables \\ --------------------------------- \\
    // ------------------------------------------------------------------------------------------------- \\
	
	public static int[] I;
	public static Brain brain = new Brain( brainSize );
	
	public static void main(String[] args) {
		init();
		//populate Index array I;
		I = new int[ (s / Q.length + s % Q.length / (Q.length/2) + 1) * Q.length ];
		for (int i = 0; i < I.length; i++) {
			I[i] = i % Q.length;
		}
		//main loop
		for (int i = 1; i < l * s + 1; i++) {
			System.out.println( Q[ I[i % s] ] + ") got " + brain.value( toArray(Q[ I[i % s] ]) ) + " | expected " + A[ I[i % s] ] );
			brain.error( toArray(A[ I[i % s] ]) );
			if (i % s == 0) {
				shuffleArray(I);
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
	
	public static float[] toArray(String s) {
		float[] r = new float[ brainSize[0] ];
		int i = 0;
		for (int c : s.chars().toArray()) {
			r[i] = (c - 48) / 10f;
			i++;
		}
		return r;
	}
	
	static void shuffleArray(int[] ar) {
		for (int i = ar.length - 1; i > 0; i--) {
			int index = library.math.random(i);
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}
}