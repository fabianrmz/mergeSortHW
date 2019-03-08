
public class pathsChicken {
	
	
	private int[][] blocks;
	private int[][] maze;
	private int x;
	private int y;
	
	
	


	public pathsChicken(int x, int y){
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
		
		
		
		
		
		
		
	}
	
	public static int randomWithRange(int min, int max){
		   int range = (max - min) + 1;     
		   return (int)(Math.random() * range) + min;
	}


	private void printMaze() {
		System.out.print("\n");
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
			System.out.print("\n");
			
		}
		System.out.print("\n");
		
		
		
	}
	
	
	private void printBlocks() {
		System.out.println("The block coordinates are: ");
		for(int i =0 ;i<this.blocks.length;i++) {
			System.out.println(this.blocks[i][0]+", "+this.blocks[i][1]);
			
		}
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		pathsChicken a=new pathsChicken(5,8);
		a.printBlocks();
		a.printMaze();
		a.findPaths();
		
		
		
	}

	private void findPaths() {
		
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
				
				if(this.maze[i][k]==-1) {
					System.out.println("block coordinates ("+i+","+k+")");
				}else {
					System.out.println(this.maze[i][k]+" coordinates ("+i+","+k+")");
				}
				
				
				
				
				
			}
			
			
		}
		System.out.print("\n");
		System.out.print("Solved:");
		this.printMaze();
	}




}
