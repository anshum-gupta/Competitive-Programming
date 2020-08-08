import java.io.*;
import java.util.ArrayList;
public class DebugUtills {
	static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out), true);
	public static void printIntArray(int[]a) {
		for(int i=0; i<a.length; i++) {
			out.print(a[i] + " ");
		}out.println();
	}
	public static void printLongArray(long[]a) {
		for(int i=0; i<a.length; i++) {
			out.print(a[i] + " ");
		}out.println();
	}
	public static void printCharArray(char[]a) {
		for(int i=0; i<a.length; i++) {
			out.print(a[i] + " ");
		}out.println();
	}
	public static void print2DIntegerArray(int[][] a) {
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<a[0].length; j++) {
				out.print(a[i][j] + " ");
			}out.println();
		}
	}
	public static void print2DLongArray(long[][] a) {
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<a[0].length; j++) {
				out.print(a[i][j] + " ");
			}out.println();
		}
	}
	public static void print2DCharArray(char[][] a) {
		for(int i=0; i<a.length; i++) {
			for(int j=0; j<a[0].length; j++) {
				out.print(a[i][j] + " ");
			}out.println();
		}
	}
	public static void printAdjacencyListForUnweightedGraph(ArrayList<ArrayList<Integer>> adj) {
		for(int i=0; i<adj.size(); i++) {
			out.print("Edge from " + i + " to --> ");
			for(Integer x : adj.get(i)) {
				out.print(x + " ");
			}out.println();
		}
	}
	public static void printAdjacencyListForWeightedGraph(ArrayList<ArrayList<Solution.Pair>> adj) {
		for(int i=0; i<adj.size(); i++) {
			out.print("Edge from " + i + " to --> ");
			for(Solution.Pair x : adj.get(i)) {
				out.print(x.a + " ");
			}out.println();
		}
	}
	public static void main(String[]args) {
		int[][]a = new int[][] {{1, 2, 3},
								{4, 5, 6}};
		print2DIntegerArray(a);
	}
}
