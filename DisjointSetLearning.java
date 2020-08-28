
public class DisjointSetLearning {
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
	public int findInefficient(int i) {
		if(parent[i] == i) {
			return parent[i];
		}
		return findInefficient(parent[i]);
	}
	public void union(int x, int y) {
		int xRepresentative = this.findInefficient(x);
		int yRepresentative = this.findInefficient(y);
		this.parent[xRepresentative] = yRepresentative;
	}
	// find the representative that i is a part of
	// small improvement by using caching
		public int findWithCaching(int i) {
			if(parent[i] == i) {
				return i;
			}
			return parent[i] = findWithCaching(parent[i]);
		}
		public void unionByRank(int x, int y) {
			int xRepresentative = this.findWithCaching(x);
			int yRepresentative = this.findWithCaching(y);
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
