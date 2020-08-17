import java.util.Arrays;

public class NumberTheoryUtils {
	public long gcd(long a, long b) {
		if(b == 0)return a;
		return gcd(b, a%b);
	}
	public int gcd(int a, int b) {
		if(b == 0)return a;
		return gcd(b, a%b);
	}
	public long lcd(long a, long b) {
		return a * b / gcd(a, b);
	}
	public int lcm(int a, int b) {
		return (int) 1L * a * b / gcd(a, b);
	}
	public int[] getLPFArray(int maxLength) {
		int[]lpf = new int[maxLength+5];
		for(int i=2; i<=maxLength; i++) {
			if(lpf[i] == 0) {
				for(int j=i; j<=maxLength; j+=i) {
					if(lpf[j] == 0) {
						lpf[j] = i;
					}
				}
			}
		}
		return lpf;
	}
	public boolean[]getSieve(int maxLength){
		boolean[]isPrime = new boolean[maxLength+5];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for(int i=2; i<=maxLength; i++) {
			if(isPrime[i]) {
				for(int j=2*i; j<=maxLength; j+=i) {
					if(isPrime[j]) {
						isPrime[j] = false;
					}
				}
			}
		}
		return isPrime;
	}
}
