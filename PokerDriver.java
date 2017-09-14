import java.util.*;

/**
 * AP Computer Science Poker Project
 * prints rules and asks how many players are going to play
 * creates new Dealer and calls for the Dealer to play the game
 * 
 * @author Saurav Desai 
 * @version 2/24/16
 */
public class PokerDriver
{
    public static void main(String[] args)
    {
        System.out.print("\f");

        System.out.println("Welcome to Poker!");
        System.out.println();
        System.out.println("This is the \"5 Card Draw\" version of Poker.");

        Scanner in = new Scanner(System.in);
        System.out.print("If you don't know how to play 5 Card Draw Poker, enter \"idk\": ");
        String howToPlay = in.nextLine();
        System.out.println();

        if (howToPlay.equalsIgnoreCase("idk")) // if they don't know how to play, prints rules
        {
            System.out.println("How to play:");
            System.out.println("You will bet, then you will each be dealt 5 cards.");
            System.out.println("You will be asked if you want to reject up to 3 of the cards you have.");
            System.out.println("Then the winner of the round will be determined, and the winner receives the pot.");
            System.out.println("Ties will be broken by whoever has the higher card that applies to the hand rank.");
            System.out.println();
        }

        System.out.print("If you don't know how the Hand Ranking works, enter \"idk\": ");
        howToPlay = in.nextLine();
        System.out.println();

        if (howToPlay.equalsIgnoreCase("idk")) // if they don't know how Hand Ranking works, prints Hand Ranking rules
        {
            System.out.println("Hand Ranking goes as follows (from lowest power, to highest power):");
            System.out.println("• No Pair: The lowest possible hand, five cards with no matching values.");
            System.out.println("• One Pair: Two cards of the same value, i.e. 2 Aces.");
            System.out.println("• Two Pair: Two sets of pairs, i.e. 2 Aces and 2 9s.");
            System.out.println("• Three of a Kind: Three cards of the same value, i.e. 3 Aces.");
            System.out.println("• Straight: Five cards with consecutive values, i.e. an Ace, 2, 3, 4, and 5.");
            System.out.println("• Flush: Five cards of the same suit, i.e. 5 cards that are Diamonds.");
            System.out.println("• Full House: A Three of a Kind and a One Pair, i.e. 3 Aces and 2 8s.");
            System.out.println("• Four of a Kind: Four cards of the same value, i.e. 4 Queens.");
            System.out.println("• Straight Flush: A Straight and a Flush, i.e. 5 Diamonds and a 4, 5, 6, 7, and 8.");
            System.out.println("• Royal Flush: The highest possible hand, 5 cards of the same suit and a 10, Jack,");
            System.out.println("  Queen, King, and Ace.");
            System.out.println();
        }

        System.out.println("Here are some reminders:"); // printing reminders
        System.out.println("• There can be between 2 and 6 players.");
        System.out.println("• Players start out with $100.");
        System.out.println("• Players will bet random amounts of money from $1 to $10.");
        System.out.println("• In this game, Aces are low unless you have a Royal Flush.");
        System.out.println("• Please enter proper information when asked to do so.");
        System.out.println();
        System.out.println("Let's play!");
        System.out.println();
        System.out.println();

        // rules have been printed above

        Scanner scan = new Scanner(System.in);
        System.out.print("How many players (integer format)?: ");
        String numPlayers1 = scan.nextLine();

        
        while (1 == 1)
        {
            try // if they don't enter an integer
            {
                Integer.parseInt(numPlayers1);
                break;
            }
            catch (NumberFormatException n)
            {
                System.out.print("Please enter a proper integer: ");
                numPlayers1 = scan.nextLine();
            }
        }
        

        int numPlayers = Integer.parseInt(numPlayers1);

        while (numPlayers > 6 || numPlayers < 2)
        {
            System.out.print("Please enter a valid number of players (2-6): ");
            numPlayers1 = scan.nextLine();

            while (1 == 1)
            {
                try // if they don't enter an integer
                {
                    Integer.parseInt(numPlayers1);
                    break;
                }
                catch (NumberFormatException n)
                {
                    System.out.print("Please enter a proper integer: ");
                    numPlayers1 = scan.nextLine();
                }
            }
            
            numPlayers = Integer.parseInt(numPlayers1);
        }

        System.out.println();
        System.out.println();

        Dealer theDealer = new Dealer(numPlayers); // creates new Dealer object reference
        theDealer.playGame(); // calls for playGame method in the Dealer class
    }
}