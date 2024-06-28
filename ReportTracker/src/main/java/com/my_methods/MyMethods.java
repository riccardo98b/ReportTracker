package com.my_methods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyMethods {

	public static List<String> lettura(String file)throws IOException{
		FileReader r = new FileReader(file);
		BufferedReader reader = new BufferedReader(r);

		String riga = reader.readLine();
		riga = reader.readLine();
		
		List<String> listFile = new ArrayList<String>();
		while(riga != null) {
			listFile.add(riga);
			riga = reader.readLine();
		}
			reader.close();
			return listFile;
	}
	
}
