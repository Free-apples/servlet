/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.massey.cs.webtech.s19041253;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author meganfreedman
 */
public class DeckOfCards {
    public static final int CARDAMOUNT = 52;
    private Card[] deckOfCards;         // Contains all 52 cards
    private int currentCardIndex = 0;

    //todo test can get cards
    public DeckOfCards(){
        deckOfCards = new Card[ CARDAMOUNT ];

        int i = 0;
        String[] suits = {"♣", "♦", "♥", "♠"};
        for ( String suit: suits)
            for ( int rank = 1; rank <= 13; rank++ )
                deckOfCards[i++] = new Card(rank, suit);
        currentCardIndex = 0;
    }
    //test shuffle
    public void Shuffle()
    {
        Random rand = new Random();

        for (int i = 0; i< CARDAMOUNT; i ++) {
            int r = i + rand.nextInt(52 - i);
            Card temp = deckOfCards[r];
            deckOfCards[r] = deckOfCards[i];
            deckOfCards[i] = temp;

        }
        currentCardIndex = 0;

    }

    public int getIndex(){
        return currentCardIndex;
    }

    public Card getCard(){
        if (currentCardIndex < CARDAMOUNT){
            currentCardIndex ++;
            return deckOfCards[currentCardIndex];
        }
        else{
            return (null);
        }


    }

    public Card returnCard(int index){
        return deckOfCards[index + 1];
    }

}




