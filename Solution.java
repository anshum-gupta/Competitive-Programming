import java.io.*;
import java.util.*;

public class Solution {
static class Pair{
	int a, b;
	public Pair() {}
	public Pair(int a, int b) {
		this.a = a;
		this.b = b;
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
static final int mxN = (int)(4e5+5);
static final int mxV = (int)(1e6+2), log = 18;
static long mod = (long)(1e9+7); //998244353;//̇
static long[]fact, inv_fact;
static final int INF = (int)1e9;
static boolean[][]vis;
static ArrayList<ArrayList<Integer>> adj;
static int n, m, k, q, x, y;
static char[][]arr;
static int[]dis;
static boolean isLeaf(int u) {
	return adj.get(u).size() == 1;
}
static void dfs(int u, int p) {
	for(Integer x : adj.get(u)) {
		if(x != p) {
			dis[x] = dis[u] + 1;
			dfs(x, u);
		}
	}
}
public static void solve() throws Exception {
	   // solve the problem here
		s = new MyScanner();
   		out = new PrintWriter(new BufferedOutputStream(System.out), true);
//	   		out = new PrintWriter("output.txt");
        int tc = 1;//s.nextInt();
        while(tc-->0){
        	n = s.nextInt();
        	adj = s.readUndirectedUnweightedGraph(n, n-1);
        	if(n == 1) {
        		out.println("0");
        		continue;
        	}
        	int root = -1;
        	for(int i=1; i<=n; i++) {
        		if(isLeaf(i)) {
        			root = i;
        			break;
        		}
        	}
        	assert(root != -1);
        	dis = new int[n+1];
        	int diameter = 0;
        	dfs(root, -1);
        	diameter = Arrays.stream(dis).max().getAsInt();
        	out.println(diameter * 3);
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
