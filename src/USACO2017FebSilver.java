import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class USACO2017FebSilver{
	int [] Chickens;
	DataPair [] Cows;
	int NChicken;
	int Ncows;
	int total;
	//input from file
	public void ProbInput() throws IOException
	{
		BufferedReader f = new BufferedReader( new FileReader("helpcross.in"));
		StringTokenizer st = new StringTokenizer( f.readLine());
	    int i;
	    String line;
	    
		NChicken = Integer.parseInt( st.nextToken());
		Ncows = Integer.parseInt( st.nextToken());
		
		Chickens = new int[ NChicken ];
		Cows = new DataPair[ Ncows];
		for( i=0; i< NChicken; i++ )
		{
			line = f.readLine();
			st = new StringTokenizer( line);
			Chickens[ i ] = Integer.parseInt(st.nextToken());			
		};
		
		for( i=0; i< Ncows; i++ )
		{
			line = f.readLine();
			st = new StringTokenizer( line);
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			Cows[ i ]= new DataPair( s, e);
		};
		
		f.close();
		System.out.println("finish input\n");
	};
	
	public void ProblemSolver()
	{
		//sort the chickens time
		Arrays.sort(Chickens);
	Arrays.sort(Cows);
		
		//go through each chick and assign a cow
		this.total = 0;
		int CowIdx = 0;
		int ckidx =0;
		int foundmatch = 0;
	//	while( CowIdx < Ncows)
		{
			foundmatch = 0;
			for( int i = ckidx; i< NChicken; i++ )
			{
				
				//find a matched cow
				if( Chickens[ i]<Cows[ CowIdx].GetStart())
				{
					
				}
				else if( Cows[CowIdx].Match(Chickens[i]))
				{
					this.total ++;
					CowIdx++;
			//		ckidx++;
			//		foundmatch=1;
		//			break;
				}
				else
				{
					CowIdx++;
					i--;
				};
			
				//if all cows are matched done
				if( CowIdx>=Ncows)
					break;
			};
			//if( foundmatch==0)
		//		CowIdx++;
		};		
		System.out.println("finish\n");
	};
	
	//output
	public void ProbOutput() throws FileNotFoundException
	{
		File file = new File( "helpcross.out");
		PrintWriter out = new PrintWriter( file );

		out.print(this.total);
		out.close();
		System.out.println(this.total);
	}
	
	//whole thing
	public void Solve()
	{
		try {
			this.ProbInput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ProblemSolver();
		try {
			this.ProbOutput();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
