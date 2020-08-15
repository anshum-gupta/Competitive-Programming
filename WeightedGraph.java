import java.util.*;

//import Solution.Pair;
public class WeightedGraph extends Graph{
	ArrayList<ArrayList<Pair<Integer, Long>>> adj;
	final long INFINITY_LONG = (long)(1e18);
	long[][]adj_matrix;
	public WeightedGraph(int n) {
		super(n);
		adj = new ArrayList<ArrayList<Pair<Integer,Long>>>();
		for(int i=0; i<=n ;i++) {
			adj.add(new ArrayList<Pair<Integer,Long>>());
		}
	}
	public WeightedGraph(int n, boolean matrixOrNot) {
		super(n);
		adj_matrix = new long[n+1][n+1];
		init_to_infinity(adj_matrix);
	}
	public void init_to_infinity(long[][]adj_matrix) {
		for(int i=0; i<adj_matrix.length; i++) {
			Arrays.fill(adj_matrix[i], INFINITY_LONG);
		}
		for(int i=0; i<adj_matrix.length; i++) {
			adj_matrix[i][i] = 0;
		}
	}
	public void readWeightedGraph(int m) {
		for(int i=0; i<m; i++) {
			int u = s.nextInt();
			int v = s.nextInt();
			long w = s.nextLong();
			adj.get(u).add(new Pair<Integer, Long>(v, w));
			adj.get(v).add(new Pair<Integer, Long>(u, w));
		}
	}
	public long[] dijkstra(int u) { // returns array of minimum distances from source u
		// TODO Complete Dijkstra
		long[] distance = new long[n+1];
		PriorityQueue<Pair<Integer, Long>> pq = new PriorityQueue<Pair<Integer, Long>>(new Comparator<Pair<Integer, Long>>() {
			public int compare(Pair<Integer, Long> p1, Pair<Integer, Long> p2) {
				return Long.compare(p1.b, p2.b);
			}
		});
		Arrays.fill(distance, INFINITY_LONG);
		distance[u] = 0;
		pq.add(new Pair<Integer, Long>(u, distance[u]));
		while(!pq.isEmpty()) {
			Pair<Integer, Long> cur = pq.poll();
			vis[cur.a] = true;
			for(Pair<Integer, Long> x : adj.get(cur.a)) {
				if(!vis[x.a]) {
					if(distance[x.a] > distance[cur.a] + x.b) {
						distance[x.a] = distance[cur.a] + x.b;
						pq.add(new Pair<Integer, Long>(x.a, distance[x.a]));
					}
				}
			}
		}
		return distance;
	}
	public long[][] floydWarshal() {
		long[][]answer = new long[n+1][n+1];
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				answer[i][j] = adj_matrix[i][j];
			}
		}
		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					if(answer[i][k] + answer[k][j] < answer[i][j]) {
						answer[i][j] = answer[i][k] + answer[k][j];
					}
				}
			}
		}
		return answer;
	}
	
}
