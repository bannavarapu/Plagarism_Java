/*
 * @author : Bhargav Annavarapu
 * @version : 1.0
 * @date : 18-09-2017*/
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Bag_Of_Words extends Common_Methods {
// this class inherits the Common_Methods class to use some of the functions of that class
// we have class variables to handle the operations
	File f1,f2;
	ArrayList<String>al1=new ArrayList<String>();
	ArrayList<String>al2=new ArrayList<String>();
	
	public Bag_Of_Words(File f1,File f2) {
		// this is a constructor to initialize class object 
		this.f1=f1;
		this.f2=f2;
	}
	String main_BOW() throws IOException
	// we are throwing IO exception if something wrong happens while handling files
	// this is the main method and we invoke this from our main (driver class)
	// this gives plagiarism check using bag of words algorithm
	{
		al1=this.get_Words(f1);
		al2=this.get_Words(f2);
		HashMap<String, Integer>h1=this.get_frequency(al1);// we are using hashmap to get the words and the frequency of that word
		HashMap<String, Integer>h2=this.get_frequency(al2);// we make use of get_frequency method from common_methods class that is inherited
		double num1=0,num2=0;
		double den=0;
		for(String x:h1.keySet())
		{
			num1+=h1.get(x)*h1.get(x);
		}
		for(String x:h2.keySet())
		{
			num2+=h2.get(x)*h2.get(x);
		}
		den=(Math.sqrt(num1))*(Math.sqrt(num2));
		double n=0;
		for(String x:h1.keySet())
		{
			for(String y:h2.keySet())
			{
				if(x.equals(y))
				{
					n+=h1.get(x)*h2.get(y);// we multiplying the matched frequencies based upon key values and adding them to n
				}
			}
		}
		String res=((n/den)*100)+"";
		return (res);// we are returning a string value to make it easy for printing
	}
}
