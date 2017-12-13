import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import javafx.util.Pair;


// this is the 2013 Jan Bronze problem1, since it is equivalent to silver for now.
// give name to silver
public class USACOsilver2013JanProb1Mirror {
	int N;    // number of mirror
	int Size; // size of the farm, boundary is -Size to Size
	int targetX, targetY; //target position
	int CurPosX, CurPosY; //current position
	
	//moving direction, E - +x, W - -x, N - +y, S - -y;
	Direction CurDir;
	// type has to non-primitive, i.e. it has to be Integer or Character, can not be int or char.
	Hashtable<Pair<Integer, Integer>, TypeMirror> MirrorsMap; //key is int pair -- coordinates, char for mirror type
	Hashtable<String,Pair<Integer, Integer> > Visited; //save the mirror pos which has been visited, key is string x+y+direction
	int [] MirrorsX, MirrorsY;
	int Fence; // index of Fence to switch, -1 means no fence to make Target reachable. 
	boolean Continue;
	
	public enum PosStatus {
		TARGET, OUTOFBOUNDS, CYCLE, MIRROR
	};
	
	public enum Direction {
		EAST, WEST, NORTH, SOUTH
	};
	public enum TypeMirror {
		UP, DOWN
	};
	//constructor
	USACOsilver2013JanProb1Mirror(){
		this.N=0;
		this.Size = 1000000;
		this.CurPosX = this.CurPosY = 0;
		this.CurDir = Direction.EAST;
		this.Continue = true;
		this.MirrorsMap = new Hashtable<Pair<Integer, Integer>,TypeMirror>();
		this.Fence = -1; //not reachable
		this.Visited = new Hashtable<String, Pair<Integer, Integer > >();
		
	};
	
