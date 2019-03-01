
public class mainSort {
	private static int[] array;
							

	private static void printArray(int[] array1) {
		int n=array1.length;
		for(int i =0 ; i<n ; i++) {
			System.out.println((i+1)+": "+array1[i]+"\n");
		}
	}



	public static int randomWithRange(int min, int max){
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}

	
	private static int[] newArray(int n) {
		int array[] =new int[n];
		for(int i=0;i<n;i++) {
			int randomNum = randomWithRange(0,99);
			array[i]=randomNum;
		}
		return array;
	}
	
	private static void mergeSorting(int array[], int left, int right) {
		if(left<right){
			int middle = (left+right)/2;
			mergeSorting(array,left, middle);
			mergeSorting(array,middle+1,right);
			merge(array,left,middle,right);
			
		}
		
		
	}
	
	
	private static void merge(int[] array, int left, int middle, int right) {
		int countLeft = middle - left + 1,
			countRight = right - middle,
			leftSide[] = new int [countLeft],
        	rightSide[] = new int [countRight]; 
        for (int i=0; i<countLeft; ++i) {
        	 leftSide[i] = array[left + i]; 
        }
        for (int j=0; j<countRight; ++j) {
        	rightSide[j] = array[middle + 1+ j]; 
        }
        int i = 0, 
        	j = 0,
        	k = left; 
        while (i < countLeft && j < countRight) { 
            if (leftSide[i] <= rightSide[j]) { 
                array[k] = leftSide[i]; 
                i++; 
            } 
            else{ 
                array[k] = rightSide[j]; 
                j++; 
            } 
            k++; 
        }
        while (i < countLeft) { 
            array[k] = leftSide[i]; 
            i++; 
            k++; 
        } 
        while (j < countRight) { 
            array[k] = rightSide[j]; 
            j++; 
            k++; 
        } 
	}
	
	public static void main(String[] args) {
		int size[] = {4000,10000,100000, 1000000};
		
		for(int k=0;k<size.length;k++) {
			System.out.println("Array size: "+size[k]);
			// 1. creating a new array with defined size
			array = newArray(size[k]);
			
			//start time count
			long startTime = System.nanoTime();
			
			// 2. apply mergeSort
			mergeSorting(array,0,array.length-1);
			
			//get and print time
			long endTime   = System.nanoTime();
			long totalTime = endTime - startTime;
			System.out.println("Time(seeconds): "+(Math.pow(10, -9) )*totalTime);
			
			
			
		}
		
		

			
	}
}

