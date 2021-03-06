import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
public class arraySort{
	private int[] array;

	public arraySort(int n){
		this.array=new int[n];
		for(int count=0;count<this.array.length;count++) {
			this.array[count] = randomWithRange(0,99);
		}
	}
	
	public static int randomWithRange(int min, int max){
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
	

	public static class Parallel extends RecursiveAction {
		
		private int[] n;
		private int left;
		private int right;

	    public Parallel(int[] array, int left, int right){
	      this.n = array;
	      this.left = left;
	      this.right = right;
	      
	      
	    }

		@Override
		protected void compute() {
			if(left<right) {
				if(right-left<=1) {
					int mid = (left+right)/2;
					Parallel divideLeft = new Parallel(n, left, mid);
					Parallel divideRight = new Parallel(n, mid+1, right);
					ForkJoinTask.invokeAll(divideLeft, divideRight);
					this.merge(left, mid, right);
				}else if(right-left<=16){
					int mid = (left+right)/2;
					Parallel divideLeft = new Parallel(n, left, mid);
					Parallel divideRight = new Parallel(n, mid+1, right);
					ForkJoinTask.invokeAll(divideLeft, divideRight);
					this.merge(left, mid, right);
				}else if(right-left<=100) {
					int mid = (left+right)/2;
					Parallel divideLeft = new Parallel(n, left, mid);
					Parallel divideRight = new Parallel(n, mid+1, right);
					ForkJoinTask.invokeAll(divideLeft, divideRight);
					this.merge(left, mid, right);
				}else if(right-left<=500) {
					int mid = (left+right)/2;
					Parallel divideLeft = new Parallel(n, left, mid);
					Parallel divideRight = new Parallel(n, mid+1, right);
					ForkJoinTask.invokeAll(divideLeft, divideRight);
					this.merge(left, mid, right);
					
				}else {
					int mid = (left+right)/2;
					Parallel divideLeft = new Parallel(n, left, mid);
					Parallel divideRight = new Parallel(n, mid+1, right);
					ForkJoinTask.invokeAll(divideLeft, divideRight);
					this.merge(left, mid, right);
					
				}
				if(left==0 && right == this.n.length-1) {
					//this.printArray(); uncomment this to print sorted integer list
				}
				
				
				
			}
			
		}
		
		public void merge(int left, int middle, int right) {
			int countLeft = middle - left + 1,
				countRight = right - middle,
				leftSide[] = new int [countLeft],
	        	rightSide[] = new int [countRight]; 
	        for (int i=0; i<countLeft; ++i) {
	        	 leftSide[i] = this.n[left + i]; 
	        }
	        for (int j=0; j<countRight; ++j) {
	        	rightSide[j] = this.n[middle + 1+ j]; 
	        }
	        int i = 0, 
	        	j = 0,
	        	k = left; 
	        while (i < countLeft && j < countRight) { 
	            if (leftSide[i] <= rightSide[j]) { 
	                this.n[k] = leftSide[i]; 
	                i++; 
	            } 
	            else{ 
	                this.n[k] = rightSide[j]; 
	                j++; 
	            } 
	            k++; 
	        }
	        while (i < countLeft) { 
	            this.n[k] = leftSide[i]; 
	            i++; 
	            k++; 
	        } 
	        while (j < countRight) { 
	            this.n[k] = rightSide[j]; 
	            j++; 
	            k++; 
	        } 
		}
		
		public void printArray() {
			int array[] = this.n;
			for(int l =0 ; l<array.length ; l++) {
				System.out.println((l+1)+": "+array[l]+"\n");
			}
		}
	    
		
	}


	    
	
	public static void main(String[] args) {
		int sizes[]= {1, 40, 100, 4000,10000, 100000,1000000,100000000};
		for(int i=0;i<sizes.length;i++) {
			arraySort a = new arraySort(sizes[i]);
			System.out.println("Array size: "+a.array.length);
			long startTime = System.nanoTime();
			a.startMergeSort();
			long endTime   = System.nanoTime();
			long totalTime = endTime - startTime;
			System.out.println("Time(seconds): "+(totalTime*(Math.pow(10, -9)))+" -> recursive way");	
			///
			
			///
			int arrayParallel[] = new int[4000];
			for(int count=0;count<arrayParallel.length;count++) {
				arrayParallel[count] = randomWithRange(0,99);
			}
			ForkJoinPool pool = new ForkJoinPool();
			long sTime = System.nanoTime();
			pool.invoke(new Parallel(arrayParallel, 0, arrayParallel.length-1));
			long eTime   = System.nanoTime();
			long tTime = eTime - sTime;
			System.out.println("Time(seconds): "+(tTime*(Math.pow(10, -9)))+" -> ForkJoinPool \n --------------");	
			
		} 
		
	}
	
	
	

	

}

