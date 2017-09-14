import java.util.*;

/**
 * AP Computer Science Poker Project
 * Players have their own hand of type Card
 * Players have their own amount of money
 * 
 * @author Saurav Desai 
 * @version 2/24/16
 */
public class Player
{
    private ArrayList<Card> myHand;
    private String name;
    private int myStack;

    /**
     * Constructor for objects of class Player
     * creates a new Player with a name, 100 dollars, and a new hand of Cards
     * 
     * @param String --> tmpName
     */
    public Player(String tmpName)
    {
        name = tmpName;
        myStack = 100;
        myHand = new ArrayList<Card>();
    }

    /**
     * Method getName
     * returns the name of the Player
     *
     * @param none
     * @return String --> name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Method takeCard
     * the Player takes a card that is given when takeCard() is called
     *
     * @param Card --> aCard
     * @return none
     */
    public void takeCard(Card aCard)
    {
        myHand.add(aCard); // adds the Card that is the parameter
    }

    /**
     * Method removeCard
     * when called, it removes a certain card when the dealer asks if Players want to reject any Cards
     *
     * @param Card --> aCard
     * @return none
     */
    public void removeCard(Card aCard)
    {
        int suit = aCard.getSuit(); // suit of the Card to remove
        int value = aCard.getValue(); // value of the Card to remove

        for (int i = 0; i < myHand.size(); i++)
        {
            if ((myHand.get(i).getSuit() == suit) && (myHand.get(i).getValue() == value)) // searches for the Card
            {
                myHand.remove(i); // removes the Card
                break;
            }
        }
    }

    /**
     * Method bet
     * generates a random int between 1 and 10
     * Player will bet that much, and it will be subtracted from their total
     *
     * @param none
     * @return int --> toBet
     */
    public int bet()
    {
        Random r = new Random();
        int toBet = r.nextInt(10) + 1; // generates random int from 1 to 10
        while (toBet > myStack) // if toBet is more than the current stack, more ints will be generated until it's valid
        {
            Random r1 = new Random();
            toBet = r1.nextInt(10) + 1; // generates random int from 1 to 10
        }
        myStack -= toBet; // subtracts toBet from the current stack
        return toBet;
    }

    /**
     * Method getMyStack
     * returns the amount of money the Player has
     *
     * @param none
     * @return int --> myStack
     */
    public int getMyStack()
    {
        return myStack;
    }

    /**
     * Method getMyHand
     * returns the Player's hand of type Card
     *
     * @param none
     * @return ArrayList --> myHand
     */
    public ArrayList getMyHand()
    {
        return myHand;
    }

    /**
     * Method winRound
     * if the Player wins the round, the pot is added to their stack
     *
     * @param int --> moneyWon
     * @return none
     */
    public void winRound(int moneyWon)
    {
        myStack += moneyWon;
    }

    /**
     * Method hasCard
     * checks to see if the Player has a certain Card in their current hand
     *
     * @param Card --> c
     * @return boolean
     */
    public boolean hasCard(Card c)
    {
        int cSuit = c.getSuit();
        int cValue = c.getValue();
        
        int currentSuit = -1;
        int currentValue = -1;
        
        for (int i = 0; i < myHand.size(); i++)
        {
            currentSuit = myHand.get(i).getSuit();
            currentValue = myHand.get(i).getValue();
            
            if (cSuit == currentSuit && cValue == currentValue) // checks if they have the Card with the same value and suit
            {
                return true; // returns true if they have Card c
            }
        }
        
        return false; // returns false if they don't have Card c
    }
    
    /**
     * Method sortHand
     * uses the compareTo method in the Card class to sort the PLayer's hand in ascending order by value
     * uses Collections.sort
     * 
     * @param none
     * @return none
     */
    public void sortHand()
    {
        Collections.sort(myHand);
    }

    // HAND RANKING BELOW!!!!!!!

