import java.io.*;
import java.util.*;

public class Solution {
static class NumberTheoryUtils {
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
			if(n % i == 0) {
				while(n % i == 0) {
					n /= i;
				}
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

public static void main(String[] args){
   new Thread(null, null, "Anshum Gupta", 99999999) {
        public void run() {
            try {
                solve();
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }.start();
}
static final long mxx = (long)(1e18+5);
static final int mxN = (int)(1e6);
static final int mxV = (int)(1e6+2), log = 18;
static long mod = (long)(1e9+7); //998244353;//̇
static long[]fact, inv_fact;
static final int INF = (int)1e9;
static boolean[][]vis;
static ArrayList<ArrayList<Integer>> adj;
static int n, m, k, q, x, y;

public static void solve() throws Exception {
	   // solve the problem here
		s = new MyScanner();
   		out = new PrintWriter(new BufferedOutputStream(System.out), true);
//	   		out = new PrintWriter("output.txt");
        int tc = s.nextInt();
        NumberTheoryUtils ntu = new NumberTheoryUtils();
        while(tc-->0){
        	long a = s.nextLong(), m = s.nextLong();
        	long g = ntu.gcd(a, m);
        	long m1 = m / g;
        	long answer = ntu.eulerTotientFunctionForSingleValue(m1);
        	out.println(answer);
        } 
           
        out.flush();
        out.close();
}

public static PrintWriter out;
public static MyScanner s;
static class MyScanner {

    BufferedReader br;
    StringTokenizer st;

    public MyScanner() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    public MyScanner(String fileName) {
    	try {
			br = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() { return Integer.parseInt(next()); }
    long nextLong() { return Long.parseLong(next()); }
    double nextDouble() { return Double.parseDouble(next()); }
    int[] nextIntArray(int n){
    	int[]a = new int[n];
    	for(int i=0; i<n; i++) {
    		a[i] = this.nextInt();
    	}
    	return a;
    }
    long[] nextlongArray(int n) {
    	long[]a = new long[n];
    	for(int i=0; i<n; i++) {
    		a[i] = this.nextLong();
    	}
    	return a;
    }
    Integer[] nextIntegerArray(int n){
    	Integer[]a = new Integer[n];
    	for(int i=0; i<n; i++) {
    		a[i] = this.nextInt();
    	}
    	return a;
    }
    Long[] nextLongArray(int n) {
    	Long[]a = new Long[n];
    	for(int i=0; i<n; i++) {
    		a[i] = this.nextLong();
    	}
    	return a;
    }
    char[][] next2DCharArray(int n, int m){
    	char[][]arr = new char[n][m];
    	for(int i=0; i<n; i++) {
    		arr[i] = this.next().toCharArray();
    	}
    	return arr;
    }
    ArrayList<ArrayList<Integer>> readUndirectedUnweightedGraph(int n, int m) {
    	ArrayList<ArrayList<Integer>>adj = new ArrayList<ArrayList<Integer>>();
    	for(int i=0; i<=n; i++)adj.add(new ArrayList<Integer>());
    	for(int i=0; i<m; i++) {
    		int u = s.nextInt();
    		int v = s.nextInt();
    		adj.get(u).add(v);
    		adj.get(v).add(u);
    	}
    	return adj;
    }
    ArrayList<ArrayList<Integer>> readDirectedUnweightedGraph(int n, int m) {
    	ArrayList<ArrayList<Integer>>adj = new ArrayList<ArrayList<Integer>>();
    	for(int i=0; i<=n; i++)adj.add(new ArrayList<Integer>());
    	for(int i=0; i<m; i++) {
    		int u = s.nextInt();
    		int v = s.nextInt();
    		adj.get(u).add(v);
    	}
    	return adj;
    }
    String nextLine(){
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}

 
}
