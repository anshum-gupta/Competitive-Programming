import java.util.Arrays;
// What this does basically
// Try to store the values as change in values rather than values itself
// what I mean by that is when I make bit I am updating from index i to i and then creating it
public class BIT_PointQueryRangeUpdate {
	int[]bit;
	int n;
	public BIT_PointQueryRangeUpdate(int[]a, int n) {
		this.n = n;
		this.bit = new int[n+10];
	}
	
	public void init_tree(int[]a) {
		Arrays.fill(this.bit, 0);
		for(int i=n-1; i>=0; i--) {
			this.updateRange(i, i, a[i]);
		}
	}
	public int[]getPrefixSums(int[]a){
		int[]prefix = new int[a.length];
		for(int i=0; i<a.length; i++) {
			prefix[i] = a[i] + (i > 0 ? prefix[i-1] : 0);
		}
		return prefix;
	}
	public void updateBIT(int ind, int val) {
		ind++;
		for(; ind <= n; ind += ind & (-ind)) {
			this.bit[ind] += val;
		}
	}
	// returns sum from a[0...ind]
	public int getElement(int ind) {
		ind++;
		int sum = 0;
		for(; ind > 0; ind -= ind & (-ind)) {
			sum += this.bit[ind];
		}
		return sum;
	}
	// adds val from index left to index right a[left....right] += val
	public void updateRange(int left, int right, int val) {
		updateBIT(left, val);
		updateBIT(right + 1, -val);
	}
}
