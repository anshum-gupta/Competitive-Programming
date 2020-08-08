import java.io.*;
import java.util.*;
import java.math.*;
import java.util.regex.*;

public class Generate {
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
		File input = new File("input.txt");
		Scanner scanner = new Scanner(input);
		MyScanner s = new MyScanner();
//        out = new PrintWriter(new BufferedOutputStream(System.out), true);
   		out = new PrintWriter("output.txt");
   		String name = scanner.next();
        out.println("Writing To File After Reading from file : NAME = > " + name);
        System.out.println("Writing To File After Reading from file : NAME = > " + name);
        out.flush();
        out.close();
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
