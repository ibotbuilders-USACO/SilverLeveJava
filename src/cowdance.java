import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class cowdance {
	static int numCows, maxTime, timeTaken = 0, cowsAtOnce = 1, guaranteed, prevOnce, binLeft, binRight, timeTakenTemp;
	static int[] cowsOnStage, second;
	static PriorityQueue<Integer> queue;
	static boolean stop = false, done = false;;
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("cowdance.in"));
		File file = new File("cowdance.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		numCows = Integer.parseInt(st.nextToken());
		maxTime = Integer.parseInt(st.nextToken());
		cowsOnStage = new int[numCows];
		binLeft = 0;
		binRight = numCows;
		cowsAtOnce = (binRight+binLeft)/2;
		queue = new PriorityQueue<Integer>(cowsAtOnce);
		int i;
		for(i=0;i<numCows;i++)
		{
			cowsOnStage[i] = Integer.parseInt(f.readLine());
		}
		while(true)
		{
			for(i=0;i<cowsAtOnce;i++)
			{
				queue.add(cowsOnStage[i]);
			}
			possible();
			if(timeTaken==maxTime)
			{
				break;
			}
			// CowsAtOnce too big
			if(timeTaken<maxTime)
			{
				cowsAtOnce--;
				timeTakenTemp = timeTaken;
				timeTaken = 0;
				for(i=0;i<cowsAtOnce;i++)
				{
					queue.add(cowsOnStage[i]);
				}
				possible();
				if(timeTaken>maxTime)
				{
					cowsAtOnce++;
					break;
				}
				else 
				{
					timeTaken = timeTakenTemp;
					cowsAtOnce++;
					binRight = cowsAtOnce;
					cowsAtOnce = (cowsAtOnce+binLeft)/2;
				}
				
			}
			// Stage too small
			if(timeTaken>maxTime)
			{
				binLeft = cowsAtOnce;
				cowsAtOnce = (cowsAtOnce+binRight)/2;
			}
			timeTaken = 0;
		}
		
		System.out.println(cowsAtOnce);
		out.print(cowsAtOnce);
		out.close();
		
	}
	public static void possible()
	{
		
		int[] last = new int[cowsAtOnce];
		int i, count = cowsAtOnce;
		while(true)
		{
			
			timeTaken = queue.poll();
			// Ending the iteration
			if(count == numCows)
			{
				for(i=1;i<cowsAtOnce;i++)
				{
					if(i == cowsAtOnce-1)
					{
						timeTaken = queue.poll();
						return;
					}
					queue.remove();
				}
			}
			queue.add(cowsOnStage[count]+timeTaken);
			count++;
			
			
		}
		
	}

}
