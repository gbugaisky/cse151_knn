import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.lang.Math;
import java.util.Comparator;
import java.util.Iterator;
import java.text.DecimalFormat;

public class NN3Classifier
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
        File validate = new File("../data/hw2test.txt");
        testList = readFile(validate);

        //for each value of k

        int currentK = 3;
        //2. Create a priority queue that will hold the k best candidates
        kElements = new PriorityQueue<Integer[]>(currentK, 
            new Comparator<Integer[]> () {
                public int compare(Integer[] a, Integer[] b) {
                    return ( (Integer) b[0]).compareTo(a[0]);
                }
            }
        ) ;

        //Initialize the confusion matrix
        int[][] confusionMatrix = new int[10][10];

        System.out.println("\nEvaluating k=" + currentK);
        //System.out.println("Actual\tEvaluated");

        Iterator<Integer[]> testEl = testList.iterator();
        while (testEl.hasNext()) {
            Iterator<Integer[]> baseEl = baseList.iterator();
            for (int j = 0; j < currentK; j++) {
                kElements.add( new Integer[] {Integer.MAX_VALUE, Integer.MAX_VALUE} );
            }
            Integer[] testing = testEl.next();
            //call calculate distance function to get our distance

            while (baseEl.hasNext()) {
                Integer[] comparison = baseEl.next();
                //call calculate distance function to get our distance
                Integer distance = distanceFunction(testing, comparison); //distance from what we're looking at to the current point
                //compare the distances
                Integer[] thing = kElements.peek();
                if ( thing[0] > distance) {
                //if our distance is shorter, then poll
                    kElements.poll();
                //and then add 
                    kElements.add( new Integer[] {distance, comparison[comparison.length-1]});
                }
                
            }

            //figure out which of the labels we should use
            Integer[] labelGet = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            while (!kElements.isEmpty())
            {
                Integer distance = kElements.peek()[0];
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


            //Increment the correct cell of the confusion matrix
            confusionMatrix[greatest][testing[testing.length-1]] += 1;

            //tidy up for the next loop
            kElements.clear();
            
        }
        
        //now we need the totals in each column to create the fraction
        int[] totalLabels = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < 10; i++)
        {
            totalLabels[i] = confusionMatrix[0][i] + confusionMatrix[1][i] + confusionMatrix[2][i] + confusionMatrix[3][i] + confusionMatrix[4][i] + confusionMatrix[5][i] + confusionMatrix[6][i] + confusionMatrix[7][i] + confusionMatrix[8][i] + confusionMatrix[9][i]; 
        }


        DecimalFormat df = new DecimalFormat("##.####"); 
        int column = 0;
        //Print the conusion matrix as a nice table
        for (int[] row : confusionMatrix )
        {
            column = 0;
            for (int i : row)
            {
                float fraction = ( (float) i)/totalLabels[column];
                if (fraction == 0)
                    System.out.print( "0       ");
                else
                    System.out.print( df.format(fraction) + "  ");
                column++;
            }
            System.out.print("\n");
        }
                   

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

                //3. then put inside the linked list
                result.add(data);
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static Integer distanceFunction(Integer[] computing, Integer[] base)
    {
        Integer distance = 0;

        for (int i = 0; i < base.length - 2; i++) 
        {
            distance += (computing[i] - base[i]) * (computing[i] - base[i]);
        }

        return distance;
    }

}
