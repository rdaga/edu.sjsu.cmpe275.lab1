package edu.sjsu.cmpe275.lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Application {
	
	String userId, targetUserId, filePath;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	FileService fs = new FileService();
	
	static public void main (String[] args) throws IOException {
		 
		String operation, continueProgram;
		@SuppressWarnings("unused")
		byte[] data;
		Application app = new Application();
		
		do {
			
			System.out.println("Enter the operation (Read(r)/Share(s)/Unshare(u)):");
	        operation = app.br.readLine().toLowerCase();
	        
	        switch (operation) {
	        case "s": 
	        	app.displayQuestions(operation);
	        	app.fs.shareFile(app.userId, app.targetUserId, app.filePath); 
	        	break;
	        	
	        case "u":
	        	app.displayQuestions(operation);
	        	app.fs.unshareFile(app.userId, app.targetUserId, app.filePath); 
	        	break;
	        	
	        case "r":
	        	app.displayQuestions(operation);
	        	data = app.fs.readFile(app.userId, app.filePath); 
	        	break;
	        	
	        default:
				System.out.println("Invalid operation");
	        }
	        
	        System.out.println("Do you want to continue (y/n):");
	        continueProgram = app.br.readLine();
	        
		} while (continueProgram.equalsIgnoreCase("y"));
	}

	public void displayQuestions(String operation) throws IOException {
		
		System.out.println("Enter your user id:");
		userId = br.readLine();
		
		if (operation.equalsIgnoreCase("s") || operation.equalsIgnoreCase("u")) {
			System.out.println("Enter the target id:");
			targetUserId = br.readLine();
		}
		
		System.out.println("Enter the file path:");
		filePath = br.readLine();
		
	}

	@SuppressWarnings("static-access")
	public int shareAccessControl(String userId, String targetUserId, String filePath){
		
		if (fs.map.containsKey(filePath)) {
			Set<String> accessList = fs.map.get(filePath);
			if (accessList.contains(userId)) {
				accessList.add(targetUserId);
				//System.out.println("File shared successfully with " + targetUserId);
				return 0;
			} else {
				String errMsg = userId + " doesn't have access to share this file";
				throw new UnauthorizedException(errMsg);
			}
		
		} else {
			String[] parts = filePath.split("/");
    		if (userId.equalsIgnoreCase(parts[5])) {
				Set<String> accessList = new HashSet<String>();
				accessList.add(userId);
				accessList.add(targetUserId);
				fs.map.put(filePath, accessList);
				//System.out.println(userId + " and " + targetUserId + " added successfully to access list");
				return 0;
			} else {
				String errMsg = userId + " doesn't have access to share this file";
				throw new UnauthorizedException(errMsg);
			}
		}
	}
	
	public int unshareAccessControl(String userId, String targetUserId, String filePath) {
		String[] parts = filePath.split("/");
		if (!userId.equalsIgnoreCase(parts[5])) {
			String errMsg = userId + " doesn't have access to unshare this file";
			throw new UnauthorizedException(errMsg);
		} else {
			//System.out.println(userId + " unshared the file " + filePath + " for " + targetUserId);
			return 0;
		}
	}
	
	@SuppressWarnings("static-access")
	public int readAccessControl(String userId, String filePath) {
		
		if (fs.map.containsKey(filePath)){
			Set<String> list = fs.map.get(filePath);
			if (list.contains(userId)) {
				//System.out.println("Read the contents of the file successfully");
				return 0;
			} else {
				String errMsg = userId + " doesn't have access to read this file";
				throw new UnauthorizedException(errMsg);
			}
		}
    	else {
    		String[] parts = filePath.split("/");
    		if (userId.equalsIgnoreCase(parts[5])) {
				//System.out.println("Read the contents of the file successfully");
				return 0;
    		} else {
    			String errMsg = userId + " doesn't have access to read this file";
    			throw new UnauthorizedException(errMsg);
    		}
		}
	}
	
	public class UnauthorizedException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public UnauthorizedException(String arg0) {
        	//super(arg0);
		}

	}
}
