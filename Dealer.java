import java.util.*;

/**
 * AP Computer Science Poker Project
 * the Dealer is the one who controls the game, so he has all of the methods
 * 
 * @author Saurav Desai 
 * @version 2/24/16
 */
public class Dealer
{
    private ArrayList<Player> myTable;
    private final String[] STR_SUITS = new String[] { "Spades", "Clubs", "Hearts", "Diamonds" };
    private final String[] STR_VALUES = new String[] { "Ace", "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten", "Jack", "Queen", "King" };
    private int pot;
    private Deck myDeck = new Deck();
    private int players;
    private int dealersMoney;
    private int newPlayerCount;

    /**
     * Dealer Constructor
     * creates a new Dealer with the number of players initially playing
     *
     * @param int --> numPlayers
     */
    public Dealer(int numPlayers)
    {
        myTable = new ArrayList<Player>(); // list of Players
        players = numPlayers; // sets the number of Players
        pot = 0; // initially, the pot will be 0
        newPlayerCount = 1;

        for (int i = 0; i < players; i++) // adds every Player to the table
        {
            String str = "Player" + (i+1);
            Player p = new Player(str);
            myTable.add(p);
        }
    }

    /**
     * Method gatherBets
     * gets every Player to bet money, which will be added to the pot
     *
     * @param none
     * @return none
     */
    public void gatherBets()
    {
        for (int i = 0; i < players; i++)
        {
            int x = myTable.get(i).bet();
            System.out.println(myTable.get(i).getName() + " bets $" + x + "."); // Player(i) bets a random amount between 1 and 10
            pot += x; // the amount Player(i) bets is added to the pot
        }
        
        System.out.println();

        for (int i = 0; i < players; i++)
        {
            System.out.println(myTable.get(i).getName() + " has $" + myTable.get(i).getMyStack() + " left."); // prints everyone's money total
        }

        System.out.println();

        System.out.println("The pot is now worth $" + pot + ".");

        System.out.println();
    }

    /**
     * Method dealToEveryone
     * deals cards to all Players in an orderly fashion
     *
     * @param none
     * @return none
     */
    public void dealToEveryone()
    {
        myDeck.deckShuffle(); // shuffles the Deck of Cards

        for (int i = 0; i < 5; i++) // 5 Cards for everyone
        {
            for (int j = 0; j < players; j++) // gives everyone their "ith" card
            {
                myTable.get(j).takeCard(myDeck.removeTopCard()); // removes the top Card from the Deck and gives it to Player(i)
            }
        }
    }

    /**
     * Method displayHands
     * prints everyone's hands
     * in a real game of Poker, you can't see each other's hands, but in this version you will be able to do so
     *
     * @param none
     * @return none
     */
    public void displayHands()
    {
        for (int i = 0; i < players; i++)
        {
            myTable.get(i).sortHand();
            System.out.println(myTable.get(i).getName() + "'s hand: ");
            System.out.println(myTable.get(i).getMyHand()); // prints Player(i)'s hand
            System.out.println();
        }

        System.out.println();
    }

