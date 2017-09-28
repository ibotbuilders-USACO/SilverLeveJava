import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
public class helpcross {
	static boolean inRange = false;
	static int combos = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("first.in"));
		File file = new File("helpcross.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		 int numChick = Integer.parseInt(st.nextToken()), numCow = Integer.parseInt(st.nextToken()), i, j=0, high =0;
		 int[] chickTime = new int[numChick];
		int[] startCow = new int[numCow];
		int[] endCow = new int[numCow];
		Object[] dummy;
		 String[] line;
		for (i = 0; i < numChick; i++) 
		{
			chickTime[i] = Integer.parseInt(f.readLine());
		}
		for (i = 0; i < numCow; i++) 
		{
			line = f.readLine().split(" ");
			startCow[i] = Integer.parseInt(line[0]);
			endCow[i] = Integer.parseInt(line[1]);
		}
		dummy = bubbleSort(endCow, startCow);
		endCow = (int[]) dummy[0];
		startCow = (int[]) dummy[1];
		Arrays.parallelSort(chickTime);
		for(i=0;i<numChick;i++)
		{
			for(j=0;j<numCow;j++)
			{
				if(chickTime[i] >= startCow[j] && chickTime[i] <=endCow[j])
				{
					combos++;
					chickTime[i] = -1;
					startCow[j] = -10;
					endCow[j] = -20;
					break;
				}
			}
		}
		System.out.println(combos);
		out.println(combos);
		out.close();
	}
	public static int[] sort(int[] array, int length) {
		int temp;
		for (int i = 0; i < length - 1; i++) {
			if (array[i] > array[i + 1]) {
				temp = array[i];
				array[i] = array[i + 1];
				array[i + 1] = temp;
				i = -1;
			}
		}
		return array;
	}
	private static Object[] bubbleSort(int[] intArray, int[] other) {
        int n = intArray.length;
        int temp = 0, temp2 = 0;
        for(int i=0; i < n; i++){
                for(int j=1; j < (n-i); j++){
                       
                        if(intArray[j-1] > intArray[j]){
                                //swap the elements!
                                temp = intArray[j-1];
                                temp2 = other[j-1];
                                intArray[j-1] = intArray[j];
                                other[j-1] = other[j];
                                intArray[j] = temp;
                                other[j] = temp2;
                        }   
                }
        }
        return new Object[] {intArray, other};
	}
}
