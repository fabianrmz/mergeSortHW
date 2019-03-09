public class pathsChicken {
	
	
	private int[][] blocks;
	private int[][] maze;
	private int x;
	private int y;
	private int res;
	
	
	


	public pathsChicken(int x, int y){
		this.res=-1;
		this.maze=new int[y][x];
		this.blocks=new int[y][2];
		this.x=x;
		this.y=y;
		
		//creating blocks coordinates in the maze
		for(int i =0 ;i<this.blocks.length;i++) {
			int randomF=0;
			int randomS=0;
			while(randomS==0 && randomF==0 || (randomS==x-1 && randomF==y-1) || (randomS==y-1 && randomF==x-1)) {
				randomF = randomWithRange(0, x-1);
				randomS = randomWithRange(0, y-1);
			}
			this.blocks[i][0]=randomF;
			this.blocks[i][1]=randomS;
			
		}
		
		//creating maze
		for(int i =0 ;i<this.maze.length;i++) {
			for(int k=0;k< this.maze[i].length;k++) {
				
				for(int j =0 ;j<this.blocks.length;j++) {
					if(i==this.blocks[j][1] && k==this.blocks[j][0]) {
						this.maze[i][k]=-1;
					}
					
				}
				if(this.maze[i][k]!=-1) {
					if(i==this.maze.length-1 && k==this.maze[i].length-1) {
						this.maze[i][k]=1;
					}else {
						this.maze[i][k]=0;
					}
					
				}
			}
			
		}
		System.out.println("A matrix of "+x+" colums and "+y+" rows was created");

	}
	
	public static int randomWithRange(int min, int max){
		   int range = (max - min) + 1;     
		   return (int)(Math.random() * range) + min;
	}


	public void printMaze() {
		System.out.println("\n\n Start\n  |\n  v");
		for(int i =0 ;i<this.maze.length;i++) {
			for(int k=0;k< this.maze[i].length;k++) {
				if(this.maze[i][k]==-1) {
					System.out.print("[ × ] ");
				}else if(this.maze[i][k]<=9 && this.maze[i][k]>=0){
					System.out.print("[ "+this.maze[i][k]+" ] ");
					
				}else if(this.maze[i][k]>=10 && this.maze[i][k]<=99) {
					System.out.print("[ "+this.maze[i][k]+"] ");
					
				}else{
					System.out.print("["+this.maze[i][k]+"] ");
				}
			}
			if(i!=this.maze.length-1) {
				System.out.print("\n");
			}else {
				System.out.print("<--Finish");
			}
			
		}
		System.out.println("\n");
	}
	
	
	public void printBlocks() {
		System.out.println("-----------------------\nThe block coordinates are: ");
		for(int i =0 ;i<this.blocks.length;i++) {
			System.out.print("("+this.blocks[i][0]+", "+this.blocks[i][1]+")\t");
			
		}
		
	}

	public void findPaths() {
		
		for(int i=this.maze.length-1 ;i>=0;i--) {
			for(int k=this.maze[i].length-1;k>=0;k--) {
				int down=k+1;
				int right=i+1;
				
				if(this.maze[i][k]!=-1) {
					if(down>=0 && down<=this.maze[i].length-1) {
						if(this.maze[i][k+1]!=-1) {
							this.maze[i][k]+=this.maze[i][k+1];
							
						}
						
					}
					if(right>=0 && right<=this.maze.length-1) {
						if(this.maze[i+1][k]!=-1) {
							this.maze[i][k]+=this.maze[i+1][k];
						}
						
					}
				}
				
				
				
				
				
				
			}
			
			
		}
		System.out.print("\n");
		if(this.maze[0][0]!=0) {
			System.out.print("Solved:");
			this.res=this.maze[0][0];
			this.printMaze();
		}else {
			System.out.print("Imposible to solve");
			
		}
		
	}
	
	
	
	
	public int getRes() {
		return this.res;
	}
	
	public static void main(String[] args) {
		pathsChicken a=new pathsChicken(10,5);
		a.printBlocks();
		a.printMaze();
		a.findPaths();
		
		
		
	}

	public void setBlocks(int[][] blocks2) {
		for(int i =0 ;i<this.maze.length;i++) {
			for(int k=0;k< this.maze[i].length;k++) {
				this.maze[i][k]=0;
				for(int j =0 ;j<blocks2.length;j++) {
					if(i==blocks2[j][1] && k==blocks2[j][0]) {
						this.maze[i][k]=-1;
					}
					
				}
				if(this.maze[i][k]!=-1) {
					if(i==this.maze.length-1 && k==this.maze[i].length-1) {
						this.maze[i][k]=1;
					}else {
						this.maze[i][k]=0;
					}
					
				}
			}
			
		}
		System.out.println("A matrix of "+x+" colums and "+y+" rows was edited");

		
	}




}

