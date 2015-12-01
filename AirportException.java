/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Complete
 * Last update: 11/27/15
 * Submitted: 12/07/15
 * Comment: Airport Exception is thrown when the user breaks the rules of an airport. An excample of when this exception is thrown is when the user fails to provide unique information where necessary (e.g. runway names).
 * @author: Timothy Baker
 * @version: 1.0
 */

public class AirportException extends RuntimeException
{
    public AirportException(String s)
    {
        super(s);
    }
}
