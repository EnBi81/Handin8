package median;

import java.io.*;
import java.util.*;

public class Median {

    // Add your private fields here
    private TreeSet<Integer> setSmall = new TreeSet<>();
    private TreeSet<Integer> setHigh = new TreeSet<>();

    public void add(int x) {

        if(setSmall.size() == 0) //Our very first item goes to the small treeset
        {
            setSmall.add(x);
        }
        else if(setSmall.size() == setHigh.size()) //when the two array sizes are equal
        {                                          //we make this move
            if(setSmall.last() < x)
                setHigh.add(x);
            else setSmall.add(x);
        }
        else if(setSmall.size() < setHigh.size()) //A bit of a logistic here
        {
            int highMin = setHigh.first();
            if(highMin < x)
            {
                setHigh.remove(highMin);
                setSmall.add(highMin);
                setHigh.add(x);
            }
            else setSmall.add(x);
        }
        else //Same logistic but in the opposite direction
        {
            int smallMax = setSmall.last();

            if(smallMax > x)
            {
                setSmall.remove(smallMax);
                setHigh.add(smallMax);
                setSmall.add(x);
            }
            else setHigh.add(x);

        }
    }

    public int median() {

        // Here we return the median value
        return setSmall.size() <= setHigh.size() ? setHigh.first() : setSmall.last();
    }
}
