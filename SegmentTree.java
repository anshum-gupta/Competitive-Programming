
public class SegmentTree implements QueryInterface {
	
	int left, right;
	// lChild => tree[2*tn] , rChild => tree[2*tn+1]
	SegmentTree lChild, rChild; 
	int sum;
	int minimum;
	int lazyForSum = 0;
	int lazyForMin = 0;
	final int INF = (int)1e9;
	public SegmentTree(int[]arr, int left, int right) {
		this.left = left;
		this.right = right;
		if(left == right) { // LEAF NODE
			this.sum = arr[left];
			this.minimum = arr[left];
		}
		else {
			int mid = (left + right) >> 1;
			lChild = new SegmentTree(arr, left, mid);
			rChild = new SegmentTree(arr, mid + 1, right);
			recalculate();
		}
	}
	
	public void recalculate() { // BASICALLY DOING tree[tn] = tree[2*tn] + tree[2*tn]
		if(left == right)return;
		this.sum = lChild.sum + rChild.sum;
		this.minimum = Math.min(lChild.minimum, rChild.minimum);
	}
	
	public void pointUpdate(int index, int value) { // point update of type SET TO
		if(left == right) {
			this.sum = value;
			this.minimum = value;
		}else {
			if(index <= lChild.right) {
				lChild.pointUpdate(index, value);
			}else {
				rChild.pointUpdate(index, value);
			}
			recalculate();
		}
	}
	
	public void updateLazy() {
		if(this.lazyForSum != 0) {
			this.sum += (this.right - this.left + 1) * lazyForSum;
			if(left != right) {
				lChild.lazyForSum += this.lazyForSum;
				rChild.lazyForSum += this.lazyForSum;
			}
			this.lazyForSum = 0;
		}
		if(this.lazyForMin != INF) {
			this.minimum = Math.min(this.minimum, this.lazyForMin);
			if(left != right) {
				lChild.lazyForMin = Math.min(lChild.lazyForMin, this.lazyForMin);
				rChild.lazyForMin = Math.min(rChild.lazyForMin, this.lazyForMin);
			}
			this.lazyForMin = INF;
		}
	}
	
	@Override
	public void rangeUpdate(int ql, int qr, int val) { // range update INCREMENT BY VALUE
		updateLazy();
		if(qr < this.left || ql > this.right) { // NO OVERLAP
			return;
		}
		if(ql <= this.left && qr >= this.right) { // COMPLETE OVERLAP
			this.sum += val * (this.right - this.left + 1);
			this.minimum += val;
			if(this.left != this.right) {
				lChild.lazyForSum += val;
				rChild.lazyForSum += val;
				lChild.lazyForMin += val;
				rChild.lazyForMin += val;
			}
			this.lazyForMin = INF;
			this.lazyForSum = 0;
			return;
		}
		lChild.rangeUpdate(ql, qr, val);
		rChild.rangeUpdate(ql, qr, val);
		recalculate();
	}

	@Override
	public int sum(int ql, int qr) {
		if(ql <= this.left && qr >= this.right) { // COMPLETE OVERLAP
			return this.sum;
		}
		if(qr < this.left || ql > this.right) { // NO OVERLAP
			return 0;
		}
		return lChild.sum(ql, qr) + rChild.sum(ql, qr);
	}
	
}
