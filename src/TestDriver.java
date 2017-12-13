import java.text.ParseException;

public class TestDriver {
	 public static void main(String args[]) throws ParseException {
	 //     MyHashTable Table = new MyHashTable();
	  //    Table.TestCase1(); 
		// USACO2017FebSilver prob = new USACO2017FebSilver();
		 //USACOSilver2017JanProb1 prob = new USACOSilver2017JanProb1();
		// USACOSilver2015DecProb1lights prob = new USACOSilver2015ecProb1lights();
		// USACOSilver2015DecProb2WinCard prob = new USACOSilver2015DecProb2WinCard();
		// USACOsilver2013JanProb1Mirror prob = new USACOsilver2013JanProb1Mirror();
		// USACOSilver2012Decprob3fence prob = new USACOSilver2012Decprob3fence();
		 USACOSilver2015FebProb1Censoring prob = new USACOSilver2015FebProb1Censoring( "KMP");
		 prob.Solver();
	   }
}
