/*
 * Purpose: Data Structure and Algorithms Lab 6 Problem 1 (preLab)
 * Status: Complete and thoroughly tested
 * Last update: 10/07/2015
 * Submitted:  10/12/2015
 * Comment: Implementation of the Queue ADT using an array as
 * the underlying data structure
 * @author: Timothy Baker
 * @version: 1.0
 */

public class QueueArrayBased<T> implements QueueInterface<T> {

    protected T[] items;
    protected int front;
    protected int rear;
    protected int numItems;

    public QueueArrayBased() {
        items = (T[]) new Object[3];
        front = 0;
        rear = items.length - 1;
        numItems = 0;
    }

    //returns true if the queue is empty
    public boolean isEmpty() {
        return numItems == 0;
    }

    //removes all items in the queue
    public void dequeueAll() {
        items = (T[]) new Object[3];
        front = 0;
        rear = items.length - 1;
        numItems = 0;
    }

    //adds an item to the back of the queue
    public void enqueue(T newItem) throws QueueException {
        //checks for when the array is full
        if(numItems == items.length)
            resize();
        //% accounts for when the rear hits the end of the unfilled array
        rear = (rear + 1) % items.length;
        items[rear] = newItem;
        numItems++;
    }

    //returns and removes the front item of the queue
    public T dequeue() throws QueueException {
        T returnItem;
        //if the queue is empty, throw an error
        if(numItems == 0)
            throw new QueueException("QueueException thrown from dequeue: Queue is empty!");
        returnItem = items[front];
        items[front] = null; //remove the item from the queue and decremenet front
        front = (front + 1) % items.length;
        numItems--;
        return returnItem;
    }

    //returns next item of the queue without changing the queue
    public T peek() throws QueueException {
        if(numItems == 0)
            throw new QueueException("QueueException thrown from peek: Queue is empty!");
        return items[front];
    }

    //returns a string representation of the queue from front to back
    public String toString() {
        String returnString = "";
        if(numItems == 0)
            returnString = null;
        for(int i = 0; i < numItems; i++) {
            returnString += items[(i + front) % items.length].toString() + " ";
        }
        return returnString;
    }

    //resizes the underlying array when necessary
    protected void resize() {
        T[] freshArray = (T[]) new Object[(int)(items.length * 1.5)];

        for(int i = 0; i < items.length; i++) {
            freshArray[i] = items[(front + i) % items.length];
        }
        front = 0;
        rear = items.length - 1;
        items = freshArray;
    }
}
