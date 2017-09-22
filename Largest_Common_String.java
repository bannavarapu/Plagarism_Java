/*
 * @author : Bhargav Annavarapu
 * @version : 1.0
 * @date : 18-09-2017*/
import java.io.File;
import java.io.IOException;

public class Largest_Common_String extends Common_Methods {
	// this class inherits the Common_Methods class to use some of the functions of that class
	// we have class variables to handle the operations
	// this gives plagiarism check using largest common string algorithm
	File f1,f2;
	StringBuilder s1,s2;
	Largest_Common_String(File f1,File f2)
	// a constructor to initialize the object of this class
	{
		 this.f1=f1;
		 this.f2=f2;
	}
	String main_LCS() throws IOException
	// we are throwing IO exception if something wrong happens while handling files
	// this is the main method and we invoke this from our main  (driver class)
	{
		s1=this.get_String(f1);
		s2=this.get_String(f2);
		String s3=compare_LCS(s1,s2);// we get the largest string 
		return (s3);
	}
	String compare_LCS(StringBuilder s1,StringBuilder s2)
	// this method gets 2 string builder objects as inputs, compares character by character
	// finally returns the percentage match in string format
	{
		int l1=s1.length()+1;
		int l2=s2.length()+1;
		int [][]matrix= new int[l1][l2];// we are forming a matrix of dimensions larger than sizes of objects
		int i=0;
		int j=0;
		double max=0;
		while(i<1)
		{
			for(j=0;j<l2;j++)
			{
				matrix[i][j]=0;
			}
			i++;		
		}
		while(j<1)
		{
			for(i=0;j<l1;i++)
			{
				matrix[i][j]=0;
			}
			j++;		
		}
		// we have filled the first row and first column with zeroes
		
		for(i=1;i<l1;i++)
		{
			for(j=1;j<l2;j++)
			{
				if(s1.charAt(i-1)==s2.charAt(j-1))
				{
					matrix[i][j]=matrix[i-1][j-1]+1;
					if(matrix[i][j]>max)
					{
						max=matrix[i][j];
					}
				}
			}
		}
		// in the above code we compare 2 characters, if they are same we add the diagonal element from 
		// matrix and store it in that cell
		max=((2*max)/(l1+l2-2))*100;
		String value=max+"";
		return value;// we are returning a string value to make it easy for printing
	}
}


	


