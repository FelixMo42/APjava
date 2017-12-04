package library.ai;

import java.util.ArrayList;

public class Node {
	public static float evolution = 0.25f;
	
	public final float[] weights;
	
	public float expected;
	
	public final ArrayList<Node> into;
	public final int positionSize;
	
	public float[] correction;
	public Brain brain;
	public float value;
	
	public Node(int position , Brain parent) {
		positionSize = parent.nodes.get(position).size();
		if (position == 0) {
			into = new ArrayList<Node>();
		} else {
			into = parent.nodes.get(position - 1);
		}
		correction = new float[into.size()];
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
	
	public void error() {
		for (int i = 0; i < into.size(); i++) {
			correction[i] += (expected - (into.get(i).getValue() * weights[i])) * into.get(i).getValue();
			into.get(i).expected += expected * weights[i] / positionSize;
		}
	}
	
	public void correct() {
		for (int i = 0; i < into.size(); i++) {
			weights[i] += correction[i] / brain.errorCount * evolution;
		}
		for (int i = 0; i < into.size(); i++) {
			correction[i] = 0;
		}
	}
}