import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class cowfind {
	static int possible = 0, length, numHinds = 0;
	static String grass;
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("cowfind.in"));
		File file = new File("cowfind.out");
		PrintWriter out = new PrintWriter(file);
		grass = f.readLine();
		length = grass.length();
		find();
		System.out.println(possible);
		out.print(possible);
		out.close();
	}
	public static void find()
	{
		int i;
		for(i=0;i<length-1;i++)
		{
			if(grass.charAt(i) == '(')
			{
				if(grass.charAt(i+1) == '(')
				{
					numHinds++;
				}
			}
			if(grass.charAt(i) == ')')
			{
				if(grass.charAt(i+1) == ')')
				{
					possible+= numHinds;
				}
			}
		}
	}

}
