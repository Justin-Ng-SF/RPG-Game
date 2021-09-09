package tilegame.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {//contains helper functions to help load game

	public static String loadFileAsString(String path) {
		//allows to add characters to string
		StringBuilder builder = new StringBuilder();
		
		try {
			//need for errors
			BufferedReader br = new BufferedReader(new FileReader(path));//reads in file
			//line we are currently working on
			String line;
			
			//reads next line of file as long as it exists
			while((line = br.readLine()) != null) 
			//everytime after reading a line, go to next line
				builder.append(line + "\n");
				
				
				br.close();
			
			
			
		}catch(IOException e) {
			//prints error to system
			e.printStackTrace();
		}
		
		
		
		
		//returns file we just loaded, converting everything we appended to builder object into a string
		return builder.toString();
	}
	
	public static int parseInt(String number) {
		
		
		try {
			return Integer.parseInt(number);
						
		}catch(NumberFormatException e) {
			//if you try passing a string that is not a number, print error to screen
			e.printStackTrace();
			return 0;
		}
		
		
		
	}
	
	
	
	
}
