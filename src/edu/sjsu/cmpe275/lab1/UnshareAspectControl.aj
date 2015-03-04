package edu.sjsu.cmpe275.lab1;

import edu.sjsu.cmpe275.lab1.Application.UnauthorizedException;

public aspect UnshareAspectControl {
	
	pointcut unshareAccessControl(String userId, String targetUserId, String filePath) :
		execution(public void FileService.unshareFile(String, String, String)) && 
		args(userId, targetUserId, filePath);
	
	void around (String userId, String targetUserId, String filePath) :
		(unshareAccessControl(userId, targetUserId, filePath)) {
		
		try {
			Application app = new Application();
			int res = app.unshareAccessControl(userId, targetUserId, filePath);
			if (res == 0) {
				System.out.println(userId + " unshares the file " + filePath + " with " + targetUserId);
				proceed(userId, targetUserId, filePath);
			}
		} catch (UnauthorizedException e) {
			System.out.println("Unauthorized unshare access control");
			e.printStackTrace();
			throw e;
		}			
	}
}
