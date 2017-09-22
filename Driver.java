/*
 * @author : Bhargav Annavarapu
 * @version : 1.0
 * @date : 20-09-2017*/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.sql.Timestamp;

public class Driver {
	/*
	 * this is our main class which includes the main function
	 * we invoke other function objects here handle them 
	 */

	public static void main(String[] args)throws IOException 
	/*
	 * this is main function which accepts the directory and checks for plagiarism in the .txt files in that directory 
	 */
	{
		try// to handle the exceptions
		{
			Date date=new Date();// to get date functionality in our program, we are importing date class
			long d=date.getTime();// getTime() returns long value, hence we are using long
			String logfile= "C:\\Users\\barghav\\Desktop\\Plagarism_java\\Plagiarism\\src\\logfile.txt";// this is the path in which we are storing our logs
			FileWriter fw= new FileWriter(logfile,true);// File writer is used to write data into file, true is used to append the data 
			BufferedWriter bw= new BufferedWriter(fw);// buffered writer is used to buffer the data and then write it into file using file writer
			System.out.println("Enter directory: ");				
			Scanner sc= new Scanner(System.in);// taking the directory path from user
			String path=sc.nextLine();
			sc.close();
			File dir=new File(path);		
			ArrayList<File> files=new ArrayList<File>();// an array list used to store all the files ending with .txt in the given directory
			for(File f:dir.listFiles())
			{
				if(f.getName().endsWith(".txt"))
				{
					if((!(f.getName().equals("stop_words.txt")))&&(!(f.getName().equals("logfile.txt"))))// we are eliminating logfile and stop words files from adding into file array list
					{
						files.add(f);
					}
				}
			}
			if(files.size()<=1)// checking the number of files in the list, if less than 2 we do not move for checking 
			{
				System.out.println("Directory is either empty or only one file is present, can not check for plagiarism");
				bw.write("Directory is either empty or only one file is present, can not check for plagiarism");
				bw.newLine();
			}
			else
			{
				System.out.println("Plagiarism using different algorithms");
				bw.write("Plagiarism using different algorithms");
				bw.newLine();
				bw.write("****************************************************");
				bw.newLine();
				System.out.println(new Timestamp(d).toString());// We convert the value returned by Time stamp function to improve the readability of date
				bw.write(new Timestamp(d).toString());	
				bw.newLine();	
				for(int x=0; x<files.size();x++)
				{
					for(int y=x+1;y<files.size();y++)
					{									
						System.out.println("Between files "+files.get(x).getName()+" "+files.get(y).getName());
						bw.write("Between files "+files.get(x).getName()+" "+files.get(y).getName());
						bw.newLine();
						Bag_Of_Words bow=new Bag_Of_Words(files.get(x),files.get(y));// creating a new object for the class to access its functions
						if(bow.main_BOW().equals("NaN"))// we are first checking the result of bag of words because in this even if one of the file is empty,
														// we can discard the other algorithms
						{
							System.out.println("Can not compare as atleast one of the file is empty");
							bw.write("Can not compare as atleast one of the file is empty");
							bw.newLine();
							bw.newLine();
							bw.newLine();
						}
						/* 
						 * all the objects created are used to access the main_(class name) method and they obtain a string value
						 */
						else
						{	
							System.out.println("Using bag of words : "+bow.main_BOW());
							bw.write("Using bag of words "+bow.main_BOW());
							bw.newLine();
							Largest_Common_String lcs= new Largest_Common_String(files.get(x),files.get(y));// creating a new object for the class to access its functions
							System.out.println("Using LCS : "+lcs.main_LCS());
							bw.write("Using LCS "+lcs.main_LCS());
							bw.newLine();
							Finger_Printing fp=new Finger_Printing(files.get(x),files.get(y));// creating a new object for the class to access its functions
							if(fp.main_fp().equals("Nan"))// we are handling the exception of file with either stop words
							{
								System.out.println("Finger printing not possible as there are either only stop words or too small to create finger print");
								bw.write("Finger printing not possible as there are either only stop words or too small to create finger print");
							}
							else
							{
								System.out.println("Using fingerprinting : "+fp.main_fp());
								bw.write("Using fingerprnting : "+fp.main_fp());
							}						
							bw.newLine();
							bw.newLine();
							bw.newLine();
							bw.newLine();
						}
						
					}
				}
				bw.close();
				fw.close();
			}
		}
		catch (IOException e)// to catch and print the exception 
		{
			System.out.println(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}		
	}
}
