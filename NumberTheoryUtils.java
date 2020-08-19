import java.util.*;
public class NumberTheoryUtils {
	private long MOD = (long)(1e9 + 7);
	long[]fact, inverseFact;
	boolean factsMade = false;
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
	public void setMod(long MOD) {
		this.MOD = MOD;
	}
	public long powerModulo(long base, long exponent) {
		if(exponent == 0)return 1L;
		long tempValue = powerModulo(base, exponent / 2L);
		long res = (tempValue * tempValue) % MOD;
		if(exponent % 2 != 0)res = (res * base) % MOD;
		return res;
	}
	public long inverseModulo(long value) {
		return this.powerModulo(value, MOD - 2L);
	}
	public long nCr(int n, int r) {
		if(n < r || n < 0 || r < 0)return 0L;
		return (((fact[n] * inverseFact[n-r]) % MOD) * inverseFact[r]) % MOD;
	}
	
	public long[]getFactorialsModulo(int maxLength){
		this.fact = new long[maxLength];
		fact[0] = 1L;
		for(int i=1; i<maxLength; i++) {
			fact[i] = (fact[i-1] * i) % MOD;
		}
		this.factsMade = true;
		return fact;
	}
	public long[]getInverseFactorialsModulo(int maxLength){
		this.inverseFact = new long[maxLength];
		if(!this.factsMade) {
			this.getFactorialsModulo(maxLength);
		}
		for(int i=0; i<maxLength; i++) {
			inverseFact[i] = this.inverseModulo(fact[i]);
		}
		return inverseFact;
	}
	public TreeMap<Integer, Integer> getPrimeFactors(long n){
		TreeMap<Integer, Integer> answer = new TreeMap<Integer, Integer>();
		int twoCount = 0;
		while(n % 2 == 0) {
			n /= 2;
			twoCount++;
		}
		if(twoCount > 0)answer.put(2, twoCount);
		for(int i=3; 1L*i*i<=n; i+=2) {
			int iCount = 0;
			while(n % i == 0) {
				n /= i;
				iCount++;
			}
			if(iCount > 0) {
				answer.put(i, iCount);
			}
		}
		return answer;
	}
	public long eulerTotientFunctionForSingleValue(long n) {
		// Complexity O(sqrt(n))
		long result = n;
		for(int i=2; 1L*i*i <= n; i++) {
			while(n % i == 0) {
				n /= i;
				result -= result / i;
			}
		}
		if(n > 1) {
			result -= result / n;
		}
		return result;
	}
	public int[]eulerTotientFunctionForRange(int n){
		int[]phi = new int[n+1];
		for(int i=1; i<=n; i++) {
			phi[i] = i;
		}
		for(int i=2; i<=n; i++) {
			if(phi[i] == i) {
				for(int j=i; j<=n; j+=i) {
					phi[j] -= phi[j] / i;
				}
			}
		}
		return phi;
	}

}
