import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class KNClassifier
{
	public static void main(String[] args)
	{
		File file = new File("../data/hw2train.txt");
		 
 		try {

			BufferedReader br = new BufferedReader( new FileReader ( file ) );

													 
			int content;
			int lineCounter = 0;
			String line;
			while ((line = br.readLine()) != null) {
			// convert to char and display it
				lineCounter++;
				System.out.println("BR Works!");
			}
																															 
			System.out.println("Line counter: " + lineCounter);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Hello World!");
		//System.out.println("Number of spaces: " + exampleLine.split(" ").length);
		//1. :
	}


}
