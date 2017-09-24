import java.text.ParseException;
//import org.apache.commons.lang3.ArrayUtils;

public class DynamicProg {
	static int[] Coins = {1, 2,3};
	static int Sum = 5;
	
	private static int MinCoin( int [] Array){
		int i, MinCoin;
		MinCoin = Array[0];
		for( i=0; i< Array.length; i++){
			if( Array[ i ] < MinCoin)
				MinCoin = Array[ i ];
		}
		return MinCoin;
	}
  public static void main(String args[]) throws ParseException {
	int TotalComb =0;
	
	TotalComb = Ncomb( Sum, Coins);
	System.out.println(TotalComb);
  };
  
  public static int Ncomb(int CurSum, int [] CoinSet ){
	  int Comb = 0;
	  int i, MinCoin;
	  
	  MinCoin = MinCoin( CoinSet);
	  if( CurSum < MinCoin ){
		  Comb = 0;
	  }else if( CurSum == MinCoin){
		  Comb = 1;
	  }else if( CoinSet.length == 1){
	  
		  if( CurSum % CoinSet[0] == 0){
			  Comb=1;
		  }
		  else
			  Comb = 0;				  
	  }
	  else{
		  // not loop through all i, 
		//  for(i=0; i< CoinSet.length-1; i++)
		  {
			 int [] NewCoinSet =  ArrayRemove( CoinSet,0);
			 Comb = Ncomb( CurSum - CoinSet[0], CoinSet)+
					 Ncomb(CurSum, NewCoinSet );
		  }
	  }
	  
	  return Comb;
  }
  
  private static int [] ArrayRemove(int [] Array, int Index ){
	  int [] newAry = new int[Array.length-1];
	  int i, idx=0;
	  for(i=0;i<Array.length;i++){
		  if( i!= Index ){
			  newAry[idx]=Array[i];
			  idx++;
		  }
		  
	  }
	  return newAry;
  }
}
