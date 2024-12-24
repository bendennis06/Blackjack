import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.shuffle;

public class Deck {
    private List<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
        initializeDeck();
        shuffle();

    }

    void initializeDeck(){
        for(CardQualities.Suit suit: CardQualities.Suit.values()){
            for(CardQualities.Rank rank : CardQualities.Rank.values()){
                cards.add(new Card(suit, rank));
            }
        }
    }

    void shuffle(){
        Collections.shuffle(cards);
    }

    public Card drawCard(){
    if(cards.isEmpty()){
        throw new IllegalStateException("The deck is empty");
    }
        return cards.remove(cards.size() - 1);
    }

    public int size(){
        return cards.size();
    }


}