    /**
     * Method onePair
     * checks to see if the Player's current hand ranks as a onePair
     *
     * @param none
     * @return boolean 
     */
    public boolean onePair()
    {
        this.sortHand(); // sorts the hand using sortHand()

        for (int i = 0; i < myHand.size() - 1; i++)
        {
            if (myHand.get(i).getValue() == myHand.get(i+1).getValue()) // checks myHand for back to back equal values
            {
                return true; // if there are back to back equal values, method returns true
            }
        }

        return false; // returns false if there aren't any back to back equal values
    }

    /**
     * Method twoPair
     * checks to see if the Player's current hand ranks as a twoPair
     *
     * @param none
     * @return boolean
     */
    public boolean twoPair()
    {
        this.sortHand(); // sorts the hand using sortHand()

        int nextPos = -1;
        int theValue = -1;

        for (int i = 0; i < myHand.size() - 1; i++)
        {
            if (myHand.get(i).getValue() == myHand.get(i+1).getValue()) // finds the first pair
            {
                nextPos = i+2; // sets next pos to i+2, so the next loop starts to look after the current onePair
                theValue = myHand.get(i).getValue();
                break;
            }
        }

        if (nextPos == -1) // if there is no first pair, method returns false
        {
            return false;
        }

        for (int i = nextPos; i < myHand.size() - 1; i++)
        {
            if (myHand.get(i).getValue() == myHand.get(i+1).getValue() && myHand.get(i).getValue() != theValue)
            {
                return true; // if another onePair is found, method returns true
            }
        }

        return false; // if no other onePair is found, method returns false
    }

    /**
     * Method threeOfAKind
     * checks to see if the Player's current hand ranks as a threeOfAKind
     *
     * @param none
     * @return boolean
     */
    public boolean threeOfAKind()
    {
        this.sortHand(); // sorts the hand using sortHand()

        int currentValue = -1; // current value
        int nextValue = -1; // value after that
        int thirdValue = -1; // value after the last value

        for (int i = 0; i < myHand.size() - 2; i++)
        {
            currentValue = myHand.get(i).getValue();
            nextValue = myHand.get(i+1).getValue();
            thirdValue = myHand.get(i+2).getValue();

            if (currentValue == nextValue && currentValue == thirdValue)
            {
                return true; // returns true if 3 consecutive values in the sorted hand are equal
            }
        }

        return false; // if no 3 equal values are found, method returns false
    }

    /**
     * Method straight
     * checks to see if the Player's current hand ranks as a straight
     *
     * @param none
     * @return boolean
     */
    public boolean straight()
    {
        this.sortHand(); // sorts the hand using sortHand()

        int currentValue = -1;
        int nextValue = -1;

        for (int i = 0; i < myHand.size() - 1; i++)
        {
            currentValue = myHand.get(i).getValue();
            nextValue = myHand.get(i+1).getValue();

            if (nextValue != currentValue + 1) // checks to see if each consecutive value isn't 1 greater than the last
            {
                return false; // returns false if each consecutive value isn't 1 greater than the last
            }
        }

        return true; // returns true if each consecutive value is 1 greater than the last
    }

    /**
     * Method flush
     * checks to see if the Player's current hand ranks as a flush
     *
     * @param none
     * @return boolean
     */
    public boolean flush()
    {
        this.sortHand(); // sorts the hand using sortHand()

        int currentSuit = -1;
        int nextSuit = -1;

        for (int i = 0; i < myHand.size() - 1; i++)
        {
            currentSuit = myHand.get(i).getSuit();
            nextSuit = myHand.get(i+1).getSuit();

            if (currentSuit != nextSuit) // checks to see if the current suit isn't the same as the next suit
            {
                return false; // returns false if the current suit isn't the same as the next suit
            }
        }

        return true; // if all of the suits are the same, method returns true
    }

