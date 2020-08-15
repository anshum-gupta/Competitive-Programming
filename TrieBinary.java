

public class TrieBinary {
	TrieBinary left, right;
	int count;
	public TrieBinary() {}
	public void insert(int value) {
		TrieBinary cur = this;
		for(int i=31; i>=0; i--) {
			int x = ((1<<i) & value);
			if(x > 0) {
				if(cur.right == null) {
					cur.right = new TrieBinary();
				}
				cur = cur.right;
			}else {
				if(cur.left == null) {
					cur.left = new TrieBinary();
				}
				cur = cur.left;
			}
			cur.count++;
		}
	}
}
