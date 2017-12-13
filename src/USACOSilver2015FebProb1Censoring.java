import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class USACOSilver2015FebProb1Censoring {
	//input string
	String FullStr;
	// pattern to match
	String Pattern;
	// string after censorship
	String ResultStr;
	// output debug info, if true, output the debug info
	boolean Verbose = true;
	int [] PatternAry;
	String MatchMethod;
	
	//constructor
	USACOSilver2015FebProb1Censoring( String Method)
	{
		this.MatchMethod = Method;
	};
	
	USACOSilver2015FebProb1Censoring()
	{
		this.MatchMethod = "JavaBuiltIn";
	};
	
	private int Matching( String Text, String Pattern )
	{
		int Midx;
		if( this.MatchMethod == "" )
			this.display( "no Algorithm is not set, use Java Built in\n");
		else
			this.display("Match Algo: " + this.MatchMethod + "\n");
		switch( this.MatchMethod)
		{
			case "KMP": Midx = this.KMPStringMatch(Text, Pattern);
						break;
			case "BruteForce": Midx = this.BruteForce( Text, Pattern );
						break;
			case "JavaBuiltIn": Midx = Text.indexOf(Pattern);
			default:
				Midx = Text.indexOf(Pattern);
		};
		
		return( Midx);
		
	}
	
	private int BruteForce( String Text, String Pattern)
	{
		int Midx = -1;
		int i, j, m;
		for( i = 0; i < Text.length() - Pattern.length(); i++ )
		{
			for(j=0; j < Pattern.length(); j++)
			{
				if( Pattern.charAt(j) != Text.charAt(i+j))
				{
					//if break, restart
					j = 0;
					break;
				};
			}
			
			//check if match
			if( j >= Pattern.length())
			{
				Midx = i;
				this.display("find match at index: " + Midx + "\n");
				break;
			}
		}
		return( Midx );
	};
	private void display( String Str )
	{
		if( this.Verbose)
			System.out.println(Str);
	};
	
	public void Solver()
	{
		this.display("start input\n");
		try
		{
			this.ProbInput();
		}
		catch( IOException ex )
		{
			ex.getStackTrace();
		}
		this.display("finish input\n");
		
		this.display("start problem solver\n");
		this.ProbSolver();
		this.display("finish solving the proble\n");
		
		try
		{
			this.ProbOutput();
		}
		catch( FileNotFoundException ex)
		{
			ex.getStackTrace();
		}
		this.display("done");
	}
	
	private void ProbInput() throws IOException
	{
		BufferedReader f = new BufferedReader( new FileReader( "Censor.in"));
		this.FullStr = f.readLine();
		this.Pattern = f.readLine();
		this.display("full string:"+this.FullStr+"\n");
		this.display("pattern:" + this.Pattern + "\n");
		
		//allocate the pattern array
		this.PatternAry = new int[ this.Pattern.length()];
	}
	
	private void ProbOutput() throws FileNotFoundException
	{
		File f = new File( "censor.out");
		PrintWriter pw = new PrintWriter( f );
		pw.println(this.ResultStr);
		pw.close();
		this.display("result: "+this.ResultStr+"\n");
	};
	
	private void patternArray( String Pattern, int Size, int [] Ary )
	{
		int prefix_len = -1; //length of the previous longest prefix suffix
		int i =0;
		Ary[ i ] = prefix_len;
		//i++;prefix_len++;
		//calculate Ary[i] for i = 1, size -1
		while(i<Size)
		{
//			if( Pattern.charAt(prefix_len) == Pattern.charAt(i))
//			{
//				Ary[ i ] = prefix_len;
//			}
//			else{
//				Ary[ i ] = prefix_len;
//			};
			while(prefix_len>=0 && Pattern.charAt(i) != Pattern.charAt(prefix_len))
			{
				prefix_len = Ary[ prefix_len ];
			}
			i++;
			prefix_len++;
			//if use following special case, it will make first letter to be -1
//			if( prefix_len == 0 && i<Size && Pattern.charAt(prefix_len)==Pattern.charAt(i))
//			{
//				Ary[ i] = Ary[ prefix_len];
//			}
//			else
//			{
				Ary[i]=prefix_len;
//			};
		};
	}
	
	// this function find the pattern Pat in String Txt and return the first index
	// if no pattern in txt, returns -1, otherwise return the index of all occurrance
	private int KMPStringMatch( String Txt, String Pat)
	{
		int Matchidx = -1;
		
		int M = Pat.length();
		int N = Txt.length();
		
		int i = 0; //index in txt
		int j = 0; //index in Pat
		
		
		// pre calculate the pattern array
		int[] lps = new int[ M+1 ];
		this.patternArray(Pat, M, lps);
		
		while( i<N )
		{
			if( Pat.charAt(j) == Txt.charAt(i))
			{
				//if match, check next one
				i++;j++;
			}
			else
			{
				// mismatch after j matches
				// match lps[0]...lps[ j-1] 
				// characters, 
				//i -= lps[j];
				if( lps[ j] == -1)
				{
					//if first letter is mismatch, need to move to next in s and reset j
					i++;
					j=0;
				}
				else
				{
					// if not first letter is mismatch, i is already moved, just update d
					j=lps[j];
				}
			}
			
			if( j == M)
			{
				//find a match
				System.out.println("found patter at index:" + i + "\n");
				Matchidx = i-j; // in txt, start from i-j, find a match string
				j = lps[ j-1];
				break;
			}
			
		};
		return( Matchidx );
	}
	private void ProbSolver()
	{
		//search the pattern and remove from string
		this.ResultStr = this.FullStr;
		int Midx;
		
		this.display( "start searching\n");
		while( ( Midx = this.Matching(this.ResultStr, this.Pattern) )!=-1)
		{
			this.display("full test: " + this.ResultStr + "\n");
			this.display("Patter: " + this.Pattern + "\n");
			this.display("match at: "+ Midx + "\n");
				if( Midx == 0 )
			{
				//match at begining
				this.ResultStr = this.ResultStr.substring(Midx + this.Pattern.length(), this.ResultStr.length()-1);
			}
			else
				this.ResultStr=this.ResultStr.substring(0, Midx )+this.ResultStr.substring(Midx+this.Pattern.length(), this.ResultStr.length());
			
			this.display("New Str: "+this.ResultStr + "\n");
		}
	};
}
