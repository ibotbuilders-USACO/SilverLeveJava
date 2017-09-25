package Array;

import java.util.Arrays;

public class ArrayHW {

	public static void main(String args[]) {
		int i, count = 0, temp = 0;
		String[] wanted = { "Eric", "Josh" };
		boolean[] present = new boolean[wanted.length];
		int[] positions = new int[wanted.length];
		String[] names = { "Pete", "Josh", "Pete", "Pete", "Pete", "Pete", "John", "Pete", "Pete", "Eric"};
		for (i = 0; i < wanted.length; i++) {
			present[i] = presentFunc(names, wanted[i]);
			if (present[i] == true) {
				count++;
			}
			System.out.println(wanted[i] + " is present: " + Boolean.toString(present[i]));
		}
		String[] presentWanted = new String[count];
		for (i = 0; i < wanted.length; i++) 
		{
			if (present[i] == true) 
			{
				presentWanted[temp] = wanted[i];
				temp++;
			}

		}
		positions = indexFunc(presentWanted, names);
		for(i=0; i<presentWanted.length;i++)
		{
			System.out.println(presentWanted[i] + "'s index is " + positions[i]);
		}
	}

	public static boolean presentFunc(String[] names, String person) {
		int i;
		boolean here = false;
		for (i = 0; i < names.length; i++) {
			if (person.equals(names[i])) {
				here = true;
			}
		}
		return here;
	}
	
	public static int[] indexFunc(String[] wanted, String[] names)
		{
			int i , j;
			int[] index = new int[wanted.length];
			for(i = 0; i < wanted.length; i++)
			{
				for(j = 0; j < names.length; j++)
				{
					if(wanted[i].equals(names[j]))
					{
						index[i] = j;
					}
				}
			}
			return index;
		}
}
