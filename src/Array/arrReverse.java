package Array;
import java.util.Arrays;
public class arrReverse {
	public static void main(String[] args)
	{
		int[] array = {1,2,3,4,5,6,7,8,9};
		reverse(array, array.length);
		System.out.println(Arrays.toString(array));
	}
	public static void reverse(int[] array, int length)
	{
		int temp, i;
		for(i=0;i<(length+1)/2;i++)
		{
			temp = array[i];
			array[i] = array[length-1-i];
			array[length-1-i] = temp;
		}
	}
}
