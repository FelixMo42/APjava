package library;

public class math {
	public static int random(int min, int max) {
		return (int)(Math.random() * (max - min + 1)) + min;
	}
	
	public static int random(int max) {
		return random(0,max);
	}
	
	public static float frandom(int min, int max) {
		return random(min * 100, max * 100) / 100f;
	}
	
	public static float sigmoid(float x) {
		//return (float)((double)x);
		return (float) (1/( 1 + Math.pow(Math.E , (-1* (double)x))));
    }
}