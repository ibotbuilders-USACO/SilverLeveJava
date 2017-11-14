import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class greetings {
	static int BNumMoves, ENumMoves;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("greetings.in"));
		File file = new File("greetings.out");
		PrintWriter out = new PrintWriter(file);
		StringTokenizer st = new StringTokenizer(f.readLine());
		BNumMoves = Integer.parseInt(st.nextToken());
		ENumMoves = Integer.parseInt(st.nextToken());
	}

}
