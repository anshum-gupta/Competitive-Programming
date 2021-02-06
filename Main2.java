import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Stream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class Main2 {
	


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
 

static final long INF_LONG = (long)(1e18 + 5), MOD = (long)1e9 + 7;//998244353; 
static final int MXN = (int)(1001), MXV = (int)(2e5 + 5), log = 18, INF = (int)1e9 + 500;
static boolean[] vis;
static ArrayList<ArrayList<Integer>> adj;
static int N, M, K, Q, H, W;
static char[] S;




public static void solve() throws Exception {
	   // solve the problem here
		s = new MyScanner();
   		out = new MyWriter(new BufferedOutputStream(System.out));
   		
        int tc = s.nextInt();   
    	for(int i=1; i<=tc; i++) {
    		testcase();
    	}
          
        out.flush();
        out.close();
}



static void testcase() {
	
	// for lcm to exist, gcd should be there
	
	char[] S = s.next().toCharArray(), T = s.next().toCharArray();
	int N = S.length, M =  T.length;
	int gcd = -1;

	for(int i = 0; i < N; i++) {
		
		if(divides(S, i + 1) && divides(T, i + 1) && equals(S, T, i + 1)) {
			gcd = i + 1;
		}
		
	}
	
	if(gcd == -1) {
		out.println("-1");
		return;
	}
	
	int times = N * M / gcd / gcd;
	
	
	StringBuilder ans = new StringBuilder("");
	for(int i = 0; i < gcd; i++) ans.append(S[i]);
	String temp = ans.toString();
	
	for(int i = 0; i < times - 1; i++) {
		ans.append(temp);
	}
	
	out.println(ans);
	
}



private static boolean divides(char[] arr, int len) {
	
	if(arr.length % len != 0) return false;
	
	for(int j = len; j < arr.length; j++) {
		
		if(arr[j] != arr[j - len]) return false;
		
	}
	
	return true;
	
}



private static boolean equals(char[] A, char[] B, int len) {
	
	for(int i = 0; i < len; i++) {
		if(A[i] != B[i]) return false;
	}
	
	return true;
	
}


public static MyWriter out;
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

static void shuffleArray(long[] a) {
	Random random = new Random();
	for (int i = a.length-1; i > 0; i--) {
		int index = random.nextInt(i + 1);
		long tmp = a[index];
		a[index] = a[i];
		a[i] = tmp;
	}
}
static void shuffleSort(long[] a) {
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
    	int[] a = new int[n];
    	for(int i = 0; i < n; i++) {
    		a[i] = this.nextInt();
    	}
    	return a;
    }
    long[] nextlongArray(int n) {
    	long[] a = new long[n];
    	for(int i = 0; i < n; i++) {
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
 
static class MyWriter extends PrintWriter {
    MyWriter(OutputStream out) {
        super(out);
    }
    void println(int[] x) {
        println(x, " ");
    }
    void println(int[] x, String delimiter) {
        if (x.length > 0) {
            print(x[0]);
            for (int i = 1; i < x.length; i++) {
                print(delimiter);
                print(x[i]);
            }
        }
        println();
    }
    void println(long[] x) {
        println(x, " ");
    }
    void println(long[] x, String delimiter) {
        if (x.length > 0) {
            print(x[0]);
            for (int i = 1; i < x.length; i++) {
                print(delimiter);
                print(x[i]);
            }
        }
        println();
    }
    void println(Iterable<?> iterable) {
        println(iterable, " ");
    }
    void println(Iterable<?> iterable, String delimiter) {
        Iterator<?> i = iterable.iterator();
        if (i.hasNext()) {
            print(i.next());
            while (i.hasNext()) {
                print(delimiter);
                print(i.next());
            }
        }
        println();
    }
    void printLines(int[] x) {
        println(x, System.lineSeparator());
    }
    void printLines(long[] x) {
        println(x, System.lineSeparator());
    }
    void printLines(Iterable<?> iterable) {
        println(iterable, System.lineSeparator());
    }
}
 
}