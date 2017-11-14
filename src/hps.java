import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Hashtable;

public class hps {
	static int total = 0, numMoves;
	static char[] fJMoves;
	static int[] right = new int[3], left = new int[3], inTotal = new int[3], right2 = new int[3], left2 = new int[3];
	public static void main(String[] args)  throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("hps.in"));
		File file = new File("hps.out");
		PrintWriter out = new PrintWriter(file);
		numMoves = Integer.parseInt(f.readLine());
		fJMoves = new char[numMoves];
		int i;
		for(i=0;i<numMoves;i++)
		{
			fJMoves[i] = f.readLine().charAt(0);
			if(fJMoves[i] == 'H')
			{
				inTotal[0]++;
			}
			if(fJMoves[i] == 'P')
			{
				inTotal[1]++;
			}
			if(fJMoves[i] == 'S')
			{
				inTotal[2]++;
			}
		}
		for(i=0;i<3;i++)
		{
			right[i] = 0;
			left[i] = 0;
			right2[i] = 0;
			left2[i] = 0;
		}
		answer();
		System.out.println(total);
		out.print(total);
		out.close();
	}
	public static void first()
	{
		int i;
		if(fJMoves[0] == 'H')
		{
			left[0]++;
		}
		if(fJMoves[0] == 'P')
		{
			left[1]++;
		}
		if(fJMoves[0] == 'S')
		{
			left[2]++;
		}
		for(i=1;i<numMoves; i++)
		{
			if(fJMoves[i] == 'H')
			{
				right[0]++;
			}
			if(fJMoves[i] == 'P')
			{
				right[1]++;
			}
			if(fJMoves[i] == 'S')
			{
				right[2]++;
			}
		}
	}
	public static void answer()
	{
		int lastLeft = 0, i, leftMax = 0, rightMax = 0;
		boolean first = true;
		first();
		while(lastLeft < numMoves)
		{
			if(first == true)
			{
				first = false;
			}
			else
			{
				if(fJMoves[lastLeft] == 'H')
				{
					left[0]++;
					right[0]--;
				}
				if(fJMoves[lastLeft] == 'P')
				{
					left[1]++;
					right[1]--;
				}
				if(fJMoves[lastLeft] == 'S')
				{
					left[2]++;
					right[2]--;
				}
			}
			left2 = left.clone();
			right2 = right.clone();
			Arrays.sort(left);
			Arrays.sort(right);
			if(left[2]+right[2]>total)
			{
				total = left[2]+right[2];
			}
			left = left2.clone();
			right = right2.clone();
			lastLeft++;
		}
	}
}

