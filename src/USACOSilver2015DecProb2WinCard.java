import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class USACOSilver2015DecProb2WinCard {
	// total # card
	int N; 
	int [] Cards;
	PriorityQueue<Integer> Bessie;
	PriorityQueue<Integer> Elsie;
	int Points = 0;
	
	USACOSilver2015DecProb2WinCard()
	{
		Bessie = new PriorityQueue<Integer>();
		Elsie = new PriorityQueue<Integer>();
	};
	
	private void ProbInput() throws IOException
	{
		// read in input
		int i;
		BufferedReader f = new BufferedReader( new FileReader( "wincard.in") );
		StringTokenizer st = new StringTokenizer( f.readLine());
		
		this.N = Integer.parseInt(st.nextToken());
		//initialize the card array, size is 2n+1, since card is from 1---2n
		this.Cards = new int[ 2*this.N+1];
		for(i = 0; i < 2*this.N+1; i++ )
			this.Cards[ i ] = 1;
		//read elsie cards
		for(i = 0; i< this.N; i++)
		{
			st = new StringTokenizer( f.readLine());
			this.Cards[ Integer.parseInt(st.nextToken())] = 0;  
		};
		f.close();
		System.out.println("finish input\n");
	};
	
	private void ProbOutput() throws FileNotFoundException
	{
		File file = new File( "cardwin.out");
		PrintWriter Out = new PrintWriter( file);
		
		Out.print(this.Points);
		Out.close();
		
		System.out.println( this.Points);
	};
	
	private void ProbSolver()
	{
		// put bessie's card and elsie's card into queue
		int i;
		//start from 1, since card number is start from 1
		for( i=1; i< this.N * 2+1; i++)
		{
			if( this.Cards[ i ] == 1)
			{
				this.Bessie.add(this.Cards[i]);
			}
			else{
				this.Elsie.add(this.Cards[i]);
			}
		}
		
		int Bcard, Ecard;
		//check 
		for(i=0;i<this.N; i++)
		{
			Bcard = this.Bessie.poll();
			Ecard = this.Elsie.peek();
			//if Bessi's card is large
			if( Bcard > Ecard)
			{
				this.Points++;
				this.Elsie.poll();
			};			
		}		
	};

	public void Solver()
	{
		try
		{
			this.ProbInput();			
		}
		catch ( IOException ex)
		{
			ex.printStackTrace();
		};
		
		this.ProbSolver();
		try{
			this.ProbOutput();
		}
		catch (FileNotFoundException ex)
		{
			ex.printStackTrace();
		}
	};
}