    /**
     * Method rejectCards
     * asks Players if they want to reject any of the cards they have
     *
     * @param none
     * @return none
     */
    public void rejectCards()
    {
        for (int i = 0; i < players; i++)
        {
            Scanner scan3 = new Scanner(System.in);
            System.out.print(myTable.get(i).getName() + ", would you like to reject any cards (yes or no)?: "); // asks if the Player(i) wants to remove any Cards
            String yesOrNo = scan3.nextLine();

            if (yesOrNo.equalsIgnoreCase("yes")) // if they want to remove Cards, following code will execute
            {
                Scanner scan4 = new Scanner(System.in);
                System.out.print("How many cards?: "); 
                String numCardsToRemove1 = scan4.nextLine();

                while (1 == 1)
                {
                    try // if they don't enter an integer
                    {
                        Integer.parseInt(numCardsToRemove1);
                        break;
                    }
                    catch (NumberFormatException n)
                    {
                        System.out.print("Please enter a proper integer: ");
                        numCardsToRemove1 = scan4.nextLine();
                    }
                }

                int numCardsToRemove = Integer.parseInt(numCardsToRemove1);

                while (numCardsToRemove > 3 || numCardsToRemove < 1) // if they say a number less than 1 or greater than 3
                {
                    System.out.print("Please enter a valid number of cards (1-3) to reject: ");
                    numCardsToRemove1 = scan4.nextLine();

                    while (1 == 1)
                    {
                        try // if they don't enter an integer
                        {
                            Integer.parseInt(numCardsToRemove1);
                            break;
                        }
                        catch (NumberFormatException n)
                        {
                            System.out.print("Please enter a proper integer: ");
                            numCardsToRemove1 = scan4.nextLine();
                        }
                    }

                    numCardsToRemove = Integer.parseInt(numCardsToRemove1);
                }

                System.out.println();

                for (int j = 0; j < numCardsToRemove; j++) // asks what Card to remove "numCardsToRemove" times
                {
                    Scanner scan2 = new Scanner(System.in);

                    System.out.print("What value (Ace, Five, Jack, etc.)?: ");
                    String valToRemove = scan2.nextLine();
                    System.out.println();
                    System.out.print("What suit (Diamonds, Spades, etc.)?: ");
                    String suitToRemove = scan2.nextLine();

                    int valIndex = -1;
                    for (int q = 0; q < STR_VALUES.length; q++)
                    {
                        if (STR_VALUES[q].equalsIgnoreCase(valToRemove)) // finds index of the value in the String array
                        {
                            valIndex = q;
                            break;
                        }
                    }

                    System.out.println();

                    while (valIndex == -1) // if they don't enter a valid Card value
                    {
                        System.out.print("Please enter a valid value: ");
                        valToRemove = scan2.nextLine();
                        System.out.println();

                        for (int k = 0; k < STR_VALUES.length; k++)
                        {
                            if (STR_VALUES[k].equalsIgnoreCase(valToRemove))
                            {
                                valIndex = k;
                                break;
                            }
                        }
                    }

                    int suitIndex = -1;
                    for (int q = 0; q < STR_SUITS.length; q++)
                    {
                        if (STR_SUITS[q].equalsIgnoreCase(suitToRemove)) // finds index of the suit in the String array
                        {
                            suitIndex = q;
                            break;
                        }
                    }

                    System.out.println();

                    while (suitIndex == -1) // if they don't enter a valid suit
                    {
                        System.out.print("Please enter a valid suit: ");
                        suitToRemove = scan2.nextLine();
                        System.out.println();

                        for (int k = 0; k < STR_SUITS.length; k++)
                        {
                            if (STR_SUITS[k].equalsIgnoreCase(suitToRemove))
                            {
                                suitIndex = k;
                                break;
                            }
                        }
                    }

                    Card theCardToRemove = new Card(suitIndex+1, valIndex+1); // creates a card to remove

                    while (myTable.get(i).hasCard(theCardToRemove) == false) // if the user enters an invalid Card, asks them to enter a valid one
                    {
                        System.out.println("Please enter a card that you actually have.");
                        System.out.print("What value (Ace, Five, Jack, etc.)?: ");
                        valToRemove = scan2.nextLine();
                        System.out.println();
                        System.out.print("What suit (Diamonds, Spades, etc.)?: ");
                        suitToRemove = scan2.nextLine();

                        valIndex = -1;
                        for (int k = 0; k < STR_VALUES.length; k++)
                        {
                            if (STR_VALUES[k].equalsIgnoreCase(valToRemove)) // finds index of the value in the String array
                            {
                                valIndex = k;
                            }
                        }

                        System.out.println();

                        while (valIndex == -1) // if the user enters an invalid Card value
                        {
                            System.out.print("Please enter a valid value: ");
                            valToRemove = scan2.nextLine();
                            System.out.println();

                            for (int k = 0; k < STR_VALUES.length; k++)
                            {
                                if (STR_VALUES[k].equalsIgnoreCase(valToRemove))
                                {
                                    valIndex = k;
                                    break;
                                }
                            }
                        }

                        suitIndex = -1;
                        for (int k = 0; k < STR_SUITS.length; k++)
                        {
                            if (STR_SUITS[k].equalsIgnoreCase(suitToRemove)) // finds index of the suit in the String array
                            {
                                suitIndex = k;
                            }
                        }

                        while (suitIndex == -1) // if the user enters an invalid suit
                        {
                            System.out.print("Please enter a valid suit: ");
                            suitToRemove = scan2.nextLine();
                            System.out.println();

                            for (int k = 0; k < STR_SUITS.length; k++)
                            {
                                if (STR_SUITS[k].equalsIgnoreCase(suitToRemove))
                                {
                                    suitIndex = k;
                                    break;
                                }
                            }
                        }

                        theCardToRemove = new Card(suitIndex+1, valIndex+1); // the card to remove
                    }

                    myTable.get(i).removeCard(theCardToRemove); // removes the Card
                    myTable.get(i).takeCard(myDeck.removeTopCard()); // player gets a new Card dealt to them

                    if ((j+1) != numCardsToRemove)
                    {
                        System.out.println("Next card:");
                        System.out.println();
                    }
                }
            }

            System.out.println();
        }

        System.out.println();
    }

