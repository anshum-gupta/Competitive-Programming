import java.util.*;
public class WeightedGraph extends Graph{
	ArrayList<ArrayList<Pair<Integer, Long>>> adj;
	final long INFINITY_LONG = (long)(1e18);
	public WeightedGraph(int n) {
		super(n);
		adj = new ArrayList<ArrayList<Pair<Integer,Long>>>();
		vis = new boolean[n+1];
		for(int i=0; i<=n ;i++) {
			adj.add(new ArrayList<Pair<Integer,Long>>());
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
			
		}
		return distance;
	}
}
