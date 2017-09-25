package Array;
import java.util.Arrays;
public class TestArray {
	public static void main(String args[]) {
		int[] array1 = { 0};
		int[] array2 = { 0};
		int[] answer = {};
		int[] extra = {};
		answer = DiffElement(array1, array2);
		extra = extraArr(array1.length, array2.length);
		System.out.print(Arrays.toString(answer) + "; " + Arrays.toString(extra));
	}

	public static boolean Compare(int[] array1, int[] array2) 
	{
		boolean equal = true;
		if (array1.length != array2.length)
		{
			equal = false;
		} 
		else 
		{
			int Length = array1.length;
			int i;
			for (i = 0; i < Length; i++) 
			{
				if(array1[i] != array2[i])
				{
					equal = false;
					break;
				}
			}
		}

		return equal;
	}
	public static int[] DiffElement(int[] array1, int[] array2) 
	{
		
		int[] diff = new int[array1.length];
		
		int i, count = 0;
		for(i=0;i<array1.length; i++)
		{
			if(array1[i] != array2[i])
			{
				diff[count] = i;
				count++;
			}
			
		}
		int[] diffTrim = new int[count];
		System.arraycopy(diff, 0, diffTrim, 0, count);
		return diffTrim;
	}


	public static boolean Compare(char[] array1, char[] array2) {
		boolean equal = false;
		return equal;
	}
	public static int[] extraArr(int length1, int length2)
	{
		
		int arrDiff, i, count = 0;
		int[] answer = new int[Math.abs(length1-length2)];
		if(length1 >= length2)
		{
			arrDiff = length1 - length2;
			for(i=length2;i < length1; i++)
			{
				answer[count]= i;
				count++;
			}
		}
		else
		{
			arrDiff = length2 - length1;
			for(i=length1;i < length2; i++)
			{
				answer[count]= i;
				count++;
			}
		}
		
		return answer;
	}

}
