import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class bbreeds {
	static int[] open = new int[500], closed = new int[500];
	static LinkedList<Integer> paren;
	static int combo = 1, length;
	static boolean done = false;
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("bbreeds.in"));
		File file = new File("bbreeds.out");
		PrintWriter out = new PrintWriter(file);
		String list;
		int i;
		// Have integer array with 0 for open Paren an 1 for close Paren
		// have variable that holds index of last open Paren
		// Recursive function until that index
		list = f.readLine();
		length = list.length();
		paren = new LinkedList<Integer>();
		for(i=0; i< list.length();i++)
		{
			if(list.charAt(i) == '(')
			{
				paren.add(0);
			}
			if(list.charAt(i) == ')')
			{
				paren.add(1);
			}
		}
		calc();
		//System.out.println(combo);
		out.println(combo);
		out.close();
	}
	public static int findBalancedEnd()
	{
		int numOpen = 1, numClosed = 0, count = 1, endIndex = 0, openCount = 1, closedCount = 0;
		
		while(true)
		{
			if(numOpen == numClosed)
			{

				if(numOpen == 1)
				{
					done = true;
					
				}
				return endIndex;
			}
			if(paren.get(count) == 0)
			{
				numOpen++;
				open[openCount] = count;
				openCount++;
			}
			else if(paren.get(count) == 1)
			{
				numClosed++;
				closed[closedCount] = count;
				closedCount++;
			}
			endIndex++;
			count++;
			
		}
		
	}
	public static int pairsPerBalance(int endIndex)
	{
		int i,j, length = (endIndex+1)/2, temp = 0;
		for(i=0;i<length;i++)
		{
			for(j=0;j<length;j++)
			{
				if(open[i]<closed[j])
				{
					temp++;
				}
			}
		}
		temp+=2;
		return temp;
	}
	public static void calc()
	{
		int endIndex = 1, i;
		boolean stop = false;
		while(stop == false)
		{
			endIndex = findBalancedEnd();
			for(i=0;i<=endIndex;i++)
			{
				paren.remove(0);
			}
			if(paren.isEmpty())
			{
				stop = true;
			}
			if(done == true)
			{
				done=false;
				combo*=2;
			}
			else 
			{
				combo *= pairsPerBalance(endIndex);
			}
			
		}
	}

}
