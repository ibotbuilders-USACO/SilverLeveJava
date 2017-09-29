import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class maxcross {
	static int numFix = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("maxcross.in"));
		File file = new File("maxcross.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		int numSig = Integer.parseInt(st.nextToken()), numNeeded = Integer.parseInt(st.nextToken()), numBroke = Integer.parseInt(st.nextToken()), i;
		int[] sig = new int[numSig], brokenID = new int[numBroke];
		int count = 0;
		for(i=0;i<numBroke;i++)
		{
			StringTokenizer ed = new StringTokenizer(f.readLine());
			brokenID[i] = Integer.parseInt(ed.nextToken());
		}
		Arrays.sort(brokenID);
		for(i=0;i<numSig;i++)
		{
			sig[i] = 1;
		}
		sig = allSig(sig, sig.length, brokenID);
		check(sig, sig.length, numNeeded);
		System.out.println(numFix);
		out.println(numFix);
		out.close();
		
	}
	public static int[] allSig(int[] array, int length, int[] check)
	{
		int[] temp = new int[length];
		int i, count = 0;
		for(i=1;i<length+1;i++)
		{
			if(check[count] == i)
			{
				array[i-1]=0;
				count++;
				
			}
			if(count == check.length)
			{
				break;
			}
		}
		return array;
	}
	public static void check(int[] sigs, int sigLength, int needed)
	{
		int brokeInBlock = 100001, brokeInBlockTemp = 0, i, startIndex, endIndex, j;
		boolean skip = false;
		for(i=0;i<sigLength-needed+1;i++)
		{
			
			if(sigs[i] == 1)
			{
				if(skip == true)
				{
					continue;
				}
				else
				{
					skip = true;
					startIndex = i;
					endIndex = startIndex + needed;
					for (j = startIndex; j < endIndex; j++) {
						if (sigs[j] == 0) {
							brokeInBlockTemp++;
						}
					}
					if (brokeInBlockTemp < brokeInBlock) {
						brokeInBlock = brokeInBlockTemp;
					}
					brokeInBlockTemp = 0;
				}
			}
			else
			{
				skip = false;
			}
			
		}
		numFix = brokeInBlock;
	}
	

}
