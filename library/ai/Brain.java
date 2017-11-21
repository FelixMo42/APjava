package library.ai;

import java.util.ArrayList;

interface Func {
	float call(float i);
}

public class Brain {
	public final ArrayList< ArrayList<Node> > nodes;
	public final ArrayList<Node> first;
	public final ArrayList<Node> last;
	public Func function;
	public int errorCount = 0;
	
	public Brain(int[] nodeCount) {
		nodes = new ArrayList< ArrayList<Node> >();
		
		for (int p = 0; p < nodeCount.length; p++) {
			nodes.add( new ArrayList<Node>() );
			
			for (int i = 0; i < nodeCount[p]; i++) {
				nodes.get(p).add( new Node( p , this ) );
			}
		}
		
		first = nodes.get(0);
		last = nodes.get(nodes.size() - 1);
		
		function = x -> library.math.sigmoid(x);
	}
	
	public int value(float[] inputs) {
		for (int i = 0; i < first.size(); i++) {
			first.get(i).input( inputs[i] );
		}
		
		for (int nl = 1; nl < nodes.size(); nl++) {
			for (Node n : nodes.get(nl)) {
				n.calcValue();
			}
		}
		
		int n = 0;
		float s = 0;
		for (int i = 0; i < last.size(); i++) {
			if ( last.get(i).getValue() > s ) {
				n = i;
				s = last.get(i).getValue();
			}
		}
		return n;
	}

	public void error(float[] expected) {
		errorCount++;
		//reset error
		for (ArrayList<Node> nl : nodes) {
			for (Node n : nl) {
				n.expected = 0;
			}
		}
		//set error
		for (int i = 0; i < last.size(); i++) {
			last.get(i).expected = expected[i];
		}
		for (int i = nodes.size() - 1; i >= 0; i--) {
			for (Node n : nodes.get(i)) {
				n.error();
			}
		}
	}
	
	public void correct() {
		for (int i = nodes.size() - 1; i >= 0; i--) {
			for (Node n : nodes.get(i)) {
				n.correct();
			}
		}
		errorCount = 0;
	}
}