package Sorting;

public class SelctionSort {
    public static <T extends Comparable<T>> void Sort(T[] array)
    {
        int n = array.length;
        int minindex=-1;
        for(int i=0; i<n-1;i++)
        {
            minindex = i;
            for(int j=i+1; j<n;j++)
            {
                if(array[minindex].compareTo(array[j])>0)
                {
                    minindex=j;
                }
            }
            T temp = array[i];
            array[i] = array[minindex];
            array[minindex] = temp;
        }
    }
}
