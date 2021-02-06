
public class DisjointSet {
	int[]parent, rank;
	int n;
	public DisjointSet(int n) {
		this.n = n;
		parent = new int[n+1];
		rank = new int[n+1];
		init();
	}
	void init() {
		for(int i = 1; i <= n; i++)parent[i] = i;
	}
	int find(int x) {
		return (parent[x] == x ? x : (parent[x] = find(parent[x])));
	}
	boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return false;
		
		if(rank[x] < rank[y]) {
			parent[x] = y;
		}else if(rank[y] < rank[x]) {
			parent[y] = x;
		}else {
			parent[x] = y;
			rank[y]++;
		}
		
		return true;
	}
}
