package CoverLetter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class App
{
	public static void main(String[] args)
	{

		try
		{
			CoverLetterGenerator.createCoverLetter();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("file not found");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
