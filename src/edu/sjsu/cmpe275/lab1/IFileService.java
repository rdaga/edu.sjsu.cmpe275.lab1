package edu.sjsu.cmpe275.lab1;

public interface IFileService {
	void shareFile(String userId, String targetUserID, String filePath);
	void unshareFile(String userId, String targetUserID, String filePath);
	byte[] readFile(String userId, String filePath);
}
