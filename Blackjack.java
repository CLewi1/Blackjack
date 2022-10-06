import java.net.SocketPermission;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();


        // Array of suits
        String[] suits = {"of Hearts", "of Diamonds", "of Clubs", "of Spades"};


        int numGames = 1;
        int currentGame = 1;
        for (int i = 0; i < 20; i++) {
            System.out.printf("*");
        }
        System.out.println();
        System.out.printf("Game %d of %d%n", currentGame, numGames);
        for (int i = 0; i < 20; i++) {
            System.out.printf("*");
        }
        System.out.println();

        // First Player cards and total
        int playerCard1 = rand.nextInt(11) + 1;
        int playerCard2 = rand.nextInt(11) + 1;
        // Player suits
        int pSuit1 = rand.nextInt(suits.length);
        int pSuit2 = rand.nextInt(suits.length);
        String playerSuit1 = suits[pSuit1];
        String playerSuit2 = suits[pSuit2];

        // Print Player cards ( if value is 11, print Ace )
        System.out.println("Your Hand:");
        if( playerCard1 == 11 | playerCard1 == 1) {
            System.out.printf("A %s%n", playerSuit1);
        }
        else {
            System.out.printf("%d %s%n", playerCard1, playerSuit1);
        }
        if( playerCard2 == 11 | playerCard2 == 1) {
            System.out.printf("A %s%n", playerSuit2);
        }
        else {
            System.out.printf("%d %s%n", playerCard2, playerSuit2);
        }

        // Player hand total
        int pTotal = playerCard1 + playerCard2;
        System.out.printf("Value: %d%n", pTotal);
        System.out.println();





        // First dealer cards and total
        int dealerCard1 = rand.nextInt(11) + 1;
        int dealerCard2 = rand.nextInt(11) + 1;
        // Dealer suits
        int dSuit1 = rand.nextInt(suits.length);
        int dSuit2 = rand.nextInt(suits.length);
        String dealerSuit1 = suits[dSuit1];
        String dealerSuit2 = suits[dSuit2];

        // Print dealer cards ( if value is 11, print Ace )
        System.out.println("Dealer Hand: ");
        if( dealerCard1 == 11 | dealerCard1 == 1) {
            System.out.printf("A %s%n", dealerSuit1);
        }
        else {
            System.out.printf("%d %s%n", dealerCard1, dealerSuit1);
        }
        System.out.println("Hidden");


        // Dealer hand total
        int dTotal = dealerCard1 + dealerCard2;
        System.out.println();
        
        // User picks Hit or stand
        boolean repeat = true;
        while (repeat) {
            System.out.println("Please choose 'Hit' or 'Stand':");
            String playerOption = input.nextLine();
            System.out.println();
            
            // If player chooses hit, deal extra card
            if ( playerOption.toLowerCase().equals("h") || playerOption.toLowerCase().equals("hit")) {
                int playerCard3 = rand.nextInt(11) + 1;
                int pSuit3 = rand.nextInt(suits.length);
                String playerSuit3 = suits[pSuit3];
                System.out.printf("Dealt: %d %s%n", playerCard3, playerSuit3);
                pTotal += playerCard3;
                System.out.printf("Your hands value: %d%n", pTotal);
                System.out.println();
                if ( pTotal > 21 ) {
                    System.out.println("You busted! Dealer wins.");
                    input.close();
                    return;
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
            System.out.printf("%d %s%n", dealerCard1, dealerSuit1);
            System.out.printf("%d %s%n", dealerCard2, dealerSuit2);
            System.out.printf("%d %s%n", dealerCard3, dealerSuit3);
            System.out.printf("Value: %d%n", dTotal);
        }
        if ( dTotal > 21 ) {
            System.out.println("Dealer busted! You win!");
            input.close();
            return;
        }       
        System.out.println("Final Results:");
        System.out.printf("Your Hand: %d%n", pTotal);
        System.out.printf("Dealer Hand: %d%n", dTotal);
        
        if ( (dTotal > pTotal) && (dTotal <= 21) ) {
            System.out.println("Dealer Wins!");
        }
        if ( (dTotal < pTotal) && (dTotal <= 21) ) {
            System.out.println("You Win!");
        }
        if ( (dTotal == pTotal) && (dTotal <= 21) ) {
            System.out.println("Its a tie!");
        }
        input.close();



    }
}

// Example output

// How many games do you want to play? 1
// 
// ******************************
// Game 1 of 1
// ******************************
// Your hand:
// K of diamonds
// 9 of hearts
// Value: 19
// 
// Dealer's hand:
// hidden
// A of hearts
// 
// Please choose 'Hit' or 'Stand': h
// 
// Your hand:
// K of diamonds
// 9 of hearts
// J of spades
// Value: 29
// 
// You busted. Dealer wins! 😭
// 
// Thanks for playing!
