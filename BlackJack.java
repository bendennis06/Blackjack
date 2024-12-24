
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJack {
    private Deck deck;
    private List<Card> playerHand;
    private List<Card> dealerHand;
    private int playerTotal;
    private int dealerTotal;


    Scanner scnr = new Scanner(System.in);
    //game rules:
    /*
    dealer and player start with two cards, both player cards face up, one dealer card face up.
    player can hit or stay
    get as close to 21 then stay

    edge case: an ace can count as 11 or 1.
       Also, if both player and dealer have 21, its a tie
     */


    public BlackJack() {
        deck = new Deck();
        deck.shuffle();

        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();

        playGame();

    }

    public void playGame(){

        instantiateGame();
        displayHands(true);
        calculateTotals(true);
        inputCheck();

        if(playerTotal <= 21){
            dealerPlay();
            checkWin();
        }

    restartGame();
    }

    public void dealerHit() {
        dealerHand.add(deck.drawCard());
    }

    public void playerHit() {
        playerHand.add(deck.drawCard());
    }

    public void instantiateGame() {
        dealerHit();
        playerHit();
        dealerHit();
        playerHit();
    }


    public void displayHands(boolean hideDealerCard) {
       if(hideDealerCard){
           System.out.println("Dealer's Hand: [Hidden, " + dealerHand.get(1) + "]");
       } else {
           System.out.println("Dealers Hand: " + dealerHand);
       }
        System.out.println("Players Hand: " + playerHand);

        calculateTotals(hideDealerCard);

        System.out.println("Player Total: " + playerTotal);

        if(!hideDealerCard){
            System.out.println("Dealer Total: " + dealerTotal);
        }

    }


    public void calculateTotals(boolean hideDealerCard){
        playerTotal = 0;
        dealerTotal = 0;

        for(Card card: playerHand){
            playerTotal += card.getValue();
        }
        playerTotal = adjustForAces(playerHand, playerTotal);

        if(!hideDealerCard) {
            for (Card card : dealerHand) {
                dealerTotal += card.getValue();
            }
            dealerTotal = adjustForAces(dealerHand, dealerTotal);
        }
    }

    public void inputCheck() {
        System.out.println("hit or stay?");
        while (true) {
            String input = scnr.nextLine().toLowerCase();

            if (input.equals("hit")) {

                playerHit();
                calculateTotals(true);
                displayHands(true);

                if (playerTotal > 21) {
                    System.out.println("You Bust!");
                    return;
                }

            } else if (input.equals("stay")) {
                break;
            } else {
                System.out.println("invalid input");
            }
        }
    }

    public void dealerPlay(){
        System.out.println("Dealers Turn");
        calculateTotals(false);
        displayHands(false);

        while(dealerTotal < 17){
            dealerHit();
            calculateTotals(false);
            displayHands(false);
        }



            if(dealerTotal > 21) {
                System.out.println("Dealer Busts, you win!");
                return;

        }
    }

    public int adjustForAces(List<Card> hand, int total){
            for(Card card: hand){
                if(card.getRank() == CardQualities.Rank.ACE && total > 21){
                    total -= 10;
                }
            }
        return total;
    }

    public void checkWin() {
        if(playerTotal > 21){
            System.out.println("You Bust, you lose!");
        } else if(dealerTotal > 21){
            System.out.println("Dealer Busts, you win!");
        } else if(playerTotal > dealerTotal){
            System.out.println("you win!");
        } else if(playerTotal < dealerTotal){
            System.out.println("you lose!");
        }else {
            System.out.println("Its a tie!");
        }

    }

    public void restartGame(){
        System.out.println("Do you want to play again? y/n");
        String input = scnr.nextLine().toLowerCase();

        if(input.equals("y")){
            playerHand.clear();
            dealerHand.clear();
            playerTotal = 0;
            dealerTotal = 0;
                playGame();
        } else{
            System.out.println("thanks for playing");
            scnr.close();
        }
    }

}
