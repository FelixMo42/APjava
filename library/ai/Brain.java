package library.ai;

import java.util.ArrayList;

public class Brain {
	interface Func {
		float call(float i);
	}
	
	public final ArrayList< ArrayList<Node> > nodes;
	public final ArrayList<Node> first;
	public final ArrayList<Node> last;
	public Func function;
	
	public Brain(int[] nodeCount) {
		nodes = new ArrayList< ArrayList<Node> >();
		
		for (int p = 0; p < nodeCount.length; p++) {
			nodes.add( new ArrayList<Node>() );
			
			for (int i = 0; i < nodeCount[p]; i++) {
				if (p == 0) {
					nodes.get(p).add( new Node( new ArrayList<Node>() , this ) );
				} else {
					nodes.get(p).add( new Node( nodes.get(p - 1) , this ) );
				}
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
		
		for (ArrayList<Node> nl : nodes) {
			for (Node n : nl) {
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
}