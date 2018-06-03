

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;



public class Method {
	
	public static int ngram(String str1, String str2) {
		int distance = 0;
		ArrayList<String> sub1 = new ArrayList();
		ArrayList<String> sub2 = new ArrayList();
		String ms;
		int count = 0;
		
		// #c
		String first1 = "#"+str1.charAt(0);
		String last1 = str1.charAt(str1.length()-1) + "#"; // t#
		sub1.add(first1);
		
		String first2 = "#"+str2.charAt(0);
		String last2 = str2.charAt(str2.length()-1) + "#";
		sub2.add(first2);
		
		if(str1.length() > 1) {
			for(int i=1; i<str1.length(); i++) {
				
				String temp = ""+str1.charAt(i-1) + str1.charAt(i);
				sub1.add(temp);
				
			}
			sub1.add(last1);
			
		}
		
		
		
		if(str2.length() > 1) {
			for(int i=1; i<str2.length(); i++) {
				
				String temp = ""+str2.charAt(i-1) + str2.charAt(i);
				sub2.add(temp);
				
			}
			sub2.add(last2);
			
		}
		
		for(int i = 0; i < sub1.size(); i++){
	         for(int j = 0; j < sub2.size(); j++){
	        	 
	        if(sub2.get(j).equals(sub1.get(i))){
	            ms = sub1.get(i);

	            count = count +1;
	            
	        }
	      }
	  }
		
		distance = sub1.size() + sub2.size() - 2*count;
		
		if(distance < 0) {
			distance = 0;
		}
		
		return distance;
	}
	
	
	public static float getLongestCommonSubstring(String a, String b){
		int m = a.length();
		int n = b.length();
		float avg = (m+n)/2f;
	 
		int max = 0;
	 
		int[][] dp = new int[m][n];
	 
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				if(a.charAt(i) == b.charAt(j)){
					if(i==0 || j==0){
						dp[i][j]=1;
					}else{
						dp[i][j] = dp[i-1][j-1]+1;
					}
	 
					if(max < dp[i][j])
						max = dp[i][j];
				}
	 
			}
		}
		
		float value = max/avg;
	 
		return value;
	}
	
	public static int editDistance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
	 
		// len1+1, len2+1, because finally return dp[len1][len2]
		int[][] dp = new int[len1 + 1][len2 + 1];
	 
		for (int i = 0; i <= len1; i++) {
			dp[i][0] = i;
		}
	 
		for (int j = 0; j <= len2; j++) {
			dp[0][j] = j;
		}
	 
		//iterate though, and check last char
		for (int i = 0; i < len1; i++) {
			char c1 = word1.charAt(i);
			for (int j = 0; j < len2; j++) {
				char c2 = word2.charAt(j);
	 
				//if last two chars equal
				if (c1 == c2) {
					//update dp value for +1 length
					dp[i + 1][j + 1] = dp[i][j];
				} else {
					int replace = dp[i][j] + 1;
					int insert = dp[i][j + 1] + 1;
					int delete = dp[i + 1][j] + 1;
	 
					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					dp[i + 1][j + 1] = min;
				}
			}
		}
	 
		return dp[len1][len2];
	}
	
	public static float getLongestCommonSubsequence(String a, String b){
		int m = a.length();
		int n = b.length();
		
		float avg = (m+n)/2f;
		
		int[][] dp = new int[m+1][n+1];
	 
		for(int i=0; i<=m; i++){
			for(int j=0; j<=n; j++){
				if(i==0 || j==0){
					dp[i][j]=0;
				}else if(a.charAt(i-1)==b.charAt(j-1)){
					dp[i][j] = 1 + dp[i-1][j-1];
				}else{
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		float value = dp[m][n]/avg;
	 
		return value;
	}
	
	public static void main(String args[]) throws IOException { 
		
		
    	
    	String str;
    	String[] data;
    	
    	int distance_ngram;
    	int distance_editD;
		float distance_lcs;
		float distance_lcss;
    	
    	try {
    		
    		//FileReader fr = new FileReader("positive_namepairs_87637.csv");
    		FileReader fr = new FileReader("negative_namepairs_350550.csv");
    		Scanner inFile = new Scanner(fr);
    		PrintWriter writer = new PrintWriter("feature_four_negative.txt", "UTF-8");
          
            	
            	
            	while(inFile.hasNextLine()) {
            	
            		str = inFile.nextLine().toUpperCase();
            		data = str.split(Pattern.quote(","));
            		
            		
            		if(!data[0].equals("") && !data[1].equals("")) {
            			
            			distance_ngram = ngram( data[0] , data[1]);
            			distance_lcss = getLongestCommonSubsequence(data[0] , data[1]);
            			distance_lcs = getLongestCommonSubstring(data[0], data[1]);
            			distance_editD = editDistance(data[0], data[1]);
            			
            			//writer.println(distance_ngram + "," + distance_editD+ "," + distance_lcs+ "," + distance_lcss + "," + "positive");
            			writer.println(distance_ngram + "," + distance_editD+ "," + distance_lcs+ "," + distance_lcss + "," + "negative");
            			
            			
                		
                		
            			
            		}
            		
            	
            		
            	}
            	
            	inFile.close();
            	writer.close();  
            	
            	
            
            
    	}
    	catch(FileNotFoundException f)
        {
            throw new FileNotFoundException("The file is not found!");
        }
        catch(IOException i)
        {
            throw new IOException("Oops!");
            
        } 
		
    
		
	}

}
