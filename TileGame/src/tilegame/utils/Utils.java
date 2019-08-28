package tilegame.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {//contains helper functions to help load game

	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();//allows to add characters to string
		
		try {//need for errors
			BufferedReader br = new BufferedReader(new FileReader(path));//reads in file
			String line;//line we are currently working on
			
			while((line = br.readLine()) != null) //reads next line of file as long as it exists
				builder.append(line + "\n");//everytime after reading a line, go to next line
				
				br.close();//closes file screen
			
			
			
		}catch(IOException e) {
			e.printStackTrace();//prints error to system
		}
		
		
		
		
		
		return builder.toString();//returns file we just loaded, converting everything we appended to builder object into a string
	}
	
	public static int parseInt(String number) {
		
		
		try {
			return Integer.parseInt(number);
						
		}catch(NumberFormatException e) {
			e.printStackTrace();//if you try passing a string that is not a number, print error to screen
			return 0;
		}
		
		
		
	}
	
	
	
	
}