	private void reset()
	{
		this.CurPosX = this.CurPosY = 0;
		this.CurDir = Direction.EAST;
		this.Continue = true;
		this.Fence = -1; //not reachable
		this.Visited.clear();
		
	}
	private void ProbInput() throws IOException
	{
		//read input from file mirror.in
		BufferedReader f = new BufferedReader( new FileReader( "mirror.in" ) );
		StringTokenizer st = new StringTokenizer( f.readLine());
		int i, x, y;
		Pair<Integer, Integer> MirrorLoc;
		char MirrorDir;
		TypeMirror MirrorType;
		String Str;
		
		//first line is N, x, y - coordinates of the target
		this.N = Integer.parseInt(st.nextToken());
		this.targetX = Integer.parseInt(st.nextToken());
		this.targetY = Integer.parseInt(st.nextToken());
		this.MirrorsX = new int[ this.N];
		this.MirrorsY = new int[ this.N];
		
		
		//read the mirrors, a, b is coordinates, then direction of mirrors
		for(i=0; i< this.N; i ++ )
		{
			st = new StringTokenizer( f.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			this.MirrorsX[ i ] = x;
			this.MirrorsY[ i ] = y;
			
			Str = st.nextToken();
			//MirrorDir = Str.charAt(0);
			if( Str.equals("/"))
			{
				MirrorType = TypeMirror.UP;
			}
			else
			{
				MirrorType = TypeMirror.DOWN;
			}
			MirrorLoc = new Pair<Integer, Integer>( x, y );
			this.MirrorsMap.put(MirrorLoc, MirrorType);
		};
	};
	
	private void ProbOutput() throws FileNotFoundException
	{
		File file = new File( "mirror.out");
		PrintWriter pw = new PrintWriter( file );
		
		pw.print(this.Fence);
		pw.close();
		System.out.print(this.Fence+"\n");
	};
	
	private void ToggleMirror( int Mirror )
	{
		if( Mirror > 0)
		{
			Pair<Integer, Integer> MPos = new  Pair<Integer, Integer>( this.MirrorsX[ Mirror-1 ], this.MirrorsY[ Mirror-1]);
			if( MirrorsMap.get( MPos) == TypeMirror.UP )
			{
				MirrorsMap.remove(MPos);
				MirrorsMap.put(MPos, TypeMirror.DOWN );
			}
			else
			{
				MirrorsMap.remove(MPos);
				MirrorsMap.put(MPos, TypeMirror.UP );
			};
		};
	}
	
	private void ProbSolver()
	{
		// start from 0
		boolean success = false;
		int Mirror = this.Fence + 1;
		System.out.println(String.format("current mirror: %d\n", Mirror) );
		while( !success)
		{
			success = this.reachable(Mirror);
			if( !success)
			{
				//toggle back current mirror
				this.ToggleMirror( Mirror );
			//toggle next Mirror
				Mirror++;
				this.ToggleMirror( Mirror );	
				this.reset();
				System.out.println(String.format("toggle mirror: %d\n", Mirror) );
			};
		};
			
		
	};
	private boolean reachable( int Mirror )
	{
		PosStatus Status;
		boolean Reachable = false;
		
		this.Continue = true;
		// get next position
		while( this.Continue)
		{
			this.next();
			System.out.println(String.format("current Pos: x--%d, y--%d\n", this.CurPosX, this.CurPosY));
			System.out.println(String.format("Current Direction: %s\n", this.CurDir.toString()));
			// get current status -  Hit Target, mirror, cycle, out of bounds
			Status = this.UpdateStatus();
			System.out.println(String.format("current Status:%s\n", Status.toString()));
			switch( Status )			
			{
				case TARGET: this.Fence = Mirror;
			                this.Continue = false;
			                Reachable = true;
							break;
				case OUTOFBOUNDS: this.Continue = false;
							break;
				case CYCLE: this.Continue = false;
							break;
				case MIRROR: 
					System.out.println(String.format("Current Direction: %s\n", this.CurDir.toString()) );
			};
		};
		
		return( Reachable );
	};
	
	private void next()
	{	
		PriorityQueue<Integer> PQ = new PriorityQueue<Integer>();
		int i, p;
	    //move to current positions
		switch( this.CurDir)
		{
		  
			case EAST:
				// hit target on the way??
				if( this.CurPosY == this.targetY && this.targetX > this.CurPosX )
				{
					PQ.add(this.targetX);
				};
				// add all the mirros on the way
				for( i = 0; i < this.N; i++)
					if( this.MirrorsY[i] == this.CurPosY && this.MirrorsX[i]>this.CurPosX )
						PQ.add(this.MirrorsX[i]);
				if(PQ.isEmpty())
				{
					//no mirror, no target, goes out of bound
					this.CurPosX = this.Size+1;
				}
				else
				{
					//if there is mirror or target on the way, move to it
					this.CurPosX = PQ.poll();
				};
			   break;
			case WEST: 
				if( this.CurPosY == this.targetY && this.targetX < this.CurPosX )
				{
					PQ.add(this.targetX);
				};
				// add all the mirros on the way
				for( i = 0; i < this.N; i++)
					if( this.MirrorsY[i] == this.CurPosY && this.MirrorsX[i]<this.CurPosX )
						PQ.add(this.MirrorsX[i]);
				if(PQ.isEmpty())
				{
					//no mirror, no target, goes out of bound
					this.CurPosX = -this.Size-1;
				}
				else
				{
					//if there is mirror or target on the way, move to it
					this.CurPosX = PQ.poll();
				};
				break;
			case NORTH: 
				if( this.CurPosX == this.targetX && this.targetY > this.CurPosY )
				{
					PQ.add(this.targetY);
				};
				// add all the mirros on the way
				for( i = 0; i < this.N; i++)
					if( this.MirrorsX[i] == this.CurPosX && this.MirrorsY[i]>this.CurPosY )
						PQ.add(this.MirrorsY[i]);
				if(PQ.isEmpty())
				{
					//no mirror, no target, goes out of bound
					this.CurPosY = this.Size+1;
				}
				else
				{
					//if there is mirror or target on the way, move to it
					this.CurPosY = PQ.poll();
				};
			   break;
			case SOUTH:
				if( this.CurPosX == this.targetX && this.targetY < this.CurPosY )
				{
					PQ.add(this.targetY);
				};
				// add all the mirros on the way
				for( i = 0; i < this.N; i++)
					if( this.MirrorsX[i] == this.CurPosX && this.MirrorsY[i]<this.CurPosY )
						PQ.add(this.MirrorsY[i]);
				if(PQ.isEmpty())
				{
					//no mirror, no target, goes out of bound
					this.CurPosY = -this.Size-1;
				}
				else
				{
					//if there is mirror or target on the way, move to it
					this.CurPosY = PQ.poll();
				};
				break;
		};
	};
	
	private PosStatus UpdateStatus()
	{
		PosStatus Status=PosStatus.TARGET;
		Pair<Integer, Integer> Pos;
		String MKey;
		
		//is it out of bounds?
		if( Math.abs(this.CurPosX) >= this.Size || Math.abs(this.CurPosY) >= this.Size)
		{
			Status = PosStatus.OUTOFBOUNDS;
		} //is it hit target??
		else if( this.CurPosX == this.targetX && this.CurPosY == this.targetY )
		{
			Status = PosStatus.TARGET;
		}
		else
		{
			
			Pos = new Pair<Integer, Integer>( this.CurPosX, this.CurPosY );
			MKey = String.format("%d%d%s", this.CurPosX,this.CurPosY,this.CurDir.toString());
			
			//check cycle
			if( this.Visited.containsKey(MKey)){
				Status = PosStatus.CYCLE;
			}
			else
			{
				this.Visited.put(MKey, Pos);
			};
			
			//check if mirror
			if( this.MirrorsMap.containsKey(Pos))
			{
				TypeMirror Mtype = this.MirrorsMap.get(Pos);
				this.CurDir = this.NewDir( this.CurDir, this.MirrorsMap.get(Pos));
				Status = PosStatus.MIRROR;
				System.out.println(String.format("Mirror Direction:%s\n", Mtype.toString()));

			};
			
		}

		return( Status );
	};
	
	private Direction NewDir( Direction Cur, TypeMirror MirrorType )
	{
		Direction NewDir = Direction.EAST;
		
		switch( Cur )
		{
			case EAST:
				if( MirrorType == TypeMirror.UP){
					NewDir = Direction.NORTH;
				}
				else
				{
					NewDir = Direction.SOUTH;
				};
				break;
			case WEST:
				if( MirrorType == TypeMirror.UP){
					NewDir = Direction.SOUTH;
				}
				else
				{
					NewDir = Direction.NORTH;
				};
				break;	
			case NORTH:
				if( MirrorType == TypeMirror.UP){
					NewDir = Direction.EAST;
				}
				else
				{
					NewDir = Direction.WEST;
				};
				break;
			case SOUTH:
				if( MirrorType == TypeMirror.UP){
					NewDir = Direction.WEST;
				}
				else
				{
					NewDir = Direction.EAST;
				};
				break;
		};
		return( NewDir);
	};
	
	
	public void Solver()
	{
		try{
			this.ProbInput();
		}
		catch ( IOException ex )
		{
			ex.getStackTrace();
		};
		System.out.println("finish input\n");
		System.out.println("start to solve problem \n");
		this.ProbSolver();
		System.out.println("finish solve the problem\n");
		System.out.println("output to file\n");
		try
		{
			this.ProbOutput();
		}
		catch( FileNotFoundException ex)
		{
			ex.getStackTrace();
		}
	};
}
