import java.util.Arrays;

// this one is for point query and range update

public class BIT_RangeQueryPointUpdate {
	int[]bit;
	int n;
	public BIT_RangeQueryPointUpdate(int[]a, int n) {
		this.n = n;
		this.bit = new int[n+10];
	}
	public BIT_RangeQueryPointUpdate(int n) {
		this.n = n;
		this.bit = new int[n+10];
	}
	public void init_tree(int[]a) {
		Arrays.fill(this.bit, 0);
		for(int i = 0; i < n; i++) {
			this.add(i, a[i]);
		}
	}
	public int sumQuery(int ind) {
		int sum = 0;
		ind++;
		for(; ind > 0; ind -= ind & (-ind)) {
			sum += bit[ind];
		}
		return sum;
	}
	public void add(int ind, int val) {
		ind++;
		for(; ind <= n; ind += ind & (-ind)) {
			bit[ind] += val;
		}
	}
	public int sumInRange(int l, int r) {
		return sumQuery(r) - sumQuery(l - 1);
	}
	
}
