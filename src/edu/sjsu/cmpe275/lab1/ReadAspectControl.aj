package edu.sjsu.cmpe275.lab1;

import edu.sjsu.cmpe275.lab1.Application.UnauthorizedException;

public aspect ReadAspectControl {

	pointcut readAccessControl(String userId, String filePath) :
		execution(public byte[] readFile(String, String)) && 
		args(userId, filePath);
	
	byte[] around (String userId, String filePath) throws UnauthorizedException : (readAccessControl(userId, filePath)) {
		
		try {
			Application app = new Application();
			int res = app.readAccessControl(userId, filePath);
			if (res == 0) {
				System.out.println(userId + " reads the file " + filePath);
				proceed(userId, filePath);
			}	
		} catch (UnauthorizedException e) {
			System.out.println("Unauthorized read access control");
			e.printStackTrace();
			throw e;
		}
		
		byte [] data = null;
		return data;
	}
}
