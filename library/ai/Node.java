package library.ai;

import java.util.ArrayList;

public class Node {
	public final ArrayList<Node> into;
	public final float[] weights;
	
	private Brain brain;
	private float value;
	
	public Node(ArrayList<Node> previous , Brain parent) {
		into = previous;
		weights = new float[into.size()];
		for (int i = 0; i < into.size(); i++) {
			weights[i] = library.math.frandom(0, 1);
		}
		brain = parent;
	}
	
	public void calcValue() {
		value = 0;
		
		for (int i = 0; i < into.size(); i++) {
			value += into.get(i).getValue() * weights[i];
		}
		
		value = brain.function.call(value);
	}
	
	public float getValue() {
		return value;
	}
	
	public void input(float n) {
		value = n;
	}
}