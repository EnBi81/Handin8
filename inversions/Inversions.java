package inversions;

import java.io.*;
import java.util.*;

public class Inversions
{
  public int countInversions(ArrayList<Integer> input)
  {
    //basically a merge sort from slides with 2 more added lines
     divide(input.toArray(new Integer[0]), 0, input.size() - 1);
     return inversion;
  }
  public int inversion = 0;
  public void divide(Integer[] array, int l, int r)
  {
    if(l < r)
    {
      int m = (l + r) / 2;
      divide(array, l, m);
      divide(array, m + 1, r);
      mergeAndCount(array, l, m, r);
    }
  }
  public void mergeAndCount(Integer[] array, int p, int q, int r)
  {
    int n1 = q - p + 1;
    int n2 = r - q;

    Integer[] L = new Integer[n1];
    Integer[] M = new Integer[n2];

    for (int i = 0; i < n1; i++)
      L[i] = array[p + i];
    for (int i = 0; i < n2; i++)
      M[i] = array[q + i + 1];

    int i, j, k = p;
    i = j = 0;

    while(i < n1 && j < n2)
    {
      if(L[i] <= M[j])
        array[k] = L[i++];
      else
      {
        inversion += n1 - i;
        array[k] = M[j++];
      }
      k++;
    }

    while(i<n1)
      array[k++] = L[i++];

    while(j < n2)
      array[k++] = M[j++];
  }
}


