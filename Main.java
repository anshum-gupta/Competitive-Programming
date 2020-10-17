import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeMap;
 
public class Main {

static class Shield {
	int durability, defence;
	public Shield(int durability, int defence) {
		this.durability = durability;
		this.defence = defence;
	}
}
	
static class NumberTheoryUtils {
	private long MOD = 998244353;
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
	public void makeFacts(int maxLength) {
		getFactorialsModulo(maxLength);
		getInverseFactorialsModulo(maxLength);
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
	public long addModulo(long a, long b) {
		return (a + b) % MOD;
	}
	public long multiplyModulo(long a, long b) {
		return (a * b) % MOD;
	}
	public long subtractModulo(long a, long b) {
		return (a - b + MOD) % MOD;
	}
	public long divideModulo(long a, long b) {
		return (a * this.inverseModulo(b)) % MOD;
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
 
static final long mxx = (long)(1e18 + 5), MOD = 998244353;
static final int mxN = (int)(1e6), mxV = (int)(1e6), log = 18, INF = (int)1e9;
static boolean[]vis;
static ArrayList<ArrayList<Integer>> adj;
static int n, m, q, k;
static char[]str;
static int[] strengths;
static Shield[] shields;
static NumberTheoryUtils ntu = new NumberTheoryUtils();

public static void solve() throws Exception {
	   // solve the problem here
		s = new MyScanner();
   		out = new PrintWriter(new BufferedOutputStream(System.out), true);
//	   		out = new PrintWriter("output.txt");
        int tc = 1;//s.nextInt();   	
    	for(int i=1; i<=tc; i++) {
//        	out.print("Case #" + i + ": ");
    		testcase();
    	}
          
        out.flush();
        out.close();
}



static void testcase() {
	n = s.nextInt();
	m = s.nextInt();
	strengths = s.nextIntArray(n);
	shields = new Shield[m];
	
	shuffleSort(strengths);
	
	// print expected damage for each shield
	// if we use this shield and fight monsters in random order 
	
	long[] prefix = new long[n];
	for(int i = 0; i < n; i++) {
		prefix[i] = strengths[i] + (i == 0 ? 0 : prefix[i - 1]);
		prefix[i] %= MOD;
	}
	
	for(int i = 0; i < m; i++) {
		shields[i] = new Shield(s.nextInt(), s.nextInt());
		
		int small = findLowerBound(shields[i].defence);
		int large = n - small;
		
//		out.println(small + " " + large);
		long smallStrengthSum = small == 0 ? 0 : prefix[small - 1];
		long largeStrengthSum = (prefix[n - 1] - (small == 0 ? 0 : prefix[small - 1]) + MOD) % MOD;
		
		if(shields[i].durability > large || shields[i].durability == n) out.println("0");
		
		long largeAnswer = largeStrengthSum * (largeStrengthSum - shields[i].durability);
		largeAnswer %= MOD;
		largeAnswer = ntu.divideModulo(largeAnswer, large);
		
		long smallAnswer = smallStrengthSum * (large + 1 - shields[i].durability);
		smallAnswer %= MOD;
		smallAnswer = ntu.divideModulo(smallAnswer, large + 1);
		
		long answer = ntu.addModulo(smallAnswer, largeAnswer);
		
		out.println(answer);
	}
	
	
}



private static int findLowerBound(int defence) {
	int lo = 0, hi = n, ans = n;
	
	while(lo <= hi) {
		int mid = (lo + hi) >> 1;
		if(strengths[mid] >= defence) {
			hi = mid - 1;
			ans = mid;
		}else lo = mid + 1;
	}
	return ans;
	
}

public static PrintWriter out;
public static MyScanner s;
static void shuffleArray(int[] a) {
	Random random = new Random();
	for (int i = a.length-1; i > 0; i--) {
		int index = random.nextInt(i + 1);
		int tmp = a[index];
		a[index] = a[i];
		a[i] = tmp;
	}
}
static void shuffleSort(int[] a) {
	shuffleArray(a);
	Arrays.parallelSort(a);
}
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