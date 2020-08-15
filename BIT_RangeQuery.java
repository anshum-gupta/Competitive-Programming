// this one is for point query and range update

public class BIT_RangeQuery {
	int[]bit;
	int n;
	public BIT_RangeQuery(int[]a, int n) {
		this.n = n;
		bit = new int[n+10];
		init_tree(a);
	}
	public void init_tree(int[]a) {
		for(int i=0; i<n; i++) {
			add(i, a[i]);
		}
	}
	public int sumQuery(int ind) {
		int sum = 0;
		for(; ind >= 0; ind -= (ind & (-ind))) {
			sum += bit[ind];
		}
		return sum;
	}
	public void add(int ind, int val) {
		for(; ind < n; ind += (ind & (-ind))) {
			bit[ind] += val;
		}
	}
	public int sumInRange(int l, int r) {
		return sumQuery(r) - sumQuery(l - 1);
	}
	
}
