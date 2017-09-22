/*
 * @author : Bhargav Annavarapu
 * @version : 1.0
 * @date : 19-09-2017*/
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Finger_Printing extends Common_Methods {
	// we are throwing IO exception if something wrong happens while handling files
	// this is the main method and we invoke this from our main (driver class)
	// this gives plagiarism check using finger printing algorithm
	File f1,f2;
	ArrayList<String> al1=new ArrayList<String>();
	ArrayList<String> al2=new ArrayList<String>();
	public Finger_Printing(File f1,File f2) 
	{
		this.f1=f1;
		this.f2=f2;
	}
	boolean isprime(long i) // this function gets a number as input and returns true if the number is prime else false hence the return tytpe is boolean
	{
		long j=3;
		for(j=3;j<(long)Math.sqrt(i);j++)
		{
			if(i%j==0)
			{
				return false;
			}
		}
		return true;
	}
	long gen_prime(long i)// this number gets a number as input and returns a prime number greater or equal to that number
	// we are making use of long to increase our range
	{
		while(true)
		{
			if(isprime(i))
			{
				return(i);
			}
			i++;
		}
	}
	
	ArrayList<Long> gen_hashmod(StringBuilder s,long prime)
	// this function is used to generate an arraylist(l) consisting of all hashmod values for corresponding k gram strings generated
	// we are making use of hashCode function to generate the hash value
	{
		String dup=s.toString();
		ArrayList<Long>l=new ArrayList<Long>();
		for(int i=0;i<dup.length()-4;i++)
		{
			String temp=dup.substring(i,i+4);
			l.add(temp.hashCode()/prime);	
		}
		return (l);
	}
	public String main_fp()throws IOException
	{
		al1=this.get_Words(f1);// we are getting all the words present in a file to array list using get_words function from Common_Methods class inherited
		al2=this.get_Words(f2);
		File sw=new File("C:\\Users\\barghav\\Desktop\\Plagarism_java\\Plagiarism\\src\\stop_words.txt");
		ArrayList<String> stop_words=new ArrayList<String>();
		stop_words=this.get_Words(sw);

		for(int i=0;i<al1.size();i++)// we are checking if the given word is present in stop word list, if present we are making it null
		{
			if(stop_words.contains(al1.get(i)))
			{
				al1.set(i,"");
			}
		}
		for(int i=0;i<al2.size();i++)
		{
			if(stop_words.contains(al2.get(i)))
			{
				al2.set(i,"");
			}
		}
		

		StringBuilder l1=this.get_String(al1);
		StringBuilder l2=this.get_String(al2);
		if(l1.length()<5 || l2.length()<5)
		{
			return ("Nan");
		}
		else
		{
			long max_length=l1.length()>l2.length()?l1.length():l2.length();
			max_length=max_length*100;
			long prime=gen_prime(max_length);
			ArrayList<Long>hash1=this.gen_hashmod(l1, prime);
			ArrayList<Long>hash2=this.gen_hashmod(l2, prime);
			long count=0;
			
			for(int i=0;i<hash1.size();i++) // we are checking the hashmod values generated from both the files, if we find a similar value we make it zero to avoid duplication of values
			{
				for(int j=0;j<hash2.size();j++)
				if(hash1.get(i).equals(hash2.get(j)))
				{
					if(!(hash1.get(i).equals(0)))
					{
						count+=2;
						hash1.set(i,(long)0);
						hash2.set(j,(long)0);
					}				
				}
			}
			double res=(double)count/(double)((hash1.size()+hash2.size()))*100;
			String result=res+"";
			return(result);// we are returning a string value to make it easy for printing
		}
		
		
	}
	
}
