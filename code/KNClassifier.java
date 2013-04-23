import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.lang.Math;

public class KNClassifier
{
	static LinkedList<Integer[]> baseList, testList;
	static PriorityQueue<Integer[]> kElements;
	public static void main(String[] args)
	{
		//0. Read base data
		File file = new File("../data/hw2train.txt");
		baseList = readFile(file);
		//System.out.println("Number of spaces: " + exampleLine.split(" ").length);

		//1. Read first data to test
		File validate = new File("../data/hw2validate.txt");
		testList = readFile(validate);

		//for each value of k

		int[] kVals = {1, 3, 5, 11, 16, 21};

		for (int i = 0; i < 6; i++)
		{
			//2. Create a priority queue that will hold the k best candidates
			kElements = new PriorityQueue<Integer[]>(){kVals[i],
				public int compare(Integer[] a, Integer[] b) {
					return ( (Integer) a[0]).compareTo(b[0])
				}
			};

			System.out.println("Evaluating k=" + kVals[i]);
			System.out.println("Actual\tEvaluated");

			Iterator<Integer[]> testEl = testList.iterator();
			while (testEl.hasNext()) {
				for (int j = 0; j < kVals[i]; j++) {
					kElements.add( {Integer.MAX_VALUE, Integer.MAX_VALUE} );
				}
				Integer[] testing = testEl.next();
				//call calculate distance function to get our distance

				while (baseEl.hasNext()) {
					Integer[] comparison = baseEl.next();
					//call calculate distance function to get our distance
					Integer distance = //distance from what we're looking at to the current point
					//compare the distances
					if (kElements.peek()[0] > distance) {
					//if our distance is shorter, then poll
						kElements.poll();
					//and then add 
						kElements.add({distance, testing[testing.length-1]});
					}
					
				}

				//figure out which of the labels we should use
				Integer[10] labelGet = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                while (!kElements.isEmpty())
                {
                    Integer temp = kElements.poll()[1];
                    labelGet[temp] += 1;
                }
                
                Integer greatest = 0;

                for (int j = 1; j < 10; j++)
                {
                	if (labelGet[j] > labelGet[greatest])
                	{
                		greatest = j;
                	}

                	else if (labelGet[j] == labelGet[greatest])
                	{
                		if (Math.random() < 0.5)
                		{
                			greatest = j;
                		}
                	}
                }

				//and if the label doesn't match the input label, print a line.
                if (testing[testing.length-1] != greatest)
                {
                	System.out.println(testing[testing.length-1] + "\t" + greatest);
                }

				//tidy up for the next loop
				kElements.clear();
				
			}
			

		}

		System.out.println("Hello World!");
		System.out.println(baseList.size());
	}


	private static LinkedList<Integer[]> readFile(File readFrom)
	{
		LinkedList<Integer[]> result = new LinkedList<Integer[]>();
 		try {

			BufferedReader br = new BufferedReader( new FileReader ( readFrom ) );
			String line;

			while ((line = br.readLine()) != null) {
				//1. When we read a line, we want to split it
				String[] read = line.split(" ");

				//2. then convert it to ints
				Integer[] data = new Integer[read.length];

				for (int i = 0; i < read.length; i++) 
				{
					data[i] = Integer.parseInt(read[i]);
				}

				System.out.println("Last: " + data[data.length - 1]);
				//3. then put inside the linked list
				result.add(data);
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

}
