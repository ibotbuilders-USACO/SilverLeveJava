

import java.util.Scanner;
public class while1
{
    public static void main(String [] args)
    {
        Scanner scan = new Scanner(System.in);
        int test = 0, count = 0;
        int numbers;
        double total = 0;
        System.out.println("Enter your test scores.");
        test = scan.nextInt();
        if(test == -10)
        {
        	System.out.println("Your test score is 0");
        }
        else
        {
        	total+=test;
        	count++;
        	while (test != -10)
        	{
        		System.out.println("Enter your test scores.");
        		test = scan.nextInt();
        		total+=test;
        		count++;
        	}
        
        	count--;
        	total+=10;
        	total/=1.0*count;
        	System.out.println(total);
        }
        
    }
}