    /**
     * Method fullHouse
     * checks to see if the Player's current hand ranks as a fullHouse
     *
     * @param none
     * @return boolean
     */
    public boolean fullHouse()
    {
        this.sortHand(); // sorts the hand by using sortHand()
        
        int threeOfAKindNum = -1;
        int currentValue = -1; // current value
        int nextValue = -1; // value after that
        int thirdValue = -1; // value after the previous one
        
        for (int i = 0; i < myHand.size() - 2; i++)
        {
            currentValue = myHand.get(i).getValue();
            nextValue = myHand.get(i+1).getValue();
            thirdValue = myHand.get(i+2).getValue();

            if (currentValue == nextValue && currentValue == thirdValue) 
            {
                threeOfAKindNum = currentValue;
                break; // if there is a threeOfAKind, threeOfAKindNum is set to be the current value, and the loop is exited
            }
        }
        
        if (threeOfAKindNum == -1) // if threeOfAKindNum wasn't changed, the method returns false
        {
            return false;
        }
        
        for (int i = 0; i < myHand.size() - 1; i++)
        {
            if (myHand.get(i).getValue() == myHand.get(i+1).getValue() && myHand.get(i).getValue() != threeOfAKindNum) 
            {
                return true; // returns true if a onePair is found that doesn't have the same value as the threeOfAKind
            }
        }
        
        return false; // if there was no onePair, method returns false
    }

    /**
     * Method fourOfAKind
     * checks to see if the Player's current hand ranks as a fourOfAKind
     *
     * @param none
     * @return boolean
     */
    public boolean fourOfAKind()
    {
        this.sortHand(); // sorts the hand by using sortHand

        int currentValue = -1; // current value
        int nextValue = -1; // the value after that
        int thirdValue = -1; // the value after the previous
        int fourthValue = -1; // the value after that

        for (int i = 0; i < myHand.size() - 3; i++)
        {
            currentValue = myHand.get(i).getValue();
            nextValue = myHand.get(i+1).getValue();
            thirdValue = myHand.get(i+2).getValue();
            fourthValue = myHand.get(i+3).getValue();

            if (currentValue == nextValue && currentValue == thirdValue && currentValue == fourthValue)
            {
                return true; // returns true if the currentValue is eaual to the next three values
            }
        }

        return false; // returns false if there is are no 4 equal values
    }

    /**
     * Method straightFlush
     * checks to see if the Player's current hand ranks as a straightFlush
     *
     * @param none
     * @return boolean
     */
    public boolean straightFlush()
    {
        this.sortHand(); // sorts the hand using sortHand()

        boolean a = this.straight(); // true or false depending on whether straight is true or false
        boolean b = this.flush(); // true or false depending on whether flush is true or false

        return a && b; // returns true only if straight and flush are both true, false otherwise
    }

    /**
     * Method royalFlush
     * checks to see if the Player's current hand ranks as a royalFlush
     *
     * @param none
     * @return boolean
     */
    public boolean royalFlush()
    {
        this.sortHand(); // sorts the hand using sortHand()

        boolean a = this.flush(); // true or false depending on whether flush is true or false
        boolean b = false;

        int card1 = myHand.get(0).getValue(); // value of card1
        int card2 = myHand.get(1).getValue(); // value of card2
        int card3 = myHand.get(2).getValue(); // value of card3
        int card4 = myHand.get(3).getValue(); // value of card4
        int card5 = myHand.get(4).getValue(); // value of card5

        if (card1 == 1 && card2 == 10 && card3 == 11 && card4 == 12 && card5 == 13)
        {
            b = true; // if each card in the hand is in the order and has the value of a royalFlush, 
        }

        return a && b; // method returns true only if a and b are true, false otherwise
    }

    /**
     * Method noPair
     * checks to see if the Player's current hand ranks as a noPair
     *
     * @param none
     * @return boolean
     */
    public boolean noPair()
    {
        boolean a = this.royalFlush();
        boolean b = this.straightFlush();
        boolean c = this.fourOfAKind();
        boolean d = this.fullHouse();
        boolean e = this.flush();
        boolean f = this.straight();
        boolean g = this.threeOfAKind();
        boolean h = this.twoPair();
        boolean i = this.onePair();

        if (a==false && b==false && c==false && d==false && e==false && f==false && g==false && h==false && i==false)
        {
            return true; // if none of the above handRanks are true, method returns true
        }

        return false; // returns false if one of the above handRanks is true
    }    
    
