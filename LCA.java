import java.io.*;
import java.util.*;
import java.math.*;
import java.util.regex.*;

class LCA extends Graph{
	int[]dep;
	int[][]par;
	final int log = 18;
	public PrintWriter out;
	public LCA(int n) {
		super(n);
//		System.out.println("LCA constructor called!");
		dep = new int[n+1];
		par = new int[log][n+1];
		vis = new boolean[n+1];
		for(int i=0; i<log; i++)Arrays.fill(par[i], -1);
	}
	public LCA() {
		super();
	}
	public void preprocess(int u, int p){
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
				preprocess(x, u);
			}
		}
	}
	public int lca(int u, int v) {
//		System.out.println("lca called!");
		if(dep[u] < dep[v]) {
			return lca(v, u);
		}
		for(int i=log-1; i>=0; i--) {
			if(par[i][u] == -1)continue;
			if(dep[u] - (1<<i) >= dep[v]) {
				u = par[i][u];
			}
		}
		if(u == v) {
//			System.out.println("lca of " + u + " and " + v + " is " + u);
			return u;
		}
		for(int i=log-1; i>=0; i--) {
			if(par[i][u] == -1)continue;
			if(par[i][u] != par[i][v]) {
				u = par[i][u];
				v = par[i][v];
			}
		}
//		System.out.println("lca of " + u + " and " + v + " is " + par[0][u]);
		return par[0][u];
	}
	public int dis(int u, int v) {
		int lca = lca(u, v);
		return dep[u] + dep[v] - dep[lca] * 2;
	}
}
