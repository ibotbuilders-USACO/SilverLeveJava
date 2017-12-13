import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

import javafx.util.Pair;
// in 2012, bronze level is equivalent to current silver level, so use silver in class name
// even the problem is 2012 bronze level prblem


public class USACOSilver2012Decprob3fence {
	int Nfence,Mcow; //number of fence and number of cows
	int LargestCowComm; //answer, the largest cow community
	
	int [] FenceStartXs, FenceStartYs, FenceEndXs, FenceEndYs; //coordinates array, save start and end points of fence
	int [] CowXs, CowYs; //coordinates array, save the position of cows
	
	//method 1, use hashtable to indicate special points, fence or cow 
	//remember when used for typed class, can not use primitive, use character not char
	Hashtable<Pair, Character> FenceCow1;  //coordinates indicate it is fence or cows
	
	// meothod 2, use matrix to indicate special points, fence or cow, be careful, the index here
	// is not coordinate directly, it is the discrete coordinates defined in compx and compy, compact x and compact y
	// in order to be able to build the path around the fence, need to double the dimension
	//
	//    | 
	//    | . _____
	//    |
	// as shown above, if two fence are 1 unit apart, need to double the dimension so that
	// the . can represent the path. see graph in ppt
	char [][] FenceCow2;
	int [] CompX, CompY; //compact x and y, it will be huge matrix is above two dimensional arry 
	// use original coordinates, 1-1000,1000, since only the points have fence or cow matters, 
	//we only need to consider those x and y's total Nfence+Mcow. each one represent the discrete
	//incremental x and y which has either fence or cow.
	Hashtable<Pair<Integer, Integer>, Character> VisitedCow = new Hashtable<Pair<Integer, Integer>, Character>();
	
	//constructor
	USACOSilver2012Decprob3fence()
	{
		Nfence = Mcow = 0;
		this.LargestCowComm = -1;
	}
	
	private void probInput() throws IOException
	{
		BufferedReader Reader = new BufferedReader ( new FileReader( "crazy.in"));
		StringTokenizer St = new StringTokenizer( Reader.readLine());
		
		//read N fence and M cow
		this.Nfence = Integer.parseInt(St.nextToken());
		this.Mcow = Integer.parseInt(St.nextToken());
		
		int i, x, y;
		Pair Point;
		// construct the the pair coordinate of fence and cow
		this.FenceStartXs = new int[this.Nfence];
		this.FenceStartYs = new int[this.Nfence];
		this.FenceEndXs = new int[this.Nfence];
		this.FenceEndYs = new int[this.Nfence];
		this.CowXs = new int[ this.Mcow];
		this.CowYs = new int[ this.Mcow];
		
		for( i =0; i < this.Nfence; i ++)
		{
			St = new StringTokenizer( Reader.readLine());
			x = Integer.parseInt(St.nextToken());
			y = Integer.parseInt(St.nextToken());
			this.FenceStartXs[i]= x;
			this.FenceStartYs[i]= y;
			
			x = Integer.parseInt(St.nextToken());
			y = Integer.parseInt(St.nextToken());
//			Point = new Pair( x, y);
			this.FenceEndXs[i]= x;
			this.FenceEndYs[i]= y;
		}
		
		// cows input
		for( i=0; i < this.Mcow; i++ )
		{
			St = new StringTokenizer( Reader.readLine());
			x = Integer.parseInt(St.nextToken());
			y = Integer.parseInt(St.nextToken());
			//Point = new Pair( x, y);
			this.CowXs[i]= x;
			this.CowYs[i]= y;
		}
	};
	
	//output function
	private void proboutput() throws FileNotFoundException
	{
		File f = new File( "crazy.out");
		PrintWriter PW = new PrintWriter( f );
		
		PW.println(this.LargestCowComm);
		System.out.println("largest cow community cotains " + this.LargestCowComm + "cows\n");
		PW.close();
	}
	
	
	private int [] Compact( char CoorD)
	{
		int [] Comp;
		int Size;
		// construct array
		Size = (int) ( this.FenceStartXs.length+this.FenceEndXs.length+this.CowXs.length );
		Comp = new int[Size ];
		//concatenate array togother
		if( CoorD == 'x')
		{
			System.arraycopy(this.FenceStartXs, 0, Comp, 0, this.Nfence);
			System.arraycopy(this.FenceEndXs, 0, Comp, this.Nfence, this.Nfence);
			System.arraycopy(this.CowXs, 0, Comp, 2*this.Nfence, this.Mcow);
		}
		else
		{
			System.arraycopy(this.FenceStartYs, 0, Comp, 0, this.Nfence);
			System.arraycopy(this.FenceEndYs, 0, Comp, this.Nfence, this.Nfence);
			System.arraycopy(this.CowYs, 0, Comp, 2*this.Nfence, this.Mcow);			
		}
		
		Arrays.sort(Comp);
		Comp = this.removeDuplicate(Comp);
		return( Comp);
	}
	
	private int[] removeDuplicate( int [] arr)
	{
		ArrayList aL = new ArrayList();
		int cur = arr[0];

		int i;
		int [] ret;
		
		aL.add(arr[0]);
		for(i = 1; i< arr.length; i++)
		{
			if( arr[i] != cur)
			{
				aL.add(arr[i]);
				cur = arr[i];
			}
		}
		
		ret = new int[ aL.size()];
		for( i = 0; i< aL.size(); i++)
			ret[i]=(int) aL.get(i);
		
		return(ret);
	}
	private Pair NextCoord( int x, int y, int dx, int dy)
	{
		Pair Next;
		
		x += dx;
		y += dy;
		
		if( x < 0 || x >= this.CompX.length*2
			|| y < 0 || y >= this.CompY.length*2)
		{
			//out of bounds
			Next = new Pair( -1, -1 ); //invalid next
		}
		else
		{
			Next = new Pair( x,y);
		}
		
		return( Next );
	}
	
