package com.jeremie.poker;

/**
 * Created by jeremie on 2016/3/15.
 */
public class Poker {

    private String name;
    private Suit suit;
    private double value;

    public enum Suit{
        spade("黑桃"),heart("红桃"),diamond("方块"),club("梅花");
        public String cn;
        Suit(String cn){
            this.cn = cn;
        }
    }

    public Poker(String name,Suit suit, double value) {
        this.name = name;
        this.suit = suit;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
