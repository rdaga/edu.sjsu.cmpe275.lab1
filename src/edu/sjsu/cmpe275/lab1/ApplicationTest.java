package edu.sjsu.cmpe275.lab1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import edu.sjsu.cmpe275.lab1.Application.UnauthorizedException;

public class ApplicationTest {

	String home = "Users/Elysian/Desktop/275-Chang";
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test(expected = UnauthorizedException.class)
	public void testA() {
    	System.out.println("testA");
    	String filePath = "/" + home + "/Alice/shared/alicefile1.txt";
    	Application app = new Application();
    	
    	app.userId = "Bob";
    	app.filePath = filePath;
    	app.fs.readFile("Bob", filePath);
	}
	
	@Test
	public void testB() {
    	System.out.println("testB");
    	String filePath = "/" + home + "/Alice/shared/alicefile1.txt";
    	Application app = new Application();
    	
    	app.userId = "Alice";
    	app.targetUserId = "Bob";
    	app.filePath = filePath;
    	app.fs.shareFile("Alice", "Bob", filePath);
    	
    	app.userId = "Bob";
    	app.targetUserId = "";
    	app.filePath = filePath;
    	app.fs.readFile("Bob", filePath);
	}
	
	@Test
	public void testC() {
    	System.out.println("testC");
    	String filePath = "/" + home + "/Alice/shared/alicefile1.txt";
    	Application app = new Application();
    	
    	app.userId = "Alice";
    	app.targetUserId = "Bob";
    	app.filePath = filePath;
    	app.fs.shareFile("Alice", "Bob", filePath);
    	
    	app.userId = "Bob";
    	app.targetUserId = "";
    	app.filePath = filePath;
    	app.fs.readFile("Bob", filePath);
    	
    	app.userId = "Bob";
    	app.targetUserId = "Carl";
    	app.filePath = filePath;
    	app.fs.shareFile("Bob", "Carl", filePath);
    	
    	app.userId = "Carl";
    	app.targetUserId = "";
    	app.filePath = filePath;
    	app.fs.readFile("Carl", filePath);
	}
	
	@Test(expected = UnauthorizedException.class)
	public void testD() {
    	System.out.println("testD");
    	String filePath1 = "/" + home + "/Alice/shared/alicefile1.txt";
    	String filePath2 = "/" + home + "/Carl/shared/carlfile1.txt";
    	Application app = new Application();
    	
    	app.userId = "Alice";
    	app.targetUserId = "Bob";
    	app.filePath = filePath1;
    	app.fs.shareFile("Alice", "Bob", filePath1);
    	
    	app.userId = "Bob";
    	app.targetUserId = "Alice";
    	app.filePath = filePath2;
    	app.fs.shareFile("Bob", "Alice", filePath2);
	}
	
	@Test(expected = UnauthorizedException.class)
	public void testE() {
    	System.out.println("testE");
    	String filePath = "/" + home + "/Alice/shared/alicefile1.txt";
    	Application app = new Application();
    	
    	app.userId = "Alice";
    	app.targetUserId = "Bob";
    	app.filePath = filePath;
    	app.fs.shareFile("Alice", "Bob", filePath);
    	
    	app.userId = "Bob";
    	app.targetUserId = "Carl";
    	app.filePath = filePath;
    	app.fs.shareFile("Bob", "Carl", filePath);
    	
    	app.userId = "Alice";
    	app.targetUserId = "Carl";
    	app.filePath = filePath;
    	app.fs.unshareFile("Alice", "Carl", filePath);
    	
    	app.userId = "Carl";
    	app.targetUserId = "";
    	app.filePath = filePath;
    	app.fs.readFile("Carl", filePath);
	}
	
	@Test(expected = UnauthorizedException.class)
	public void testF() {
    	System.out.println("testF");
    	String filePath = "/" + home + "/Alice/shared/alicefile1.txt";
    	Application app = new Application();
    	
    	app.userId = "Alice";
    	app.targetUserId = "Bob";
    	app.filePath = filePath;
    	app.fs.shareFile("Alice", "Bob", filePath);
    	
    	app.userId = "Alice";
    	app.targetUserId = "Carl";
    	app.filePath = filePath;
    	app.fs.shareFile("Alice", "Carl", filePath);
    	
    	app.userId = "Carl";
    	app.targetUserId = "Bob";
    	app.filePath = filePath;
    	app.fs.shareFile("Carl", "Bob", filePath);
    	
    	app.userId = "Alice";
    	app.targetUserId = "Bob";
    	app.filePath = filePath;
    	app.fs.unshareFile("Alice", "Bob", filePath);
    	
    	app.userId = "Bob";
    	app.targetUserId = "";
    	app.filePath = filePath;
    	app.fs.readFile("Bob", filePath);
	}
	
	@Test
	public void testG() {
    	System.out.println("testG");
    	String filePath = "/" + home + "/Alice/shared/alicefile1.txt";
    	Application app = new Application();
    	
    	app.userId = "Alice";
    	app.targetUserId = "Bob";
    	app.filePath = filePath;
    	app.fs.shareFile("Alice", "Bob", filePath);
    	
    	app.userId = "Bob";
    	app.targetUserId = "Carl";
    	app.filePath = filePath;
    	app.fs.shareFile("Bob", "Carl", filePath);
    	
    	app.userId = "Alice";
    	app.targetUserId = "Bob";
    	app.filePath = filePath;
    	app.fs.unshareFile("Alice", "Bob", filePath);
    	
    	app.userId = "Bob";
    	app.targetUserId = "Carl";
    	app.filePath = filePath;
    	exception.expect(UnauthorizedException.class);
    	app.fs.shareFile("Bob", "Carl", filePath);
    	
    	app.userId = "Carl";
    	app.targetUserId = "";
    	app.filePath = filePath;
    	app.fs.readFile("Carl", filePath);
	}
	
	@Test(expected = UnauthorizedException.class)
	public void testH() {
    	System.out.println("testH");
    	String filePath1 = "/" + home + "/Alice/shared/alicefile1.txt";
    	String filePath2 = "/" + home + "/Alice/shared/alicefile2.txt";
    	Application app = new Application();
    	
    	app.userId = "Alice";
    	app.targetUserId = "Bob";
    	app.filePath = filePath1;
    	app.fs.shareFile("Alice", "Bob", filePath1);
    	
    	app.userId = "Bob";
    	app.targetUserId = "";
    	app.filePath = filePath2;
    	app.fs.readFile("Bob", filePath2);
	}
}
