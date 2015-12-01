/*
 * Purpose: Data Structure and Algorithms Lab 2 Problem 1 & 2
 * Status: Complete and thoroughly tested/Incomplete/ Barely started
 * Last update: 08/21/15
 * Submitted:  08/23/15
 * Comment: test suite and sample run attached
 * @author: John Doe
 * @version: 2015.08.21
 */

// ********************************************************
// Interface ListInterface for the ADT list.
// *********************************************************
public interface ListInterface<T>
{
    boolean isEmpty();
    int size();
    void add(int index, T item)
    throws ListIndexOutOfBoundsException;
    Object get(int index)
    throws ListIndexOutOfBoundsException;
    void remove(int index)
    throws ListIndexOutOfBoundsException;
    void removeAll();
