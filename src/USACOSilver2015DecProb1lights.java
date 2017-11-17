import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.swing.tree.TreeNode;

import javafx.util.Pair;

public class USACOSilver2015DecProb1lights {
	//N -- N x N rooms
	// M -- M pair of rooms to indicate room A--> room B, which Room A has a switch for Room B 
	int N, M;
	boolean Changed = true; // true means one node is added to tree, false no new node is added
	// Start -- room A
	Pair<Integer, Integer>[] ArrayRoomA, ArrayRoomB;

	// tree to save the room A --> Room B mapping
	MyTree<Pair<Integer, Integer>> TreeRoom = new MyTree<Pair<Integer, Integer>>( new Pair<Integer, Integer>(1,1));
	
	int numVisitedRoom;
	//input function
	//this function read input from file,
	public void ProbInput() throws IOException
	{
		System.out.println("start reading input from file\n" );
		BufferedReader in = new BufferedReader( new FileReader( "light.in"));
		StringTokenizer st = new StringTokenizer( in.readLine());
		int x,y,a,b;
		
		//read in N, M
		this.N = Integer.parseInt( st.nextToken());
		this.M = Integer.parseInt(st.nextToken());
		
		
		
		this.ArrayRoomA = new Pair[M];
		this.ArrayRoomB = new Pair[M];
		
		for( int i = 0; i < M; i++)
		{
			st = new StringTokenizer( in.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			a = Integer.parseInt( st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
		    this.ArrayRoomA[ i ] = new Pair<Integer, Integer>(x, y);
		    this.ArrayRoomB[ i ] = new Pair<Integer, Integer>( a, b );
		}
		System.out.println("finish reading input\n");
	};
	
	
	//output function
	public void PorbOuput() throws FileNotFoundException
	{
		PrintWriter out = new PrintWriter( new File( "light.out"));
		out.print(this.numVisitedRoom);
		out.close();
		System.out.println(this.numVisitedRoom);
	};
	
	// function to solve problem
	public void ProbSolver()
	{
		//create the tree from ArrayRoomA and ArrayRoomB
		int i;
		MyNode<Pair<Integer, Integer>> node;
		
		while( this.Changed)
		{
			//set change to false
			this.Changed = false;
			for( i = 0; i< this.M; i++ )
			{
				//if this node has been in tree, skip
				if( this.ArrayRoomA[i].getKey()==-1)
					continue;
				//search node in tree, if roomA is in tree, then add B
				node = this.TreeRoom.addChild(this.ArrayRoomB[i], this.ArrayRoomA[i]);
				
		
				if(node != null)
				{
					//if roomA is in tree, tree is updated 
					this.Changed = true;
					//indicate ith element has been in tree
					this.ArrayRoomA[ i ]= new Pair<Integer, Integer>(-1,-1);
				};			
			}
		};
		
		//travesal the tree
		this.numVisitedRoom = this.TreeRoom.Traversal();
		
	};
	
	// driver function
	public void Solver()
	{
		try
		{
			this.ProbInput();
		}
		catch( IOException e)
		{
			e.printStackTrace();
		};
		
		this.ProbSolver();
		
		try
		{
			this.PorbOuput();
		}
		catch( FileNotFoundException e)
		{
			e.printStackTrace();
		};
	}
	
};
