/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.massey.cs.webtech.s19041253;

/**
 *
 * @author meganfreedman
 */
public class Card {
    private int rank;
    private String suit;
    private int value;

    public Card(int rank, String suit){
        this.rank = rank;
        this.suit = suit;
        if (this.rank < 11 && this.rank > 1){
            this.value = this.rank;
        }
        else if(this.rank == 1){
            this.value = 11;
        }
        else{
            this.value = 10;
        }

    }

    public int getCardValue(){
        return value;
    }

    public int getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

}
