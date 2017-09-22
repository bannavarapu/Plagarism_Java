/*
 * @author : Bhargav Annavarapu
 * @version : 1.0
 * @date : 18-09-2017*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Common_Methods {
	// this class contains all the common methods needed for implementing other methods of algorithms

	public ArrayList<String> get_Words(File f) throws IOException
	// this method gets a file object as input, process that file and returns all the words
	// we return an array list of strings that contains all the words of that file
	// this even removes special characters in the files and turns everything into lower case
	// this is used in bag of words
	{
		BufferedReader b=new BufferedReader(new FileReader(f));
		String temp= new String();
		ArrayList<String> al=new ArrayList<String>();
		while((temp=b.readLine())!=null)
		{
			String words[]=temp.split(" ");
			
			for(String x :words) 
			{
				StringBuilder dummy=new StringBuilder();
				x.toLowerCase();
				for(Character c:x.toCharArray() )
				{
					if((c>='A'&& c<='Z')||(c>='a'&& c<='z')||(c>='0'&& c<='9'))
					{
//						
						dummy.append(c);
					}
				}
				if(dummy.length()>0)
				{
					x= dummy.toString();
					al.add(x);
				}
//					dummy.toString();
				
			}			
		}
		b.close();
		return (al);		
	}
	public StringBuilder get_String(File f) throws IOException
	// this method gets a file object as input, process that and returns a string that contains all the required characters 
	// we return a string builder because string of type string builder is easy to manipulate
	// this even removes special characters in the files and turns everything into lower case
	// this is used in LCS
	{
		FileInputStream input=new FileInputStream(f);
		StringBuilder common=new StringBuilder();
		char c;
		int i;
		
		while((i=input.read())!=-1)
		{
			c=(char)i;
			if((c>='A'&& c<='Z')||(c>='a'&& c<='z')||(c>='0'&& c<='9'))
			{
				
				common.append(c);
			}
		}
		input.close();
		return (common);
	}
	public StringBuilder get_String(ArrayList<String>al)
	// this method gets an array list of words, then returns a string of all these words
	//we return a string builder because string of type string builder is easy to manipulate
	// we are overloading this method as the end required result is same but the arguments are different
	// we use this in finger printing
	{
		StringBuilder largestring=new StringBuilder();
		for(String x:al)
		{
			largestring.append(x);
		}
		return largestring;
	}
	
	public HashMap<String, Integer> get_frequency(ArrayList<String>al )
	// this method gets an array list of all the words present in a file, after removing unnecessary characters and words
	// it returns a hashmap that has a word as key and its frequency as value
	// we are using hashmap as return type because we can handle both the words and frequency at the same time
	{
		HashMap<String, Integer>h=new HashMap<String, Integer>();
		for (String x:al)
		{
			int freq=0;
			for(String y:al)
			{
				if(x.equals(y))
				{
					freq++;
				}
			}
			h.put(x, freq);
		}
		return (h);
	}
	
}
