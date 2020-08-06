
public class SegmentTree implements QueryInterface {
	
	int left, right;
	SegmentTree lChild, rChild;
	int sum;
	
	public SegmentTree(int[]arr, int left, int right) {
		this.left = left;
		this.right = right;
		if(left == right) {
			sum = arr[left];
		}
		else {
			int mid = (left + right) >> 1;
			lChild = new SegmentTree(arr, left, mid);
			rChild = new SegmentTree(arr, mid + 1, right);
			recalculate();
		}
	}
	
	public void recalculate() {
		if(left == right)return;
		this.sum = lChild.sum + rChild.sum;
	}
	
	public void pointUpdate(int index, int value) {
		if(left == right) {
			sum = value;
		}else {
			if(index <= lChild.right) {
				lChild.pointUpdate(index, value);
			}else {
				rChild.pointUpdate(index, value);
			}
			recalculate();
		}
	}
	@Override
	public void increment(int i, int j, int val) {
		// TODO Auto-generated method stub

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
