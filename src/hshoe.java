import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class hshoe {
		static int size, answer = 0;
		static int[][] shoes;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader f = new BufferedReader(new FileReader("hshoe.in"));
		File file = new File("hshoe.out");
		PrintWriter out = new PrintWriter(file);
		size = Integer.parseInt(f.readLine());
		shoes = new int[size][size];
		String temp;
		int i,j;
		for(i=0;i<size;i++)
		{
			temp = f.readLine();
			for(j=0;j<size;j++)
			{
				if(temp.charAt(j) == '(')
				{
					shoes[i][j] = 0;
				}
				else if(temp.charAt(j) == ')')
				{
					shoes[i][j] = 1;
				}
			}
		}
		System.out.println(answer);
		out.print(answer);
		out.close();
	}

}
