import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
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
static final int mxV = (int)(1e6), log = 18;
static long mod = (long)(1e9+7); //998244353;
static final int INF = (int)1e9;
static boolean[]vis, recst;
static ArrayList<ArrayList<Integer>> adj;
static int n, m, k, q, x;
static char[]str;
static char[][] arr;
static int[]a, indeg;
static ArrayList<Integer> top;
public static void solve() throws Exception {
	   // solve the problem here
		s = new MyScanner();
   		out = new PrintWriter(new BufferedOutputStream(System.out), true);
//	   		out = new PrintWriter("output.txt");
        int tc = s.nextInt();
        for(int i=1; i<=tc; i++) {
        	out.print("Case #" + i + ": ");
        	testcase();
        }
           
        out.flush();
        out.close();
}
static void dfs(int u) {
	vis[u] = true;
	for(Integer x : adj.get(u)) {
		if(!vis[x]) {
			dfs(x);
		}
	}
	top.add(u);
}
static void testcase() {
	n = s.nextInt();
	m = s.nextInt();
	arr = new char[n][];
	for(int i=0; i<n; i++) {
		arr[i] = s.next().toCharArray();
	}
	adj = new ArrayList<ArrayList<Integer>>();
	indeg = new int[26];
	for(int i=0; i<26; i++)adj.add(new ArrayList<Integer>());
	boolean[]isPart = new boolean[26];
	for(int i=0; i<n-1; i++) {
		for(int j=0; j<m; j++) {
			int before = arr[i+1][j] - 'A';
			int after = arr[i][j] - 'A';
			isPart[before] = isPart[after] = true;
			if(before != after){
				adj.get(before).add(after);
				indeg[after]++;
			}
		}
	}
	vis = new boolean[26];
	recst = new boolean[26];
	boolean ok = true;
	for(int i=0; i<26; i++) {
		if(isPart[i]) {
			if(hasCycle(i)) {
				ok = false;
				break;
			}
		}
	}
	if(!ok) {
		out.print("-1\n");
		return;
	}
	Arrays.fill(vis, false);
	top = new ArrayList<Integer>();
	for(int i=0; i<26; i++) {
		if(!vis[i] && isPart[i]) {
			dfs(i);
		}
	}
	Collections.reverse(top);
	for(Integer x : top) {
		out.print((char)(x + 'A'));
	}out.print("\n");
}
private static boolean hasCycle(int u) {
	if(recst[u])return true;
	if(vis[u])return false;
	vis[u] = true;
	recst[u] = true;
	for(Integer x : adj.get(u)) {
			if(hasCycle(x))return true;
	}
	recst[u] = false;
	return false;
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
