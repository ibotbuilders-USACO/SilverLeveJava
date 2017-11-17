
public class DataPair implements Comparable<DataPair>{
	private int start;
	private int end;
	
	//constructor 
	DataPair()
	{
		start = 0;
		end = 0;
	};
	
	DataPair( int s, int e)
	{
		start = s;
		end = e;
	};
	
	// access method
	public int GetStart()
	{
		return( start);
	};
	public int GetEnd()
	{
		return( end );
	};
	
	// set method
	public void SetStart( int s)
	{
		start = s;
	};
	public void SetEnd( int e)
	{
		end = e;
	};
	
	//compare for sorting
	public int compareTo( DataPair CompPair)
	{
		return( this.end - CompPair.GetEnd());
	};
	
	public boolean Match( int in )
	{
		boolean ret = false;
		if( in <= this.end && in>= this.start )
			ret = true;
		
		return( ret);
	}
}
