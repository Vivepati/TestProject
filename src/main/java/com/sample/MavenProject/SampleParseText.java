package com.sample.MavenProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SampleParseText {
	
	public static File fInputFIle = new File("test.txt");

	public static void main(String[] args) {

		returnKeyValue(fInputFIle);       
    }
	
	public static String returnKeyValue(File fFile) {
		
		BufferedReader br = null;
        String st;
        boolean bSZ=false;
        int iSZCount = 0;
        
		try {
			br = new BufferedReader(new FileReader(fInputFIle));
			while ((st = br.readLine()) != null) {				
				if ((st.contains("SZ")) && (bSZ==false)){
					bSZ= true;
					iSZCount++;
				}else if( st.contains("]]") ) {
					st = st.replace("]]", "]] ");
					StringTokenizer stkz = new StringTokenizer(st);
					while( stkz.hasMoreTokens()){
						String sToken = stkz.nextToken();
						if(sToken.contains("]]")) {
							bSZ=false;
							
						}else if((st.contains("SZ")) && (bSZ==false)){
							bSZ= true;
							iSZCount++;
						}						
					}
				}else if(st.contains("record")) {
					continue;
				}else {
					System.out.println(st);
				}	
				
			}
			    //System.out.println(st);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();
		}
		
		return "";
	}
	
}
