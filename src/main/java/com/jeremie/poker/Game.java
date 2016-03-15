package com.jeremie.poker;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 游戏类
 * Created by jeremie on 2016/3/15.
 */
public class Game {
    /**
     * 扑克
     */
    public Queue<Poker> pokersQueue;
    /**
     * 玩家
     */
    public List<Player> players;

    public void init() {
        List<Poker> pokers = Arrays.asList(new Poker("1", Poker.Suit.club, 1),
                new Poker("2", Poker.Suit.club, 2),
                new Poker("3", Poker.Suit.club, 3),
                new Poker("4", Poker.Suit.club, 4),
                new Poker("5", Poker.Suit.club, 5),
                new Poker("6", Poker.Suit.club, 6),
                new Poker("7", Poker.Suit.club, 7),
                new Poker("8", Poker.Suit.club, 8),
                new Poker("9", Poker.Suit.club, 9),
                new Poker("10", Poker.Suit.club, 10),
                new Poker("J", Poker.Suit.club, 0.5),
                new Poker("Q", Poker.Suit.club, 0.5),
                new Poker("K", Poker.Suit.club, 0.5),
                new Poker("1", Poker.Suit.diamond, 1),
                new Poker("2", Poker.Suit.diamond, 2),
                new Poker("3", Poker.Suit.diamond, 3),
                new Poker("4", Poker.Suit.diamond, 4),
                new Poker("5", Poker.Suit.diamond, 5),
                new Poker("6", Poker.Suit.diamond, 6),
                new Poker("7", Poker.Suit.diamond, 7),
                new Poker("8", Poker.Suit.diamond, 8),
                new Poker("9", Poker.Suit.diamond, 9),
                new Poker("10", Poker.Suit.diamond, 10),
                new Poker("J", Poker.Suit.diamond, 0.5),
                new Poker("Q", Poker.Suit.diamond, 0.5),
                new Poker("K", Poker.Suit.diamond, 0.5),
                new Poker("1", Poker.Suit.heart, 1),
                new Poker("2", Poker.Suit.heart, 2),
                new Poker("3", Poker.Suit.heart, 3),
                new Poker("4", Poker.Suit.heart, 4),
                new Poker("5", Poker.Suit.heart, 5),
                new Poker("6", Poker.Suit.heart, 6),
                new Poker("7", Poker.Suit.heart, 7),
                new Poker("8", Poker.Suit.heart, 8),
                new Poker("9", Poker.Suit.heart, 9),
                new Poker("10", Poker.Suit.heart, 10),
                new Poker("J", Poker.Suit.heart, 0.5),
                new Poker("Q", Poker.Suit.heart, 0.5),
                new Poker("K", Poker.Suit.heart, 0.5),
                new Poker("1", Poker.Suit.spade, 1),
                new Poker("2", Poker.Suit.spade, 2),
                new Poker("3", Poker.Suit.spade, 3),
                new Poker("4", Poker.Suit.spade, 4),
                new Poker("5", Poker.Suit.spade, 5),
                new Poker("6", Poker.Suit.spade, 6),
                new Poker("7", Poker.Suit.spade, 7),
                new Poker("8", Poker.Suit.spade, 8),
                new Poker("9", Poker.Suit.spade, 9),
                new Poker("10", Poker.Suit.spade, 10),
                new Poker("J", Poker.Suit.spade, 0.5),
                new Poker("Q", Poker.Suit.spade, 0.5),
                new Poker("K", Poker.Suit.spade, 0.5));
        Collections.shuffle(pokers);
        pokersQueue = new LinkedBlockingQueue<>(pokers);
        int playerNumber = getPlayerNumberByPane();
        players = new ArrayList<>();
        int banker = RandomUtils.nextInt(playerNumber) + 1;
        for (int i = 0; i < playerNumber; i++) {
            Poker poker = pokersQueue.poll();
            Player player = new Player("player" + (i + 1), poker, banker - 1 == i);
            players.add(player);
        }
        for (Player player : players) {
            Poker poker = player.getPokers().get(0);
            System.out.println(player.getName() + " " + poker.getSuit().cn + " " + poker.getName() + " 分值：" + player.getValue() + " 是否庄-->" + player.isBanker());
        }
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.init();
    }

    public int getPlayerNumberByPane() {
        String number = JOptionPane.showInputDialog("请输入要玩的人数(大于等2人，少于等于4人)");
        while (number == null || !StringUtils.isNumeric(number) || StringUtils.isBlank(number)) {
            JOptionPane.showMessageDialog(null, "请输入数字!");
            number = JOptionPane.showInputDialog("请输入要玩的人数(大于2人，少于5人)");
        }
        int platerNumber = Integer.parseInt(number);
        while (platerNumber < 2 || platerNumber > 4) {
            JOptionPane.showMessageDialog(null, "请输入大于2人，少于5人的数字!");
            number = JOptionPane.showInputDialog("请输入要玩的人数(大于2人，少于5人)");
            while (number == null || !StringUtils.isNumeric(number) || StringUtils.isBlank(number)) {
                JOptionPane.showMessageDialog(null, "请输入数字!");
                number = JOptionPane.showInputDialog("请输入要玩的人数(大于2人，少于5人)");
            }
            platerNumber = Integer.parseInt(number);
        }
        return platerNumber;
    }

}
