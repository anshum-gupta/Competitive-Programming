import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


public class MinimumSpanningTree extends Graph{
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
	public long PrimsDistance(ArrayList<ArrayList<Pair>> adj, int start) {
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
	
}

