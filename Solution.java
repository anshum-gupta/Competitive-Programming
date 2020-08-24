import java.io.*;
import java.util.*;


public class Solution {
static class MinimumSpanningTree extends Graph{
	final long INFINITY_LONG = (long)(1e18);
	static class Pair{
		int vertex;
		long distance;
		public Pair() {}
		public Pair(int vertex, long distance) {
			this.vertex = vertex;
			this.distance = distance;
		}
	}
	public MinimumSpanningTree(int n) {
		super(n);
	}
	public long Prims(ArrayList<ArrayList<Pair>> adj, int start) {
		ArrayList<ArrayList<Pair>> mst = new ArrayList<>();
		long[]distance = new long[super.n+1];
		int[]parent = new int[super.n+1];
		Arrays.fill(distance, INFINITY_LONG);
		Arrays.fill(parent, -1);
		distance[start] = 0;
		parent[0] = 0; distance[0] = 0;
		parent[start] = start;
		PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return Long.compare(o1.distance, o2.distance);
			}
		});
		for(int i=1; i<=n; i++) {		
			pq.add(new Pair(i, distance[i]));
		}
		vis[0] = true;
		Arrays.fill(vis, false);
		
		while(!pq.isEmpty()) {	
			Pair cur = pq.poll();
//			out.println("at vertex " + cur.vertex);
			vis[cur.vertex] = true;
			for(Pair p : adj.get(cur.vertex)) {
				if(!vis[p.vertex]) {
//					out.println("trying vertex " + p.vertex);
					out.println(pq.size());
					if(p.distance < distance[p.vertex]) {
						Pair curPair = new Pair(p.vertex, distance[p.vertex]);
						curPair.distance = distance[p.vertex] = p.distance;
						pq.add(curPair);
						parent[p.vertex] = cur.vertex;
					}
				}
			}
		}
		for(int i=0; i<=n; i++)mst.add(new ArrayList<Solution.MinimumSpanningTree.Pair>());
		long totalDistance = 0;
		out.println(Arrays.toString(parent));
		out.println(Arrays.toString(distance));
		for(int i=1; i<=n; i++) {
			if(parent[i] != i) {
				mst.get(i).add(new Pair(parent[i], distance[i]));
				mst.get(parent[i]).add(new Pair(i, distance[i]));
				totalDistance += distance[i];
			}
		}
		return totalDistance;
	}
}
static class Graph {
	ArrayList<ArrayList<Integer>> adj;
	boolean[]vis;
//	final MyScanner s = new MyScanner();
	public Graph() {}
	int n;
	public Graph(int n) {
		this.n = n;
//		System.out.println("Graph constructor called!");
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
static final long mxx = (long)(1e18+5), mod = (long)(1e9+7); //998244353;//̇;
static final int mxN = (int)(1e6), mxV = (int)(1e6), log = 18, INF = (int)1e9;;
static boolean[][]vis;
static ArrayList<ArrayList<MinimumSpanningTree.Pair>> adj;
static int n, m, k, q, p;
static char[]str;

public static void solve() throws Exception {
	   // solve the problem here
		s = new MyScanner();
   		out = new PrintWriter(new BufferedOutputStream(System.out), true);
//	   		out = new PrintWriter("output.txt");
        int tc = 1;//s.nextInt();
        while(tc-- > 0){
        	n = s.nextInt();
        	m = s.nextInt();
        	MinimumSpanningTree mst = new MinimumSpanningTree(n);
        	adj = new ArrayList<ArrayList<MinimumSpanningTree.Pair>>();
        	for(int i=0; i<=n; i++)adj.add(new ArrayList<Solution.MinimumSpanningTree.Pair>());
        	for(int i=0; i<m; i++) {
        		int u = s.nextInt(), v = s.nextInt(), dist = s.nextInt();
        		adj.get(u).add(new MinimumSpanningTree.Pair(v, dist));
        		adj.get(v).add(new MinimumSpanningTree.Pair(u, dist));
        	}
        	int start = s.nextInt();
        	out.println(mst.Prims(adj, start));
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
