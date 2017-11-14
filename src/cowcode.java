import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class cowcode {
	static char answer;
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("cowcode.in"));
		File file = new File("cowcode.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		int origLength, numTimes;
		String str;
		long index, currentLen;
		double temp;
		str = st.nextToken();
		origLength = str.length();
		index = Long.parseLong(st.nextToken());
		temp = (double)index/origLength;
		numTimes = (int)(Math.ceil((Math.log(temp))/(Math.log(2))));
		currentLen = (long) (origLength*Math.pow(2, numTimes));
		rotation(numTimes, str, index, currentLen, origLength);
		System.out.println(answer);
		out.print(answer);
		out.close();
	}
	public static void rotation(int numTimes, String str, long wantedPos, long currentLen, int origLength)
	{
		if(wantedPos <= origLength) 
		{
			answer = str.charAt((int)(wantedPos-1));
			return;
		}
		else if(wantedPos < currentLen/2)
		{
			rotation(numTimes-1, str, wantedPos, currentLen/2, origLength);
		}
		else if(wantedPos == (currentLen/2)+1)
		{
			rotation(numTimes-1, str, wantedPos-1, currentLen/2, origLength);
		}
		else
		{
			rotation(numTimes-1, str, (long)(wantedPos-1-(currentLen/2)), currentLen/2, origLength);
		}
	}

}
