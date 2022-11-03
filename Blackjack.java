import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("How many games would you like to play?");
        int numGames = input.nextInt();
        int currentGame = 1;
        
        input.nextLine();

        while (currentGame <= numGames) {
            initGame(currentGame, numGames);

            // Array of suits
            String[] suits = {"of Hearts", "of Diamonds", "of Clubs", "of Spades"};
            
            // List of player options
            List<Object> player = playerHand(suits, rand);
            int pTotal = (int) player.get(4);

            // List of dealer options
            List<Object> dealer = dealerHand(suits, rand);
            int dealerCard1 = (int) dealer.get(0);
            String dealerSuit1 = (String) dealer.get(1);
            int dealerCard2 = (int) dealer.get(2);
            String dealerSuit2 = (String) dealer.get(3);
            int dTotal = (int) dealer.get(4);


            input.nextLine();

            boolean repeat = true;
            while (repeat) {
                
                if ( pTotal > 21 ) {
                    System.out.println("You busted! Dealer wins.");
                    break;
                }
                else if ( pTotal == 21 ) {
                    System.out.println("Blackjack!");
                }
                
                
                
                System.out.println("Please choose 'Hit' or 'Stand':");
                String playerOption = input.nextLine();
                System.out.println();
                
                // If player chooses hit, deal extra card
                if ( playerOption.toLowerCase().equals("h") || playerOption.toLowerCase().equals("hit")) {
                    int playerCard3 = rand.nextInt(11) + 1;
                    int pSuit3 = rand.nextInt(suits.length);
                    String playerSuit3 = suits[pSuit3];
                    System.out.printf("Dealt: %s%n", playerCard3, playerSuit3);
                    pTotal += playerCard3;
                    System.out.printf("Your hands value: %d%n", pTotal);
                    System.out.println();
                    if ( pTotal > 21 ) {
                        System.out.println("You busted! Dealer wins.");
                        break;
                    }
                }
                else if ( playerOption.toLowerCase().equals("s") || playerOption.toLowerCase().equals("stand")) {
                    repeat = false;
                }
                else {
                    System.out.println("Please enter hit or stand.");
                }
            }
            while ( dTotal < 17 && dTotal < 21 ) {
                int dealerCard3 = rand.nextInt(11) + 1;
                int dSuit3 = rand.nextInt(suits.length);
                String dealerSuit3 = suits[dSuit3];
                dTotal += dealerCard3;
                System.out.println("Dealer's hand:");
                checkAce(dealerCard1, dealerSuit1);
                checkAce(dealerCard2, dealerSuit2);
                checkAce(dealerCard3, dealerSuit3);
                System.out.printf("Value: %d%n", dTotal);
                System.out.println();
            }
            if ( dTotal > 21 ) {
                System.out.println("Dealer busted! You win!");
                input.close();
                break;
            }       
            System.out.println("Final Results:");
            System.out.printf("Your Hand: %d%n", pTotal);
            System.out.printf("Dealer Hand: %d%n", dTotal);
            
            if ( (dTotal > pTotal) && (dTotal <= 21) && (pTotal < 21) ) {
                System.out.println("Dealer Wins!");
            }
            if ( (dTotal < pTotal) && (dTotal <= 21) && (pTotal < 21) ) {
                System.out.println("You Win!");
            }
            if ( (dTotal == pTotal) && (dTotal <= 21) && (pTotal < 21) ) {
                System.out.println("Its a tie!");
            }
            
            currentGame += 1;

        }
        input.close();
        System.out.println();
        System.out.println("Thanks for playing!");

    }

    public static void checkAce(int card, String suit) {
        if( card == 11 | card == 1) {
            System.out.printf("A %s%n", suit);
        }
        else {
            System.out.printf("%d %s%n", card, suit);
        }
    }

    public static void initGame(int current, int num) {
        System.out.println();
        System.out.println();
        for (int i = 0; i < 20; i++) {
            System.out.printf("*");
        }
        System.out.println();
        System.out.printf("Game %d of %d%n", current, num);
        for (int i = 0; i < 20; i++) {
            System.out.printf("*");
        }
        System.out.println();
    }

    public static List<Object> playerHand(String[] suit, Random rand) {
        
        // First Player cards and total
        int playerCard1 = rand.nextInt(11) + 1;
        int playerCard2 = rand.nextInt(11) + 1;
        // Player suits
        int pSuit1 = rand.nextInt(suit.length);
        int pSuit2 = rand.nextInt(suit.length);
        String playerSuit1 = suit[pSuit1];
        String playerSuit2 = suit[pSuit2];

        // Print Player cards ( if value is 11, print Ace )
        System.out.println("Your Hand:");
        checkAce(playerCard1, playerSuit1);
        checkAce(playerCard2, playerSuit2);

        // Player hand total
        int pTotal = playerCard1 + playerCard2;
        System.out.printf("Value: %d%n", pTotal);
        System.out.println();

        return Arrays.asList(playerCard1, playerSuit1, playerCard2, playerSuit2, pTotal);
    }

    public static List<Object> dealerHand(String[] suit, Random rand) {
        // First dealer cards and total
        int dealerCard1 = rand.nextInt(11) + 1;
        int dealerCard2 = rand.nextInt(11) + 1;
        // Dealer suits
        int dSuit1 = rand.nextInt(suit.length);
        int dSuit2 = rand.nextInt(suit.length);
        String dealerSuit1 = suit[dSuit1];
        String dealerSuit2 = suit[dSuit2];

        // Print dealer cards ( if value is 11, print Ace )
        System.out.println("Dealer Hand: ");
        checkAce(dealerCard1, dealerSuit1);
        System.out.println("Hidden");


        // Dealer hand total
        int dTotal = dealerCard1 + dealerCard2;
        System.out.println();

        return Arrays.asList(dealerCard1, dealerSuit1, dealerCard2, dealerSuit2, dTotal);
    }
}