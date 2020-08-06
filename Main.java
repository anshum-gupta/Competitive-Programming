import java.io.*;
import java.util.*;
import java.math.*;
import java.util.regex.*;

public class Main {
public static void main(String[] args) {
	new Thread(null, null, "Anshum Gupta", 99999999) {
		public void run() {
			try {
				solve();
			} catch( Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}.start();
}
public static void solve() throws Exception {
   // solve the problem here
		MyScanner s = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out), true);
        int[]arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        SegmentTree st = new SegmentTree(arr, 0, arr.length-1);
        RangeSlow rs = new RangeSlow(arr);
        int a = rs.sum(3, 8);
        int b = st.sum(3, 8);
        out.println(a + " " + b);
        st.pointUpdate(5, 100);
        rs.pointUpdate(5, 100);
        a = rs.sum(3, 8);
        b = rs.sum(3, 8);
        out.println(a + " " + b);
}
 
         
 
 
 
    //-----------PrintWriter for faster output---------------------------------
    public static PrintWriter out;
 
    //-----------MyScanner class for faster input----------
    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;
 
        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
 
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
         
        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    //--------------------------------------------------------
}
