import java.io.*;
import java.util.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
static class edge implements Comparable<edge>{
        int to, from, weight;
        public edge(int x, int y, int z) {
            to = Math.min(x, y);
            from = Math.max(x,  y);
            weight = z;
        }
        public int compareTo(edge e) {
            return weight - e.weight;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + to;
            result = prime * result + from;
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            edge cur = (edge)obj;
            if(to == cur.to && from == cur.from)return true;
            if(to == cur.from && from == cur.to)return true;
            return false;
        }
    }
static class Pair<E> implements Comparable<Pair<E>>{

       E a;
       E b;
//       int ind;
//       public Pair(int x, long y) {a = x;b=y;}
       public Pair(E x, E y) {a = x;b=y;}
//       public Pair(int x,int y, int z){a=x;b=y;ind = z;}
       public int compareTo(Pair<E> p){
           return Integer.compare((Integer)a, (Integer)p.a);
       }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (Integer)a;
            result = prime * result + (Integer)b;
           
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            Pair<Integer> cur = (Pair<Integer>)obj;
            if((Integer)a == (Integer)cur.a && (Integer)b == (Integer)cur.b)return true;
            if((Integer)b == (Integer)cur.a && (Integer)a == (Integer)cur.b)return true;
            return false;
        }
 } 
public static void main(String[] args) throws Exception {
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
       inv_fact[i] = my_inv(fact[i]);
  }
}
static void lazy(int st, int e, int tn) {
    if(lazy[tn] != INF) {
        tree[tn] = Math.min(tree[tn], lazy[tn]);
        if(st != e) {
            lazy[2*tn] = Math.min(lazy[2*tn], lazy[tn]);
            lazy[2*tn+1] = Math.min(lazy[2*tn+1], lazy[tn]);
        }
        lazy[tn] = INF;
    }
}
static void update(int ind, int val, int st, int e, int tn) {
    if(st == e) {
        tree[tn] = val;
        return;
    }
    int mid = (st + e) >> 1;    
      if(ind <= mid)
        update(ind, val, st, mid, 2*tn);
      else
        update(ind, val, mid+1, e, 2*tn+1);
    tree[tn] = Math.min(tree[2*tn], tree[2*tn+1]);
}
static int query(int l, int r, int st, int e, int tn) {
    if(st > r || e < l)return INF;
    
    if(st >= l && e <= r) {
        return tree[tn];
    }

    int mid = (st + e) >> 1;
    int x = query(l, r, st, mid, 2 * tn);
    int y = query(l, r, mid+1, e, 2 * tn + 1);
    return Math.min(x, y);
}
static final long mxx = (long)(1e18+5);
static final int mxN = (int)(1e5);
static final int mxV = (int)(1e5+5), log = 18;
static long mod = (long)(1e9+7); //998244353;//
static int[]tree, lazy, dep;
static long[]fact, inv_fact, a;
static final int INF = (int)2e9+5;
static boolean[]vis;
static ArrayList<ArrayList<Integer>> adj;
static int n, m, k, x, y, z, q;
static char[]arr;
static int[][]par;
static void dfs(int u, int p) {
	vis[u] = true;
	if(p != -1) {
		par[0][u] = p;
	}
	for(int i=1; i<18; i++) {
		if(par[i-1][u] == -1)break;
		par[i][u] = par[i-1][par[i-1][u]];
	}
	for(Integer x : adj.get(u)) {
		if(!vis[x]) {
			dep[x] = dep[u] + 1;
			dfs(x, u);
		}
	}
}
static int lca(int u, int v) {
	if(dep[u] < dep[v]) {
		return lca(v, u);
	}
	for(int i=log-1; i>=0; i--) {
		if(par[i][u] == -1)continue;
		if(dep[u] - (1<<i) >= dep[v]) {
			u = par[i][u];
		}
	}
	if(u == v)return u;
	for(int i=log-1; i>=0; i--) {
		if(par[i][u] == -1)continue;
		if(par[i][u] != par[i][v]) {
			u = par[i][u];
			v = par[i][v];
		}
	}
	return par[0][u];
}
static int dis(int u, int v) {
	int lca = lca(u, v);
//	out.println("lca of " + u + " and " + v + " is " + lca);
	return dep[u] + dep[v] - dep[lca] * 2;
}
public static void solve() throws Exception {
   // solve the problem here
   MyScanner s = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out), true);
        int tc = 1;//s.nextInt();
        while(tc-->0){
        	n = s.nextInt();
        	adj = new ArrayList<ArrayList<Integer>>();
        	for(int i=0; i<n; i++)adj.add(new ArrayList<Integer>());
        	for(int i=0; i<n-1; i++) {
        		int u = s.nextInt()-1;
        		int v = s.nextInt()-1;
        		adj.get(u).add(v);
        		adj.get(v).add(u);
        	}
        	q = s.nextInt();
        	par = new int[18][n];
        	dep = new int[n];
        	vis = new boolean[n];
        	for(int i=0; i<18; i++)Arrays.fill(par[i], -1);
        	dfs(0, -1);
        	for(int i=0; i<q; i++) {
        		int x = s.nextInt()-1;
        		int y = s.nextInt()-1;
        		int a = s.nextInt()-1;
        		int b = s.nextInt()-1;
        		int k = s.nextInt();
        		boolean ok = false;
        		int ab = dis(a, b);
        		int xy = dis(x, y);
        		int ax = dis(a, x);
        		int bx = dis(b, x);
        		int ay = dis(a, y);
        		int by = dis(b, y);
        		if(ab <= k && (k-ab) % 2 == 0)ok = true;
        		if(ax + 1 + by <= k && (k - (ax+by+1)) % 2 == 0)ok = true;
        		if(ay + 1 + bx <= k && (k - (ay+bx+1)) % 2 == 0)ok = true;
        		if(ax+1+by+xy+1 <= k && (k-(ax+1+by+xy+1)) % 2 == 0)ok = true;
        		if(ay+1+bx+xy+1 <= k && (k-(ay+1+bx+xy+1)) % 2 == 0)ok = true;
        		out.println(ok ? "YES" : "NO");
        	}
        }   
           
        out.flush();
}
 
         
 
 
 
    //-----------PrintWriter for faster output---------------------------------
    public static PrintWriter out;
 
    //-----------MyScanner class for faster input----------
    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;
 
        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
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
    //--------------------------------------------------------
}
