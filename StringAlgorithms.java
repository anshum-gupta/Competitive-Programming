import java.util.Arrays;

public class StringAlgorithms {
	char[]text;
	int textLength;
	
	public StringAlgorithms() {
		
	}
	
	public StringAlgorithms(String str) {
		this.text = str.toCharArray();
		textLength = str.length();
	}
	
	public StringAlgorithms(char[]str) {
		this.text = Arrays.copyOf(str, str.length);
		textLength = str.length;
	}
	 
	int[] z() {
	    int l = 0, r = 0;
	    int[]z = new int[textLength];
	    for(int i=1; i<textLength; i++) {
	        if(i > r) {
	            l = r = i;
	            while(r < textLength && text[r-l] == text[r])r++;
	            z[i] = r-- - l;
	        }else {
	            int k = i - l;
	            if(z[k] < r - i + l)z[i] = z[k];
	            else {
	                l = i;
	                while(r < textLength && text[r-l] == text[r])r++;
	                z[i] = r-- - l;
	            }
	        }
	    }
	    return z;
	} 

	public void kmp(char[]pattern) {
		int[]longestPrefixSuffix = longestPrefixSuffix(pattern);
		int textIndex = 0, patternIndex = 0;
		while(textIndex < textLength) {
			if(pattern[patternIndex] == text[textIndex]) {
				textIndex++;
				patternIndex++;
			}
			if(patternIndex == pattern.length) {
				// pattern found at index textIndex - patternIndex
				patternIndex = longestPrefixSuffix[patternIndex - 1];
			}else if(textIndex < textLength && pattern[patternIndex] != text[textIndex]) {
				if(patternIndex != 0) {
					patternIndex = longestPrefixSuffix[patternIndex - 1];
				}else {
					textIndex++;
				}
			}
		}
	}

	private int[] longestPrefixSuffix(char[] pattern) {
		int patternLength = pattern.length;
		int[]longestPrefixSuffix = new int[patternLength];
		for(int patternIndex = 1; patternIndex < patternLength; patternIndex++) {
			int possibleMatchingIndex = longestPrefixSuffix[patternIndex - 1];
			while(possibleMatchingIndex > 0 && pattern[possibleMatchingIndex] != pattern[patternIndex]) {
				possibleMatchingIndex = longestPrefixSuffix[possibleMatchingIndex - 1];
			}
			if(pattern[possibleMatchingIndex] == pattern[patternIndex]) {
				possibleMatchingIndex++;
			}
			longestPrefixSuffix[patternIndex] = possibleMatchingIndex;
		}
		return longestPrefixSuffix;
	}

	// This kmp seems complicated, an easy way : 
	// concatenate PATTERN + # + TEXT
	// compute lps of above
	// if lps(i) == pattern.length pattern found at index i - pattern.length
}


