import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class USACOSilver2017JanProb1 {
	int nCow;
	int Tmax;
	int [] timeCows;
	int minStageSize;
	
	public void probInput() throws IOException {
		BufferedReader in = new BufferedReader( new FileReader( "cowdance.in"));
		StringTokenizer st = new StringTokenizer( in.readLine());
		int i;
		
		nCow = Integer.parseInt(st.nextToken());
		Tmax = Integer.parseInt(st.nextToken());
		
		timeCows = new int[nCow];
		for(i = 0; i< nCow; i++)
		{
			timeCows[ i] = Integer.parseInt(in.readLine());
		};
		
		in.close();
		System.out.println("finish input");
	};
	
	public void probOut() throws FileNotFoundException
	{
		PrintWriter out = new PrintWriter( new File( "cowdance.out"));
		out.print(this.minStageSize);
		out.close();
		System.out.println(this.minStageSize);
	}
	
	public void Solver()
	{
		try
		{
			this.probInput();
		}
		catch( IOException e)
		{
			e.printStackTrace();
		};
		
		this.ProblemSolver();
		try
		{
			this.probOut();			
		}
		catch( FileNotFoundException e)
		{
			e.printStackTrace();
		}
	};
	
	public void ProblemSolver()
	{
		//check if stagesize equals 1 works, if works it is 1
		int low = 1, high = this.nCow, mid;
		boolean finishMid, finishLow = false, finishHigh = false, cont=true;
		//check the original setting
		//if low works, return it
		finishLow = FinishInTime( low );
		if( finishLow )
		{
			this.minStageSize = low;
			cont = false;
		}
		else 
		{
			//if high can not finish, give to 0. this should happen only when there is cow dance more than Tmax
			finishHigh = FinishInTime( high);
			if( !finishHigh)
			{
				this.minStageSize = 0;
				cont = false;
			}
		};

		//search the minimum stage size that make FinishInTime = 1;
		while( cont)
		{
				
				// now low can not finish, high can
				//get the mid and check
				mid = (int) ( ( low + high)/2 );
				finishMid = FinishInTime( mid );
				if( finishMid )
				{
					high = mid;
				}
				else
				{
					
					low = mid;					
				}
				//if high can finish but high-1 can not, then high is the min 
				if( high == low + 1)
				{
					this.minStageSize = high;
					cont = false;
				};				
		};

		System.out.println("finish if stage size is "+this.minStageSize + "\n");
	};
	
	public boolean FinishInTime( int stageSize )
	{
		 int first,curCow=0, nRemain, totalTime = 0;
		 boolean inTime = false;
		 PriorityQueue<Integer> OnStage = new PriorityQueue<Integer>( stageSize );
		 Integer [] RemainTime = new Integer[ stageSize ];
		 
		 // take the first stageSize element
		 for( int i = 0; i < stageSize; i++)
		 {
			OnStage.add(timeCows[i]);
		 };
		 
		 for( int i = stageSize; i< this.nCow;i++)
		 {
			 //check the minimum cow time, advance clock to current time, 
			 first = OnStage.poll();
			 totalTime = first;
			 
			 // add a new cow. instead of minus the time of first cow to get remaining time of all cows
			 // we add the current time to then new cow on stage, so time for each cow indicate the time that cow finishes.
			 // this will avoid to process the time of each cow on stage.
			 curCow = timeCows[ i ] + totalTime;
			 OnStage.add(curCow);
		 }
		 
		 // add the last batch time
		 while(!OnStage.isEmpty())
			 curCow = OnStage.poll();
			 
		 totalTime = curCow;
		 if( totalTime <= this.Tmax)
			 inTime = true;
		 
		 return inTime;
	};
	
}
