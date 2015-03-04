package edu.sjsu.cmpe275.lab1;

import edu.sjsu.cmpe275.lab1.Application.UnauthorizedException;

public aspect ShareAccessControl {
	
	pointcut shareAccessControl(String userId, String targetUserId, String filePath) :
		execution(public void FileService.shareFile(String, String, String)) && 
		args(userId, targetUserId, filePath);
	
	void around (String userId, String targetUserId, String filePath) :
		(shareAccessControl(userId, targetUserId, filePath)) {
		
		try {
			Application app = new Application();
			int res = app.shareAccessControl(userId, targetUserId, filePath);
			if (res == 0) {
				System.out.println(userId + " shares the file " + filePath + " with " + targetUserId);
				proceed(userId, targetUserId, filePath);
			}
		} catch (UnauthorizedException e) {
			System.out.println("Unauthorized share access control");
			e.printStackTrace();
			throw e;
		}
		
	}
}
