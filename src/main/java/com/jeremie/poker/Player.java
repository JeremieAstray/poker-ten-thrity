package com.jeremie.poker;

import java.util.ArrayList;
import java.util.List;

/**
 * 玩家类
 * Created by jeremie on 2016/3/15.
 */
public class Player {

    /**
     * 玩家名
     */
    private String name;
    /**
     * 手牌
     */
    private List<Poker> pokers = new ArrayList<Poker>();
    /**
     * 手牌分数
     */
    private double value = 0;
    /**
     * 庄家标示
     */
    private boolean banker = false;

    /**
     * 初始化玩家
     * @param name
     * @param poker 第一张抽到的扑克
     * @param isbanker 是否庄家
     */
    public Player(String name, Poker poker,boolean isbanker){
        this.name = name;
        pokers.add(poker);
        value += poker.getValue();
        banker = isbanker;
    }

    /**
     *
     * @param poker
     * @return 是否爆了
     */
    public boolean addPokers(Poker poker){
        pokers.add(poker);
        value += poker.getValue();
        return value > 10.5;
    }

    /**
     * 玩家名
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 获取手牌
     * @return
     */
    public List<Poker> getPokers(){
        return pokers;
    }

    /**
     * 获取总分
     * @return
     */
    public double getValue() {
        return value;
    }

    /**
     * 询问是否庄家
     * @return
     */
    public boolean isBanker() {
        return banker;
    }
}
