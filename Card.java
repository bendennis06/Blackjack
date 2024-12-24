public class Card {

    private CardQualities.Suit suit;
    private CardQualities.Rank rank;
    private String cardName;
    public  int value;
    private boolean isAce;

    public Card(CardQualities.Suit suit, CardQualities.Rank rank){
        this.suit = suit;
        this.rank = rank;


        switch(rank) {
            case ACE:
                isAce = true; // conditional logic req'd since Ace can be 1 or 11.
               this.value = 11;
                break;
            case JACK:
            case QUEEN:
            case KING:
                this.value = 10;
                break;
            default:
                value = rank.ordinal() + 1;
                break;
        }

    }

    public CardQualities.Rank getRank() {
        return rank;
    }

    public CardQualities.Suit getSuit() {
        return suit;
    }

    @Override
    public String toString(){
        return rank + " of " + suit;
    }

    public  int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
