package Array;
import java.util.Arrays;
public class passReference {

	public static void main(String[] args) {
		int[] array = {1,2,3,4,5};
		change(array);
		System.out.println(Arrays.toString(array));
	}
	public static void change(int[] array)
	{
		array[0] = 100;
	}

}
