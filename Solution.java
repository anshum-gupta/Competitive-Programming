import java.io.*;
import java.util.*;

public class Solution {
static	class Graph {
		ArrayList<ArrayList<Integer>> adj;
		boolean[]vis;
//		final MyScanner s = new MyScanner();
		public Graph() {}
		int n;
		public Graph(int n) {
			this.n = n;
//			System.out.println("Graph constructor called!");
			vis = new boolean[n+1];
			adj = new ArrayList<ArrayList<Integer>>();
			for(int i=0; i<=n; i++)adj.add(new ArrayList<Integer>());
		}
		public void read(int m) {
			for(int i=0; i<m; i++) {
				int u = s.nextInt();
				int v = s.nextInt();
				adj.get(u).add(v);
				adj.get(v).add(u);
			}
		}
		public void print_graph() {
			for(int i=1; i<=n; i++) {
				System.out.print("Edge from " + i + " to --->  ");
				for(Integer x : adj.get(i)) {
					System.out.print(x + " ");
				}System.out.println();
			}
		}
		public void dfs(int u) {
			vis[u] = true;
			for(Integer v : adj.get(u)) {
				if(!vis[v]) {
					dfs(v);
				}
			}
		}
		public void bfs(int u) {
			Queue<Integer> q = new ArrayDeque<Integer>();
			vis[u] = true;
			q.add(u);
			while(!q.isEmpty()) {
				int cur = q.poll();
				for(Integer x : adj.get(cur)) {
					if(!vis[x]) {
						vis[x] = true;
						q.add(x);
					}
				}
			}
		}
		void dfsForTopologicalSort(int u, ArrayList<Integer> topologicalSort) {
			vis[u] = true;
			for(Integer x : adj.get(u)) {
				if(!vis[x]) {
					dfsForTopologicalSort(x, topologicalSort);
				}
			}
			topologicalSort.add(u);
		}
		public ArrayList<Integer>topologicalSort(int u) {
				ArrayList<Integer> topologicalSort = new ArrayList<Integer>();
				dfsForTopologicalSort(1, topologicalSort);
				return topologicalSort;
		}
		public void bfsWithDistanceStore(int u, int[]distance) {
			Queue<Integer> q = new ArrayDeque<Integer>();
			vis[u] = true;
			q.add(u);
			distance[u] = 0;
			while(!q.isEmpty()) {
				int cur = q.poll();
				for(Integer x : adj.get(cur)) {
					if(!vis[x]) {
						vis[x] = true;
						q.add(x);
						distance[x] = distance[cur] + 1;
					}
				}
			}
		}
		public int[] ShortestDistanceFromGivenVertex(int u) {
			int[]res = new int[n];
			this.bfsWithDistanceStore(u, res);
			return res;
		}
		
	}
static class Pair<E, V> implements Comparable<Pair<E, V>>{
       E a;
       V b;
       public Pair(E x, V y) {a = x;b=y;}
       public int compareTo(Pair<E, V> p){
           return Integer.compare((Integer)a, (Integer)p.a);
       }
//        @Override
//        public int hashCode() {
//            final int prime = 31;
//            int result = 1;
//            result = prime * result + (Integer)a;
//            result = prime * result + (Integer)b;
//           
//            return result;
//        }
//        @Override
//        public boolean equals(Object obj) {
//            Pair<E, V> cur = (Pair<E, V>)obj;
//            if((Integer)a == (Integer)cur.a && (Integer)b == (Integer)cur.b)return true;
//            if((Integer)b == (Integer)cur.a && (Integer)a == (Integer)cur.b)return true;
//            return false;
//        }
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
public static long gcd(long a,long b)
{
  if(a<b)
    return gcd(b,a);
  if(b==0)
    return a;
  return gcd(b,a%b);
  
}
static long lcm(int a,int b) {
  return  a*b / gcd(a,b);
}
static int[] z(char[]arr) {
    int l = 0, r = 0;
    int[]z = new int[n];
    for(int i=1; i<n; i++) {
        if(i > r) {
            l = r = i;
            while(r < n && arr[r-l] == arr[r])r++;
            z[i] = r-- - l;
        }else {
            int k = i - l;
            if(z[k] < r - i + l)z[i] = z[k];
            else {
                l = i;
                while(r < n && arr[r-l] == arr[r])r++;
                z[i] = r-- - l;
            }
        }
    }
    return z;
}   
static long pow(long x,long y){
    if(y == 0)return 1;
    if(y==1)return x;
    long a = pow(x,y/2);
    a = (a*a)%mod;
    if(y%2==0){
        return a;
    }
    return (a*x)%mod;
}
static long my_inv(long a) {
    return pow(a,mod-2);
}
static long bin(int a,int b) {
    if(a < b || a<0 || b<0)return 0;
    return ((fact[a]*inv_fact[a-b])%mod * inv_fact[b])%mod;
}
static void make_facts() {
  fact=new long[mxN];
  inv_fact = new long[mxN];
  fact[0]=inv_fact[0]=1L;
  for(int i=1;i<mxN;i++) {
       fact[i] = (i*fact[i-1])%mod;
//       inv_fact[i] = my_inv(fact[i]);
  }
}
static final long mxx = (long)(1e18+5);
static final int mxN = (int)(4e5+5);
static final int mxV = (int)(1e5+5), log = 18;
static long mod = (long)(1e9+7); //998244353;//̇
static long[]fact, inv_fact;
static final int INF = (int)1e9;
static boolean[][]vis;
static ArrayList<ArrayList<Integer>> adj;
static int n, m, k, q, x, y, d;
static char[]s1, s2;
static char[][]arr;
static int[][]dif;

public static void solve() throws Exception {
	   // solve the problem here
			s = new MyScanner();
	   		out = new PrintWriter(new BufferedOutputStream(System.out), true);
//	   		out = new PrintWriter("output.txt");
	        int tc = 1;//s.nextInt();
	        while(tc-->0){
	        	n = s.nextInt();
	        	int[]a = s.nextIntArray(n);
//	        	DebugUtills.printIntArray(a);
	        	BIT_RangeQueryPointUpdate bit = new BIT_RangeQueryPointUpdate(a, n);
	        	bit.init_tree(a);
	        	int ans = bit.sumInRange(2, 4);
	        	out.println(ans);
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
