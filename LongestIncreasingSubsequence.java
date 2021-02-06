
public class LongestIncreasingSubsequence {
	
	public static int getLongestIncreasingSubsequenceLength(int[] array) {
		int size = array.length;
		int[] tailTable = new int[size]; 
	    int len; // always points empty slot 
	  
//		System.out.println(Arrays.toString(array));

	    tailTable[0] = array[0]; 
	    len = 1; 
	    for (int i = 1; i < size; i++) { 
	        if (array[i] < tailTable[0]) 
	            // new smallest value 
	            tailTable[0] = array[i]; 

	        else if (array[i] > tailTable[len - 1]) 
	            // A[i] wants to extend largest subsequence 
	            tailTable[len++] = array[i]; 

	        else
	            // A[i] wants to be current end candidate of an existing 
	            // subsequence. It will replace ceil value in tailTable 
	            tailTable[ceilIndex(tailTable, -1, len - 1, array[i])] = array[i]; 
	    } 

	    return len; 
	}

	private static int ceilIndex(int array[], int left, int right, int key) 
	{ 
	    while (right - left > 1) { 
	        int mid = left + (right - left) / 2; 
	        if (array[mid] >= key) 
	            right = mid; 
	        else
	            left = mid; 
	    } 

	    return right; 
	}

}
