package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class SourceTarget {
	
	public static void outputMessage(String s, PrintStream out){
		out.println(s);
	}
	
	public static void mainFunction(String file) throws IOException{
		StringBuffer strbuf = new StringBuffer();
		BufferedReader in = new BufferedReader(new FileReader(file));
		String str;
		
		while ((str = in.readLine()) != null){
			strbuf.append(str + '\n');
		}
		
		if (strbuf.length() > 0){
			outputMessage(strbuf.toString(), System.out);
		}
		in.close();
	}
}
