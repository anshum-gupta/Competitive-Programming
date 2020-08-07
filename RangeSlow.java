
public class RangeSlow implements QueryInterface {
	
	int[]arr;
	int n;
	public RangeSlow(int[]arr) {
		this.arr = arr;
		this.n = arr.length;
	}
	
	@Override
	public void rangeUpdate(int i, int j, int val) {
		for(int index = i; index <= j; index++) {
			arr[index] += val;
		}
	}
	public void pointUpdate(int index, int value) {
		arr[index] = value;
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
