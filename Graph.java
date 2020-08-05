import java.io.*;
import java.util.*;
import java.math.*;
import java.util.regex.*;

class Graph {
	ArrayList<ArrayList<Integer>> adj;
	boolean[]vis;
	final MyScanner s = new MyScanner();
	public Graph() {}
	int n;
	public Graph(int n) {
		this.n = n;
		System.out.println("Graph constructor called!");
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
}
