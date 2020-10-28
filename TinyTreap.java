import java.util.Random;

public class TinyTreap {

	static final Random random = new Random(5);
	
	// lower priority on top, all methods return the new treap root
	// To add new seg-tree supported properties, edit recalc()
	// To add lazyprop values, edit recalc() and prop()

	// If you only add by merging, skip add() and rebalance()
	// If you don't need lazyprop, skip prop() and rangeAdd()
	
	static class Treap {
		int data, priority;
		Treap[] kids = new Treap[2];
		
		int subtreeSize, sum, toPropogate;
		
		public Treap(int data) {
			this.data = data;
			priority = random.nextInt();
			recalculate(this);
		}
		
		//returns lefthalf, rightHalf
		//numberInLeft is size of left treap, aka index of first thing in right treap
		
		static Treap[] split(Treap me, int numberInLeft) {
			if (me == null) return new Treap[] {null, null};
			
			propogate(me);
			
			if (size(me.kids[0]) >= numberInLeft) {
				Treap[] leftResult = split(me.kids[0], numberInLeft);
				me.kids[0] = leftResult[1];
				recalculate(me);
				return new Treap[] {leftResult[0], me};
			}
			else {
				numberInLeft = numberInLeft - size(me.kids[0]) - 1;
				Treap[] rightResult = split(me.kids[1], numberInLeft);
				me.kids[1] = rightResult[0];
				recalculate(me);
				return new Treap[] {me, rightResult[1]};
			}
		}
		
		static Treap merge(Treap left, Treap right) {
			if (left == null) return right;
			if (right == null) return left;
			
			propogate(left); 
			propogate(right);
			
			if (left.priority < right.priority) {
				left.kids[1] = merge(left.kids[1], right);
				recalculate(left);
				return left;
			}
			else {
				right.kids[0] = merge(left, right.kids[0]);
				recalculate(right);
				return right;
			}
		}
		
		static void recalculate(Treap me) {
			if (me == null) return;
			
			me.subtreeSize = 1;
			me.sum = me.data + me.toPropogate * size(me);
			
			for (Treap t : me.kids) if (t!=null) me.subtreeSize += t.subtreeSize;
			for (Treap t : me.kids) if (t!=null) me.sum += t.sum + t.toPropogate * size(t);
		}
		
		static void propogate(Treap me) {
			if (me == null) return;
			if (me.toPropogate == 0) return;
			
			for (Treap t : me.kids) 
				if (t!=null) 
					t.toPropogate += me.toPropogate;
			
			me.data += me.toPropogate;
			me.toPropogate = 0;
			
			recalculate(me);
		}
		
		static Treap rangeAdd(Treap t, int l, int r, int toAdd) {
			Treap[] a = split(t, l);
			Treap[] b = split(a[1], r - l + 1);
			b[0].toPropogate += toAdd;
			return merge(a[0], merge(b[0], b[1]));
		}
		
		static int size(Treap t) {
			return t == null ? 0 : t.subtreeSize;
		}
	}

}