	private int Connect( int x, int y )
	{
		int i, CommSize = 0;
		int Xidx, Yidx;
		Stack<Pair<Integer, Integer>> sTack = new Stack<Pair<Integer, Integer>>();  
		Pair<Integer, Integer> CurPoint, NextPoint;
		int [] nextXCoord = {0,0,1,-1};
		int [] nextYCoord = {1,-1,0,0};
		
		Xidx = this.CompId(x,  this.CompX);
		Yidx = this.CompId(y,  this.CompY);
		CurPoint = new Pair<Integer, Integer>( Xidx, Yidx);
		
		//if this cow has already been connected, do not need to do it again
		if( this.VisitedCow.containsKey(CurPoint))
			return(CommSize);
		
		sTack.push( CurPoint );
		
		while( !sTack.isEmpty())
		{
			CurPoint = sTack.pop();
			//if it is a cow, increment the size
			if( this.FenceCow2[ CurPoint.getKey()][CurPoint.getValue()] == 'C')
			{
				CommSize ++;
				this.VisitedCow.put(CurPoint, 'Y');
			};
			this.FenceCow2[CurPoint.getKey()][CurPoint.getValue()] ='Y';
			//find its neighbour
			for(i=0;i<4;i++)
			{
				NextPoint = this.NextCoord(CurPoint.getKey(), CurPoint.getValue(),nextXCoord[i], nextYCoord[i]);
				//if out of bound skip
				if( NextPoint.getKey() == -1 )
					continue;
				//if already visited, skip
				if( this.FenceCow2[NextPoint.getKey()][NextPoint.getValue()] =='Y')
					continue;
				//if not fence, push to stack
				if( this.FenceCow2[NextPoint.getKey()][NextPoint.getValue()]!='F')
				{
					// if not fence and valid, continue check
					sTack.push(NextPoint);
				};
			}
		}
		
		return( CommSize);
	}
	
	
	private void InitData()
	{
		int i, j, Size, ii, jj;
		int x, y, xidx, yidx;
		int startXidx, startYidx, endXidx, endYidx;
		int startX, startY, endX, endY;
		// construct array;
		this.FenceCow2 = new char[this.CompX.length*2][];
		for( i=0; i< this.CompX.length*2; i++ )
		{
			this.FenceCow2[ i ] = new char[ this.CompY.length*2 ];
			for( j=0;j<this.CompY.length*2;j++)
				this.FenceCow2[i][j]='E';
		}
		
		//initialize the fence
		for(i = 0; i< this.Nfence; i++ )
		{
			x = this.FenceStartXs[i];
			y = this.FenceStartYs[i];
			startXidx = this.CompId(x, this.CompX);
			startYidx = this.CompId(y, this.CompY);
			x = this.FenceEndXs[i];
			y = this.FenceEndYs[i];
			endXidx = this.CompId(x, this.CompX);
			endYidx = this.CompId(y, this.CompY);
			
			// need to make startX and StartY is the smaller one
			if( startXidx > endXidx )
			{
				startX = endXidx;
				endX = startXidx;
			}
			else
			{
				startX = startXidx;
				endX = endXidx;
			}
			if( startYidx > endYidx)
			{
				startY = endYidx;
				endY = startYidx;
			}
			else
			{
				startY = startYidx;
				endY = endYidx;
			}
			
			for( ii = startX; ii<= endX ; ii++)
			{
				for( jj = startY; jj<= endY; jj++ )
				{
					this.FenceCow2[ ii ][ jj ] = 'F';
				}
			}
		}
		//initialize the cow coord
		for(i=0;i<this.Mcow;i++)
		{
			x = this.CowXs[i];
			y = this.CowYs[i];
			
			xidx = this.CompId( x, this.CompX );
			yidx = this.CompId( y, this.CompY );
			
			this.FenceCow2[xidx][yidx] = 'C';
		}
		
		
	}
	
	private int CompId( int x, int [] CompCoord)
	{
		int idx;
		
		idx = Arrays.binarySearch(CompCoord, x);
		//multiply by 2 to make the matrix granularity by 2, so edge can be vertex for flood
		return( idx *2);
	}
	//problem solver
	private void probSolver()
	{
		int i, CommSize;
		
		//build up compact x and y
		this.CompX = this.Compact('x');
		this.CompY = this.Compact('y');
		this.InitData();
		//check the conection of each cow
		for( i = 0; i < this.Mcow; i++ )
		{
			System.out.println("process cow " + i + "\n");
			CommSize = this.Connect( this.CowXs[ i ], this.CowYs[ i ]);
			System.out.println("cow "+ i + "has community: "+ CommSize + "\n");
			if( CommSize > this.LargestCowComm)
				this.LargestCowComm = CommSize;
			System.out.println("largest comm size is "+this.LargestCowComm + "\n");
		}
	}
	
	//public interface function
	public void Solver()
	{
		//input
		System.out.println("read intput:\n");
		try
		{
			this.probInput();
		}
		catch( IOException ex)
		{
			ex.getStackTrace();
		}
		System.out.println("finish reading input\n");
		System.out.println("start solve the prblem\n");
		this.probSolver();
		
		System.out.println("finish the problem solver\n");
		System.out.println("start output to file\n");
		try
		{
			this.proboutput();
		}
		catch( FileNotFoundException ex)
		{
			ex.getStackTrace();
		}
		System.out.println("done!!!\n");
	}
}
