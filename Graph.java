import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;


public class Graph {
	ArrayList<ArrayList<Integer>> adjacencyList;
	boolean[]visited;
	final MyScanner s = new MyScanner();
	public Graph() {}
	int n;
	public Graph(int n) {
		this.n = n;
//		System.out.println("Graph constructor called!");
		visited = new boolean[n+1];
		adjacencyList = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<=n; i++)adjacencyList.add(new ArrayList<Integer>());
	}
	public void readUndirectedGraph(int m) {
		for(int i=0; i<m; i++) {
			int u = s.nextInt();
			int v = s.nextInt();
			adjacencyList.get(u).add(v);
			adjacencyList.get(v).add(u);
		}
	}
	public void print_graph() {
		for(int i=1; i<=n; i++) {
			System.out.print("Edge from " + i + " to --->  ");
			for(Integer x : adjacencyList.get(i)) {
				System.out.print(x + " ");
			}System.out.println();
		}
	}
	public void dfs(int u) {
		visited[u] = true;
		for(Integer v : adjacencyList.get(u)) {
			if(!visited[v]) {
				dfs(v);
			}
		}
	}
	public void bfs(int u) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		visited[u] = true;
		q.add(u);
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(Integer x : adjacencyList.get(cur)) {
				if(!visited[x]) {
					visited[x] = true;
					q.add(x);
				}
			}
		}
	}
	void dfsForTopologicalSort(int u, ArrayList<Integer> topologicalSort) {
		visited[u] = true;
		for(Integer x : adjacencyList.get(u)) {
			if(!visited[x]) {
				dfsForTopologicalSort(x, topologicalSort);
			}
		}
		topologicalSort.add(u);
	}
	public ArrayList<Integer>topologicalSort(int u) {
			ArrayList<Integer> topologicalSort = new ArrayList<Integer>();
			for(int i=1; i<=n; i++) {
				if(!visited[i]) {
					dfsForTopologicalSort(i, topologicalSort);
				}
			}
			return topologicalSort;
	}
	public void bfsWithDistanceStore(int u, int[]distance) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		visited[u] = true;
		q.add(u);
		distance[u] = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(Integer x : adjacencyList.get(cur)) {
				if(!visited[x]) {
					visited[x] = true;
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
		Arrays.fill(visited, false);
		for(int i=1; i<=n; i++) {
			if(containsCycleUtilDirectedGraph(i, recursionStack)) {
				return true;
			}
		}
		return false;
	}
	private boolean containsCycleUtilDirectedGraph(int i, boolean[] recursionStack) {
		if(recursionStack[i])return true;
		if(visited[i])return false;
		visited[i] = recursionStack[i] = true;
		for(Integer x : adjacencyList.get(i)) {
			if(containsCycleUtilDirectedGraph(x, recursionStack))return true;
		}
		return false;
	}
	public ArrayList<ArrayList<Integer>> getTransposeGraph(){
		ArrayList<ArrayList<Integer>> transposeGraph = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<=n; i++)transposeGraph.add(new ArrayList<Integer>());
		for(int i=0; i<=n; i++) {
			for(Integer x : adjacencyList.get(i)) {
				transposeGraph.get(x).add(i);
			}
		}
		return transposeGraph;
	}
	public int[] stronglyConnectedComponentsKosaraju() {
		ArrayList<ArrayList<Integer>> transposeGraph = this.getTransposeGraph();
		Stack<Integer> stack = new Stack<Integer>();
		int[]representativeOfComponent = new int[n+1];
		for(int i=1; i<=n; i++) {
			if(!visited[i]) {
				dfsForStronglyConnectedComponents(i, stack);
			}
		}
		Arrays.fill(visited, false);
		while(!stack.isEmpty()) {
			int u = stack.pop();
			if(!visited[u]) {
				secondDfsForStronglyConnectedComponents(u, transposeGraph, representativeOfComponent, u);
			}
		}
		return representativeOfComponent;
	}
	private void secondDfsForStronglyConnectedComponents(int u, ArrayList<ArrayList<Integer>> transposeGraph, int[] representativeOfComponent, int representative) {
		visited[u] = true;
		representativeOfComponent[u] = representative;
		for(Integer x : transposeGraph.get(u)) {
			if(!visited[x]) {
				secondDfsForStronglyConnectedComponents(x, transposeGraph, representativeOfComponent, representative);
			}
		}
	}
	private void dfsForStronglyConnectedComponents(int u, Stack<Integer> stack) {
		visited[u] = true;
		for(Integer x : adjacencyList.get(u)) {
			if(!visited[x]) {
				dfsForStronglyConnectedComponents(x, stack);
			}
		}
		stack.push(u);
	}
	
}
