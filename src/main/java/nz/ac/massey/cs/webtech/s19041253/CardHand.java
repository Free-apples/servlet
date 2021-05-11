package nz.ac.massey.cs.webtech.s19041253;

import java.util.ArrayList;

public class CardHand {
    private int value;
    private Card firstCard = null;
    private ArrayList<Card> cardHand = new ArrayList<Card>();
    private int size;

    //Todo test
    public void createHand(DeckOfCards deck){
        Card card1 = deck.getCard();
        Card card2 = deck.getCard();
        if (firstCard == null) {
            firstCard = card1;
        }
        cardHand.add(card1);
        cardHand.add(card2);
        value = card1.getCardValue() + card2.getCardValue();
        size += 2;
    }

    //todo test
    public void addToHand(DeckOfCards deck) {
        Card card = deck.getCard();
        cardHand.add(card);
        value = value + card.getCardValue();
        size++;
    }


    public int getValue() {
        return value;
    }


    public ArrayList<Card> getCardHand() {
        return this.cardHand;
    }

    public Card getFirstCard() {
        return firstCard;
    }

    public int getSize(){
        return size;
    }

    public Card getCard(int index){
        return cardHand.get(index);

    }

}
