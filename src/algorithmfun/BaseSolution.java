package algorithmfun;

import java.util.ArrayList;

public class BaseSolution {
	protected void printArray(Object[] array)
	{
		if (array == null )
			System.out.println("empty array");
		for (Object element : array)
			System.out.print(element.toString()+" ");
		System.out.println();
	}
	
	protected void printArrayList(ArrayList<Object> list)
	{
		if (list == null)
			System.out.println("empty array list");
		printArray(list.toArray());
	}

}
