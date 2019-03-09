import static org.junit.Assert.assertEquals;
import org.junit.*; 

public class pathsChickenTest {
	
	@Test
	public void testPathsChicken() {
		
		pathsChicken a=new pathsChicken(5,10);
		int blocks[][]={{1, 0},{3, 1},{1, 4},{2, 5},{2, 2},{4, 0},{2,7}};
		a.setBlocks(blocks);
		a.findPaths();
		assertEquals( a.getRes(),64);
		
		
	}

	
	@Test
	public void testPathsChicken2() {
		pathsChicken a2=new pathsChicken(10,5);
		int blocks3[][]={{0, 1},{9, 2},{7, 0},{7,3},{2,0}};
		a2.setBlocks(blocks3);
		a2.findPaths();
		assertEquals( a2.getRes(),72);
		
	}
	
	
	@Test
	public void testPathsChicken3() {
		pathsChicken a1=new pathsChicken(5,5);
		int blocks2[][]={{4, 1},{3, 3},{2, 1},{4,1}};
		a1.setBlocks(blocks2);
		a1.findPaths();
		assertEquals( a1.getRes(),16);
		
	}
	
	
	
	

}