    /**
     * Method determineWinner
     * determines the winner of the current round by using the handRanking
     * 
     * @param none
     * @return none
     */
    public void determineWinner()
    {
        int winner = -1;
        ArrayList<Integer> winnerList = new ArrayList<Integer>(); // for ties
        ArrayList<Integer> handRanks = new ArrayList<Integer>(); // hand ranks for each player
        ArrayList<Integer> highestCards = new ArrayList<Integer>(); // highest cards for players with ties

        for (int i = 0; i < players; i++)
        {
            handRanks.add(myTable.get(i).checkHandRank()); // adds everyone's rank to the List
        }

        int theRank = Integer.MIN_VALUE;
        for (int i = 0; i < players; i++)
        {
            if (handRanks.get(i) >= theRank) // finds the max rank
            {
                theRank = handRanks.get(i);
            }
        }

        int count = 0;

        for (int i = 0; i < players; i++)
        {
            if (handRanks.get(i) == theRank) // finds the amount of times the max rank appears
            {
                count++;
                winnerList.add(i);
            }
        }

        String rankThatWon = ""; // will assign whatever rank that won to rankThatWon

        if (theRank == 10)
        {
            rankThatWon = "Royal Flush";
        }
        else if (theRank == 9)
        {
            rankThatWon = "Straight Flush";
        }
        else if (theRank == 8)
        {
            rankThatWon = "Four of a Kind";
        }
        else if (theRank == 7)
        {
            rankThatWon = "Full House";
        }
        else if (theRank == 6)
        {
            rankThatWon = "Flush";
        }
        else if (theRank == 5)
        {
            rankThatWon = "Straight";
        }
        else if (theRank == 4)
        {
            rankThatWon = "Three of a Kind";
        }
        else if (theRank == 3)
        {
            rankThatWon = "Two Pair";
        }
        else if (theRank == 2)
        {
            rankThatWon = "One Pair";
        }
        else if (theRank == 1)
        {
            rankThatWon = "No Pair";
        }
        else
        {
            rankThatWon = "";
        }

        if (count > 1 && theRank != 10) // if there are ties, but not royalFlush ties
        {
            for (int i = 0; i < winnerList.size(); i++)
            {
                highestCards.add(myTable.get(winnerList.get(i)).getHighestCard(theRank)); // adds the highest Cards of everyone in the List
            }

            int maxCard = Integer.MIN_VALUE;

            for (int i = 0; i < highestCards.size(); i++)
            {   
                if (highestCards.get(i) > maxCard) // finds the highest Card in the List
                {
                    maxCard = highestCards.get(i);
                    winner = winnerList.get(i);
                }
            }

            System.out.println("The winner of this round is " + myTable.get(winner).getName() 
                + "! Congratulations! " + myTable.get(winner).getName() + " won with a " + rankThatWon + ".");
            System.out.println(myTable.get(winner).getName() + " won $" + pot + "!");
            System.out.println();
            myTable.get(winner).winRound(pot); // winner gets to keep the total pot
            pot = 0; // pot equals 0 after the Player takes it
        }

        if (count > 1 && theRank == 10) // if there are royalFlush ties (very, very, very rare)
        {
            if (pot % count != 0)
            {
                int amountToReduce = 0;
                amountToReduce = pot%count;
                pot -= (amountToReduce); // the dealer keeps left over money if the pot doesn't equally divide
                dealersMoney += amountToReduce;
            }

            int moneyToGive = pot/count; // the money that will be given to everyone who won

            for (int i = 0; i < winnerList.size(); i++)
            {
                myTable.get(winnerList.get(i)).winRound(moneyToGive);
            }

            System.out.println("There were multiple winners in this round by a Royal Flush. The pot of $" + pot + " was evenly distributed between:");
            for (int i = 0; i < winnerList.size(); i++)
            {
                System.out.println(myTable.get(winnerList.get(i)).getName());
            }

            System.out.println("They all won $" + moneyToGive + ".");
        }

        if (count == 1) // no ties
        {
            winner = winnerList.get(0);

            System.out.println("The winner of this round is " + myTable.get(winner).getName() + "! Congratulations! " 
                + myTable.get(winner).getName() + " won with a " + rankThatWon + ".");
            System.out.println(myTable.get(winner).getName() + " won $" + pot + "!"); // prints the winner, the rank which they won by, and the money they won
            System.out.println();
            myTable.get(winner).winRound(pot);
            pot = 0; // pot = 0 after Player takes it
        }

        System.out.println();
    }

    /**
     * Method printAmounts
     * prints the amount of money everyone has
     * 
     * @param none
     * @return none
     */
    public void printAmounts()
    {
        for (int i = 0; i < players; i++)
        {
            System.out.println(myTable.get(i).getName() + "  has $" + myTable.get(i).getMyStack() + "."); // prints Player(i)'s money
        }

        System.out.println();
    }

