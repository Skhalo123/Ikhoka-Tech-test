package com.ikhokha.techcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommentAnalyzer {
	
	public static int shorterThan15 = 0;
	public static int mover = 0;
	public static int shaker = 0;
	
	private File file;
	public CommentAnalyzer(File file) {
		this.file = file;
	}
	
	public Map<String, Integer> analyze() {
		
	
			Map<String, Integer> resultsMap = new HashMap<>();	
		
	
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			String line = null;
			while ((line = reader.readLine()) != null) {
				
				if (line.length() < 15) {
					shorterThan15++;
					incOccurrence(resultsMap, "SHORTER_THAN_15");
					
				}

				 if (line.toLowerCase().contains("mover")) {
					 mover++;
					incOccurrence(resultsMap, "MOVER_MENTIONS");
					
				 }
				
				if (line.toLowerCase().contains("shaker")) {
					shaker++;
					incOccurrence(resultsMap, "SHAKER_MENTIONS");
					
				
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file.getAbsolutePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Error processing file: " + file.getAbsolutePath());
			e.printStackTrace();
		}
		
		return resultsMap;
		
	}
	
	/**
	 * This method increments a counter by 1 for a match type on the countMap. Uninitialized keys will be set to 1
	 * @param countMap the map that keeps track of counts
	 * @param key the key for the value to increment
	 */
	private void incOccurrence(Map<String, Integer> countMap, String key) {
		
		countMap.putIfAbsent(key, 0);
		
		if(key.contains("SHORTER_THAN_15")) {
			
			countMap.put(key,shorterThan15);
				
		}else if(key.contains("MOVER_MENTIONS")){
			
			countMap.put(key,mover);		
					
		} else if(key.contains("SHAKER_MENTIONS")) {
		
			countMap.put(key,shaker);		
			
	 }
	

		
	}

}
