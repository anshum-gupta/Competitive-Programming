import java.util.Arrays;

public class StringAlgorithms {
	char[]str;
	int n;
	public StringAlgorithms() {
		
	}
	public StringAlgorithms(String str) {
		this.str = str.toCharArray();
		n = str.length();
	}
	public StringAlgorithms(char[]str) {
		this.str = Arrays.copyOf(str, str.length);
		n = str.length;
	}
	int[] z() {
	    int l = 0, r = 0;
	    int[]z = new int[n];
	    for(int i=1; i<n; i++) {
	        if(i > r) {
	            l = r = i;
	            while(r < n && str[r-l] == str[r])r++;
	            z[i] = r-- - l;
	        }else {
	            int k = i - l;
	            if(z[k] < r - i + l)z[i] = z[k];
	            else {
	                l = i;
	                while(r < n && str[r-l] == str[r])r++;
	                z[i] = r-- - l;
	            }
	        }
	    }
	    return z;
	} 
}
