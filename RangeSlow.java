
public class RangeSlow implements QueryInterface {
	
	int[]arr;
	int n;
	public RangeSlow(int[]arr) {
		this.arr = arr;
		this.n = arr.length;
	}
	
	@Override
	public void increment(int i, int j, int val) {
		for(int index = i; index <= j; index++) {
			arr[index] += val;
		}
	}

	@Override
	public int sum(int i, int j) {
		int result = 0;
		for(int index = i; index <= j; index++) {
			result += arr[index];
		}
		return result;
	}

}
