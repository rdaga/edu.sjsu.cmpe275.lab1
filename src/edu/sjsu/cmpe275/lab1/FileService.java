package edu.sjsu.cmpe275.lab1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class FileService implements IFileService {
	
	public static Map<String, Set<String>> map = new HashMap<String, Set<String>>();
	
	public void shareFile(String userId, String targetUserID, String filePath) {
		Set<String> list = map.get(filePath);
		list.add(targetUserID);
	}
	
	public void unshareFile(String userId, String targetUserID, String filePath) {
		Set<String> list = map.get(filePath);
		list.remove(targetUserID);
	}

	public byte[] readFile(String userId, String filePath) {
		Path path = Paths.get(filePath);
		byte[] data;
		try {
			data = Files.readAllBytes(path);
			return data;
		} catch (IOException e) {
			System.out.println("Error in reading the file");
			e.printStackTrace();
			return null;
		}
	}	
}