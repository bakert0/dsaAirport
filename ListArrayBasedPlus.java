/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Complete and thoroughly tested
 * Last update: 11/27/15
 * Submitted:  12/07/15
 * Comment: Improved version of ListArrayBased class from Lab2. Added Generic Type.
 * @author: Timothy Baker
 * @version: 1.0
 */

public class ListArrayBasedPlus<T> extends ListArrayBased<T> {

    //public ListArrayBased decoratedList;

    //constructor simply invokes the super's constructor
    public ListArrayBasedPlus() {
        super();
    }

    //reverses the list
    public void reverse() {
        for(int i = 0; i < numItems/2; i++) {
            T temp = items[i];
            items[i] = items[numItems - i - 1];
            items[numItems - i - 1] = temp;
        }
    }
    //toString method
    public String toString() {
        String returnString = "List of size " + numItems + " has the following items : ";
        for(T item: items) {
            if(item != null)
                returnString += item + " ";
        }
        return returnString.trim();
    }

    //resizes the array when it gets too big
    public void resize() {
        T[] biggerItems = (T[]) new Object[(int)(numItems * 1.5)];
        for(int i = 0; i < numItems; i++) {
            biggerItems[i] = items[i];
        }
        items = biggerItems;

    }

    //overrides the super class's add method
    public void add(int index, T item) {
        if(items.length == numItems)
            resize();
        super.add(index, item);
    }