    /**
     * Method kickOut
     * if a Player's money is 0, they will be kicked out
     * 
     * @param none
     * @return none
     */
    public void kickOut()
    {
        int theirStack = -1;

        for (int i = 0; i < players; i++)
        {
            theirStack = myTable.get(i).getMyStack();

            if (theirStack == 0)
            {
                System.out.println(myTable.get(i).getName() + " is out of money and will be kicked out immediately.");
                myTable.remove(i);
                players = myTable.size();
                i--;
            }
        }

        players = myTable.size();

        System.out.println();
    }

    /**
     * Method invite
     * asks if anyone would like to join the game
     * maximum of one Player may join every round 
     * 
     * @param none
     * @return none
     */
    public void invite()
    {
        if (players < 6)
        {
            Scanner scan = new Scanner(System.in);
            System.out.print("Would one other person like to join (yes or no)?: ");
            String toJoin = scan.nextLine();

            if (toJoin.equalsIgnoreCase("yes")) // if another person wants to join, a new Player is added
            {
                String hisName = "NewPlayer" + (newPlayerCount);
                Player newPlayer = new Player(hisName);
                myTable.add(newPlayer);
                System.out.println("A new player was added.");
                newPlayerCount++;
            }
        }

        players = myTable.size();

        System.out.println();
    }

    /**
     * Method determineFinalWinner
     * creates an array of each Player's stacks, and finds the highest (winner)
     * 
     * @param none
     * @return none
     */
    public void determineFinalWinner()
    {
        int[] stacks = new int[players]; // 1D array of each Player's stack

        for (int i = 0; i < players; i++)
        {
            stacks[i] = myTable.get(i).getMyStack(); // Player(i)'s stack is added to the 1D array
        }

        int maxStack = Integer.MIN_VALUE;
        int gameWinner = -1;
        for (int i = 0; i < players; i++)
        {
            if (stacks[i] > maxStack) // finds the highest stack
            {
                maxStack = stacks[i];
                gameWinner = i;
            }
        }

        int maxStackCount = 0;
        ArrayList<Integer> gameWinnerList = new ArrayList<Integer>();
        for (int i = 0; i < players; i++) // finds how many times the highest stack appears
        {
            if (maxStack == stacks[i])
            {
                maxStackCount++;
                gameWinnerList.add(i);
            }
        }

        if (maxStackCount == 1) // no ties
        {
            System.out.println("The winner of this game of Poker is " + myTable.get(gameWinner).getName() + " with $" 
                + myTable.get(gameWinner).getMyStack() + ".");
        }
        else // ties
        {
            System.out.println("There were " + maxStackCount + " winners in this game of Poker. They were:");

            for (int i = 0; i < maxStackCount; i++)
            {
                System.out.println(myTable.get(gameWinnerList.get(i)).getName() + " with $" + myTable.get(gameWinnerList.get(i)).getMyStack() + ".");
            }

        }
    }

    /**
     * Method playGame
     * executes all of the above methods in a loop, until 1 Player is left, or until the users want toQuit
     * 
     * @param none
     * @return none
     */
    public void playGame()
    {
        while (true)
        {
            this.gatherBets();
            this.dealToEveryone();
            this.displayHands();
            this.rejectCards();
            this.displayHands();
            this.determineWinner();
            this.printAmounts();
            this.kickOut();

            if (players == 1) // if there is one Player left after someone gets kicked out, the game will end
            {
                break;
            }

            this.invite();

            // all of the above methods have been run

            System.out.println();
            Scanner scan = new Scanner(System.in);
            System.out.print("If you would like to quit now, type \"Quit\"... anything else to keep playing: ");
            String toQuit = scan.nextLine();
            System.out.println();
            System.out.println();

            if (toQuit.equalsIgnoreCase("Quit")) // game will end if the users wish toQuit
            {
                break;
            }

            for (int i = 0; i < players; i++)
            {
                myTable.get(i).getMyHand().clear(); // clears Players' hands after the round is over
            }

            if (myDeck.getNumberOfCards() < players*8) // creates a new Deck if the current Deck is running out (players*8 = players*3 + players*5)
            // everybody can reject 3 Cards, and everyone gets 5 Cards
            {
                myDeck = new Deck();
                System.out.println("A new deck is going to be used for the next round.");
                System.out.println();
            }

            System.out.println("------------------------------START OF THE NEW ROUND------------------------------");
            System.out.println();
            System.out.println();
        }

        this.determineFinalWinner();

        System.out.println();
        System.out.println("Thanks for playing!"); // END OF GAME
    }
}