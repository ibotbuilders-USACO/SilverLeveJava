import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Stack;

public class typo {
	static Stack<Integer> stack = new Stack<Integer>();
	static LinkedList<Integer> patternLink = new LinkedList<Integer>(), second = new LinkedList<Integer>();
	static int changed = 0, length, numOpen, numClosed, total;
	static String pattern;
	static boolean isBalance = true;
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("typo.in"));
		File file = new File("typo.out");
		PrintWriter out = new PrintWriter(file);
		pattern = f.readLine();
		int i;
		length = pattern.length();
		for(i=0;i<length;i++)
		{
			patternLink.add(pattern.charAt(i)-40);
			if(patternLink.get(i) == 0)
			{
				numOpen++;
			}
			else
			{
				numClosed++;
			}
		}
		isBalanced();
		System.out.println(changed);
		out.print(changed);
		out.close();
	}
	public static void isBalanced()
	{
		int count = 0;
		boolean first = true;
		if(numClosed == numOpen)
		{
			return;
		}
		if(patternLink.get(0) == 1)
		{
			patternLink.set(0, 0);
			changed++;
			count++;
			total++;
		}
		if(numClosed>numOpen)
		{
			while(count<length)
			{
				if(patternLink.get(count) == 0)
				{
					total++;
				}
				else
				{
					//before adding does it balance
					if(total<0)
					{
						patternLink.set(count, 0);
						count++;
						continue;
					}
					else
					{
						total--;
						changed++;
						
					}
					
					
				}
				count++;
			
			}
		}
		else
		{
			//NumOpen > numClosed
			while(count<length)
			{
				if(patternLink.get(count) == 1)
				{
					total--;
					count++;
					continue;
				}
				if(count == length - 1 && patternLink.get(count) == 0)
				{
					changed++;
					return;
				}
				if(count == length - 1 && patternLink.get(count) == 1)
				{
					return;
				}
				if(patternLink.get(count) == 0 && first == true)
				{
					total++;
					first = false;
					count++;
					continue;
				}
				if(patternLink.get(count) == 1 && first == true)
				{
					total++;
					changed++;
					first = false;
					count++;
					continue;
				}
				else
				{
					//before adding does it balance
					if(total<0)
					{
						patternLink.set(count, 0);
						count++;
						continue;
					}
					else
					{
						total--;
						if(total<0)
						{
							patternLink.set(count, 0);
							total+=2;
							count++;
							continue;
						}
						total+=2;
						changed++;
						
					}
					
					
				}
				count++;
			
			}
		}
		
	}
	

}
