package com.jeremie.poker;

/**
 * 扑克类
 * Created by jeremie on 2016/3/15.
 */
public class Poker {

    /**
     * 扑克名
     */
    private String name;
    /**
     * 扑克花色
     */
    private Suit suit;
    /**
     * 扑克的价值
     */
    private double value;

    /**
     * 花色枚举类
     */
    public enum Suit{
        spade("黑桃"),heart("红桃"),diamond("方块"),club("梅花");
        public String cn;
        Suit(String cn){
            this.cn = cn;
        }
    }

    /**
     * 扑克构造方法
     * @param name
     * @param suit
     * @param value
     */
    public Poker(String name,Suit suit, double value) {
        this.name = name;
        this.suit = suit;
        this.value = value;
    }

    public String getName() {
        return name;
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

    public String getCardName(){
        return this.suit.cn + this.name;
    }
}
