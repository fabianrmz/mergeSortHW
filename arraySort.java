import java.util.concurrent.ForkJoinPool;

public class arraySort {
	private int[] array;

	public arraySort(int n){
		this.array=new int[n];
		for(int count=0;count<this.array.length;count++) {
			this.array[count] = randomWithRange(0,99);
		}
	}
	
	public int randomWithRange(int min, int max){
		   int range = (max - min) + 1;     
		   return (int)(Math.random() * range) + min;
	}
	
	@SuppressWarnings("unused")
	private void printArray() {
		int array[] = this.array;
		for(int l =0 ; l<array.length ; l++) {
			System.out.println((l+1)+": "+array[l]+"\n");
		}
	}


	private void startMergeSort() {
		this.mergeSorting(0,this.array.length-1);
	}
	
	private void mergeSorting(int left, int right) {
		if(left<right){
			int middle = (left+right)/2;
			this.mergeSorting(left, middle);
			this.mergeSorting(middle+1,right);
			this.merge(left,middle,right);
			
		}
		
		
	}
	
	private void merge(int left, int middle, int right) {
		int countLeft = middle - left + 1,
			countRight = right - middle,
			leftSide[] = new int [countLeft],
        	rightSide[] = new int [countRight]; 
        for (int i=0; i<countLeft; ++i) {
        	 leftSide[i] = this.array[left + i]; 
        }
        for (int j=0; j<countRight; ++j) {
        	rightSide[j] = this.array[middle + 1+ j]; 
        }
        int i = 0, 
        	j = 0,
        	k = left; 
        while (i < countLeft && j < countRight) { 
            if (leftSide[i] <= rightSide[j]) { 
                this.array[k] = leftSide[i]; 
                i++; 
            } 
            else{ 
                this.array[k] = rightSide[j]; 
                j++; 
            } 
            k++; 
        }
        while (i < countLeft) { 
            this.array[k] = leftSide[i]; 
            i++; 
            k++; 
        } 
        while (j < countRight) { 
            this.array[k] = rightSide[j]; 
            j++; 
            k++; 
        } 
	}
	

	public static void main(String[] args) {
		int sizes[]= {4000,10000, 100000,1000000};
		for(int i=0;i<sizes.length;i++) {
			
			arraySort a = new arraySort(sizes[i]);
			System.out.println("Array size: "+a.array.length);
			long startTime = System.nanoTime();
			a.startMergeSort();
			long endTime   = System.nanoTime();
			long totalTime = endTime - startTime;
			System.out.println("Time(seconds): "+(totalTime*(Math.pow(10, -9)))+"\n");	
		}
		
			 
		 
	}

}

