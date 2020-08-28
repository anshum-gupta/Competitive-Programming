import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution {
static class Graph {
	ArrayList<ArrayList<Integer>> adj;
	boolean[]vis;
	final MyScanner s = new MyScanner();
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
			for(int i=1; i<=n; i++) {
				if(!vis[i]) {
					dfsForTopologicalSort(i, topologicalSort);
				}
			}
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
	public boolean containsCycleDirectedGraph() {
		boolean[]recursionStack = new boolean[n+1];
		Arrays.fill(vis, false);
		for(int i=1; i<=n; i++) {
			if(containsCycleUtilDirectedGraph(i, recursionStack)) {
				return true;
			}
		}
		return false;
	}
	private boolean containsCycleUtilDirectedGraph(int i, boolean[] recursionStack) {
		if(recursionStack[i])return true;
		if(vis[i])return false;
		vis[i] = recursionStack[i] = true;
		for(Integer x : adj.get(i)) {
			if(containsCycleUtilDirectedGraph(x, recursionStack))return true;
		}
		return false;
	}
}

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
	static class Edge implements Comparable<Edge>{
		int source, destination;
		long weight;
		public Edge() {}
		public Edge(int source, int destination, long weight) {
			this.source = source;
			this.destination = destination;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Long.compare(weight, o.weight);
		}
	}
	
	public MinimumSpanningTree(int n) {
		super(n);
	}
	public long PrimsTotalWeight(ArrayList<ArrayList<Pair>> adj, int start) {
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
//					out.println(pq.size());
					if(p.distance < distance[p.vertex]) {
						Pair curPair = new Pair(p.vertex, distance[p.vertex]);
						curPair.distance = distance[p.vertex] = p.distance;
						pq.add(curPair);
						parent[p.vertex] = cur.vertex;
					}
				}
			}
		}
		for(int i=0; i<=n; i++)mst.add(new ArrayList<MinimumSpanningTree.Pair>());
		long totalDistance = 0;
//		out.println(Arrays.toString(parent));
//		out.println(Arrays.toString(distance));
		for(int i=1; i<=n; i++) {
			if(parent[i] != i) {
				mst.get(i).add(new Pair(parent[i], distance[i]));
				mst.get(parent[i]).add(new Pair(i, distance[i]));
				totalDistance += distance[i];
			}
		}
		return totalDistance;
	}
	
	public ArrayList<ArrayList<Pair>> Prims(ArrayList<ArrayList<Pair>> adj, int start){

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
//					out.println(pq.size());
					if(p.distance < distance[p.vertex]) {
						Pair curPair = new Pair(p.vertex, distance[p.vertex]);
						curPair.distance = distance[p.vertex] = p.distance;
						pq.add(curPair);
						parent[p.vertex] = cur.vertex;
					}
				}
			}
		}
		for(int i=0; i<=n; i++)mst.add(new ArrayList<MinimumSpanningTree.Pair>());
		for(int i=1; i<=n; i++) {
			if(parent[i] != i) {
				mst.get(i).add(new Pair(parent[i], distance[i]));
				mst.get(parent[i]).add(new Pair(i, distance[i]));
			}
		}
		return mst;
	
	}

	public Edge[] Kruskals(Edge[]edges, int start) {
		Edge[] result = new Edge[n];
		Arrays.parallelSort(edges);
		DisjointSetLearning ds = new DisjointSetLearning(n);
		ds.init();
		int currentEdgeCount = 0, index = 0;
		while(currentEdgeCount < n - 1) {
			Edge currentEdge = edges[index++];
			int representativeOfEdgeSource = ds.find(currentEdge.source);
			int representativeOfEdgeDestination = ds.find(currentEdge.destination);
			if(representativeOfEdgeSource != representativeOfEdgeDestination) {
				result[currentEdgeCount++] = currentEdge;
				ds.union(representativeOfEdgeSource, representativeOfEdgeDestination);
			}
		}
		return result;
	}
	public long KruskalsTotalWeight(Edge[]edges, int start) {
		Edge[] result = new Edge[n];
		long totalDistance = 0;
		Arrays.parallelSort(edges);
		DisjointSetLearning ds = new DisjointSetLearning(n);
		ds.init();
		int currentEdgeCount = 0, index = 0;
		while(currentEdgeCount < n - 1) {
			Edge currentEdge = edges[index++];
			int representativeOfEdgeSource = ds.findWithCaching(currentEdge.source);
			int representativeOfEdgeDestination = ds.findWithCaching(currentEdge.destination);
			if(representativeOfEdgeSource != representativeOfEdgeDestination) {
				result[currentEdgeCount++] = currentEdge;
				totalDistance += currentEdge.weight;
				ds.unionByRank(representativeOfEdgeSource, representativeOfEdgeDestination);
			}
		}
		return totalDistance;
	}
}

static class DisjointSetLearning {
	int[]parent;
	int[]rank;
	int n;
	public DisjointSetLearning(int n) {
		parent = new int[n+1];
		rank = new int[n+1];
		this.n = n;
	}
	public void init() {
		for(int i=0; i<=n; i++)
			parent[i] = i;
	}
	public int find(int i) {
		if(parent[i] == i) {
			return parent[i];
		}
		return find(parent[i]);
	}
	public void union(int x, int y) {
		int xRepresentative = this.find(x);
		int yRepresentative = this.find(y);
		this.parent[xRepresentative] = yRepresentative;
	}
	// find the representative that i is a part of
	// small improvement by using caching
		public int findWithCaching(int i) {
			if(parent[i] == i) {
				return i;
			}
			return parent[i] = find(parent[i]);
		}
		public void unionByRank(int x, int y) {
			int xRepresentative = this.find(x);
			int yRepresentative = this.find(y);
			if(xRepresentative == yRepresentative)
				return;
			int xRank = rank[x];
			int yRank = rank[y];
			if(xRank < yRank) {
				this.parent[xRepresentative] = yRepresentative;
			}else if(yRank < xRank) {
				this.parent[yRepresentative] = xRepresentative;
			}else {
				this.parent[xRepresentative] = yRepresentative;
				rank[yRepresentative]++;
			}
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
static final int mxV = (int)(1e6), log = 18;
static long mod = (long)(1e9+7); //998244353;
static final int INF = (int)1e9;
static boolean[]vis, recst;
static ArrayList<ArrayList<Integer>> adj;
static int n, m, k, q, x;
static char[]str;

public static void solve() throws Exception {
	   // solve the problem here
		s = new MyScanner();
   		out = new PrintWriter(new BufferedOutputStream(System.out), true);
//	   		out = new PrintWriter("output.txt");
       int n = s.nextInt();
       int m = s.nextInt();
       MinimumSpanningTree mst = new MinimumSpanningTree(n);
       MinimumSpanningTree.Edge[] edges = new MinimumSpanningTree.Edge[m];
       for(int i=0; i<m; i++) {
    	   edges[i] = new MinimumSpanningTree.Edge(s.nextInt(), s.nextInt(), s.nextLong());
       }
       long totalDistance = mst.KruskalsTotalWeight(edges, 1);
       out.println(totalDistance);
           
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
