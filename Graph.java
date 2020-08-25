import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;


class Graph {
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
		if(vis[i])return true;
		vis[i] = recursionStack[i] = true;
		for(Integer x : adj.get(i)) {
			if(containsCycleUtilDirectedGraph(x, recursionStack))return true;
		}
		return false;
	}
}
