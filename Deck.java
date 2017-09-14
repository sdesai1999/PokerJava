import java.util.*;

/**
 * AP Computer Science Poker Project
 * a Deck of type Card
 * 
 * @author Saurav Desai 
 * @version 2/24/16
 */
public class Deck
{
    private ArrayList<Card> deck;
    //private int numOfCards = 52;
    
    /**
     * Constructor for objects of class Deck
     * creates a Deck and instantiaties 52 different cards
     * 
     * @param none
     */
    public Deck()
    {
        deck = new ArrayList<Card>(); // initializes the deck

        for (int i = 1; i <= 4; i++)
        {
            for (int j = 1; j <= 13; j++)
            {
                Card toAdd = new Card(i, j);
                deck.add(toAdd);
            }
        }
        
        // Cards have been created and added to Deck
    }

    /**
     * Method deckShuffle
     * shuffles the Deck using Collections.shuffle
     * puts the Cards into a random order
     *
     * @param none
     * @return none
     */
    public void deckShuffle()
    {
        Collections.shuffle(deck); 
        Collections.shuffle(deck); // shuffles the deck twice
    }
    
    /**
     * Method get
     * returns a specific Card that is in the Deck at a specific location
     *
     * @param int --> i
     * @return Card --> deck.get(i)
     */
    public Card get(int i)
    {
        return deck.get(i);
    }
    
    /**
     * Method getNumberOfCards
     * returns the number of Cards currently in the Deck
     *
     * @param none
     * @return int --> deck.size()
     */
    public int getNumberOfCards()
    {
        return deck.size();
    }
    
    /**
     * Method removeTopCard
     * removes the top card in the deck
     * returns the top card
     * used when the dealer is dealing cards to everyone
     *
     * @param none
     * @return Card --> c
     */
    public Card removeTopCard()
    {
        Card c = new Card(deck.get(0).getSuit(), deck.get(0).getValue());
        // creates a Card that is the same as the top Card in the Deck
        deck.remove(0); // removes the top Card
        return c; // returns a copy of the top Card
    }
    
    /**
     * Method getTheDeck
     * returns the entire deck
     *
     * @param none
     * @return ArrayList --> deck
     */
    public ArrayList getTheDeck()
    {
        return deck;
    }
}