    /**
     * Method checkHandRank
     * method uses the handRanks above to check which one applies to the Player's current hand
     * the highest possible rank is royalFlush, which is a 10
     * the lowest possible rank is noPair, which is a 1
     *
     * @param none
     * @return int
     */
    public int checkHandRank()
    {
        if (this.royalFlush() == true)
        {
            return 10; // royalFlush is a 10
        }
        else if (this.straightFlush() == true)
        {
            return 9; // straightFlush is a 9
        }
        else if (this.fourOfAKind() == true)
        {
            return 8; // fourOfAKind is an 8
        }
        else if (this.fullHouse() == true)
        {
            return 7; // fullHouse is a 7
        }
        else if (this.flush() == true)
        {
            return 6; // flush is a 6
        }
        else if (this.straight() == true)
        {
            return 5; // straight is a 5
        }
        else if (this.threeOfAKind() == true)
        {
            return 4;  // threeOfAKind is a 4
        }
        else if (this.twoPair() == true)
        {
            return 3; // twoPair is a 3
        }
        else if (this.onePair() == true)
        {
            return 2; // onePair is a 2
        }
        else if (this.noPair() == true)
        {
            return 1; // noPair is a 1
        }
        else 
        {
            return -1; // will never execute
        }
    }

    /**
     * Method getHighestCard
     * used for breaking ties
     * finds the highest card in each handRank scenario
     * can't have ties in threeOfAKind and fourOfAKind
     * royalFlush ties can't be broken, so Players split the pot
     *
     * @param int --> a
     * @return int
     */
    public int getHighestCard(int a)
    {
        this.sortHand(); // sorts the hand by using sortHand()
        int max = -1;

        if (a == 1) // noPair ties
        {
            for (int i = 0; i < myHand.size(); i++)
            {
                if (myHand.get(i).getValue() >= max)
                {
                    max = myHand.get(i).getValue();
                }
            }

            return max; // highest noPair card
        }
        else if (a == 2) // onePair ties
        {
            for (int i = 0; i < myHand.size() - 1; i++)
            {
                if (myHand.get(i).getValue() == myHand.get(i+1).getValue())
                {
                    max = myHand.get(i).getValue();
                    break;
                }
            }

            return max; // highest onePair card
        }
        else if (a == 3) // twoPair ties
        {
            int firstPos = -1;

            for (int i = 0; i < myHand.size() - 1; i++)
            {
                if (myHand.get(i).getValue() == myHand.get(i+1).getValue())
                {
                    max = myHand.get(i).getValue();
                    firstPos = i+2;
                    break;
                }
            }

            for (int i = firstPos; i < myHand.size() - 1; i++)
            {
                if (myHand.get(i).getValue() == myHand.get(i+1).getValue() && myHand.get(i).getValue() >= max)
                {
                    max = myHand.get(i).getValue();
                    break;
                }
            }

            return max; // highest twoPair card
        }
        else if (a == 5) // straight ties
        {
            max = myHand.get(4).getValue();
            return max; // highest straight card
        }
        else if (a == 6) // flush ties
        {
            for (int i = 0; i < myHand.size(); i++)
            {
                if (myHand.get(i).getValue() >= max)
                {
                    max = myHand.get(i).getValue();
                }
            }

            return max; // highest flush card
        }
        else if (a == 7) // fullHouse ties
        {
            for (int i = 0; i < myHand.size(); i++)
            {
                if (myHand.get(i).getValue() >= max)
                {
                    max = myHand.get(i).getValue();
                }
            }

            return max; // highest fullHouse card
        }
        else if (a == 9) // straightFlush ties
        {
            for (int i = 0; i < myHand.size(); i++)
            {
                if (myHand.get(i).getValue() >= max)
                {
                    max = myHand.get(i).getValue();
                }
            }

            return max; // highest straightFlush card
        }
        else if (a == 10) // royalFlush ties --> they divide the pot amongst themselves (can't break the tie)
        {
            return 0;
        }
        else 
        {
            return -1; // will never execute
        }
    }
}