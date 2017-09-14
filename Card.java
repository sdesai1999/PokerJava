import java.util.*;

/**
 * AP Computer Science Poker Project
 * constructor creats new card that will be put in a deck of 52 cards
 * methods that allow for getting the suit and value
 * 
 * @author Saurav Desai 
 * @version 2/24/16
 */
public class Card implements Comparable<Card>
{
    private int suit;
    private int value;
    private String strVal;
    private String strSuit;
    private final String[] STR_SUITS = new String[] { "Spades", "Clubs", "Hearts", "Diamonds" };
    private final String[] STR_VALUES = new String[] { "Ace", "Two", "Three", "Four", "Five", "Six", "Seven",
                                                       "Eight", "Nine", "Ten", "Jack", "Queen", "King" };
                                                       
    // two arrays, one for value and one for suit, have been created so each card has a String value and String suit

    /**
     * Constructor for objects of class Card
     * creates a new card with a value, suit, String value, and String suit
     * 
     * @param int, int --> tmpSuit, tmpVal
     */
    public Card(int tmpSuit, int tmpVal)
    {
        suit = tmpSuit;
        value = tmpVal;
        strVal = STR_VALUES[value-1]; // assigns a String for the card's value from the array above
        strSuit = STR_SUITS[suit-1]; // assigns a String for the card's suit from the array above
    }
   
    
    /**
     * Method getValue
     * returns the value of the card
     *
     * @param none
     * @return int --> value
     */
    public int getValue()
    {
        return value;
    }
    
    /**
     * Method getSuit
     * returns the suit of the card
     *
     * @param none
     * @return int --> suit
     */
    public int getSuit()
    {
        return suit;
    }
    
    /**
     * Method compareTo
     * allows for Collections.sort to sort the hands in increasing order by value
     *
     * @param Card --> other
     * @return int --> two.compareTo(first)
     */
    public int compareTo(Card other)
    {
        Integer second = value; // second value
        Integer first = other.getValue(); // first value
        return second.compareTo(first);
    }
    
    /**
     * Method toString
     * allows for the card to be printed as a String rather than a memory location
     *
     * @param none
     * @return String
     */
    public String toString()
    {
        return strVal + " of " + strSuit; // i.e. Ace of Spades
    }
}