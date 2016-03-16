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

    /**
     * 庄家号码
     */
    public int banker;

    /**
     * 初始化盘面
     */
    public void init() {
        List<Poker> pokers = Arrays.asList(
                new Poker("A", Poker.Suit.club, 1),
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
                new Poker("A", Poker.Suit.diamond, 1),
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
                new Poker("A", Poker.Suit.heart, 1),
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
                new Poker("A", Poker.Suit.spade, 1),
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
        banker = RandomUtils.nextInt(playerNumber) + 1;
        for (int i = 0; i < playerNumber; i++) {
            Poker poker = pokersQueue.poll();
            Player player = new Player("player" + (i + 1), poker, banker - 1 == i);
            players.add(player);
        }
    }

    /**
     * 玩游戏
     */
    public void play() {
        for (Player player : players) {
            Poker poker = player.getPokers().get(0);
            System.out.println(player.getName() + " " + poker.getCardName() + "，" +
                    poker.getCardName() + " 分值：" +
                    player.getValue() + " 是否庄-->" + player.isBanker());
        }
        for (int i = 0; i < players.size(); i++)
            if (i != banker - 1)
                askForCard(players.get(i));
        askForCard(players.get(banker - 1));

        System.out.println("--------结果---------");
        printResult(banker, players);
    }

    /**
     * 询问是否要卡
     *
     * @param player
     */
    public void askForCard(Player player) {
        System.out.println("--------" + player.getName() + "加牌环节---------");
        boolean need;
        do {
            if (player.getPokers().size() == 5) {
                System.out.println(player.getName() + "已经满5张牌，停止加牌！");
                break;
            } else if (player.getValue() - 10.5 > 0.001) {
                System.out.println(player.getName() + "分值已经过10.5，停止加牌！");
                break;
            } else if (player.getPokers().size() == 2 && Math.abs(player.getValue() - 10.5) < 0.001) {
                System.out.println(player.getName() + "玩家已赢，停止加牌！");
                break;
            }
            int answer = JOptionPane.showConfirmDialog(null, player.getName() + "，请问你要加牌么？");
            need = JOptionPane.YES_OPTION == answer;
            if (need) {
                getCard(player);
                printPlayerCard(player);
            } else {
                System.out.println(player.getName() + "停止加牌！");
            }
        } while (need);
    }

    /**
     * 打印玩家手上的牌
     *
     * @param player
     */
    public void printPlayerCard(Player player) {
        System.out.print(player.getName() + "手上的卡：");
        List<Poker> pokers = player.getPokers();
        for (int i = 0; i < pokers.size(); i++) {
            if (i == pokers.size())
                System.out.print(pokers.get(i).getCardName());
            else
                System.out.print(pokers.get(i).getCardName() + "，");
        }
        System.out.println(" 总分 " + player.getValue());
    }

    /**
     * 返回是否爆煲
     *
     * @param player
     * @return
     */
    public boolean getCard(Player player) {
        Poker poker = pokersQueue.poll();
        System.out.println(player.getName() + "拿到" + poker.getCardName());
        return player.addPokers(poker);
    }

    /**
     * 根据现有状况打印结果
     *
     * @param bankerNum 庄家的号码
     * @param players   玩家
     */
    public void printResult(int bankerNum, List<Player> players) {
        Player banker = players.get(bankerNum - 1);
        for (int i = 0; i < players.size(); i++) {
            if (bankerNum != i + 1) {
                Player player = players.get(i);
                if (player.getValue() - 10.5 > 0.001) {
                    //闲家大于10.5
                    System.out.print("爆煲：");
                    printWinner(banker, player);
                } else if (player.getPokers().size() >= 5) {
                    //闲家五龙
                    System.out.print("五龙：");
                    printWinner(player, banker);
                } else if (Math.abs(player.getValue() - 10.5) < 0.001 && Math.abs(banker.getValue()) - 10.5 < 0.001)
                    //庄家和闲家都10点半
                    printWinner(banker, player);
                else if (player.getPokers().size() == 2 && player.getValue() - 10.5 < 0.001)
                    //闲家倘若为一只10加一只 J,Q或K 成十点半
                    printWinner(player, banker);
                else if (player.getValue() - 10.5 < 0.001) {
                    if (banker.getValue() - 10.5 > 0.001)
                        //闲家未爆，庄家爆
                        printWinner(player, banker);
                    else if (Math.abs(player.getValue() - banker.getValue()) < 0.001) {
                        //点数相同，庄家胜，称食夹棍
                        System.out.print("食夹棍：");
                        printWinner(banker, player);
                    } else if (player.getValue() - banker.getValue() > 0.001)
                        //闲家大于庄家
                        printWinner(player, banker);
                    else
                        //其他
                        printWinner(banker, player);
                }
            }
        }
    }

    /**
     * 打印赢家
     *
     * @param winner
     * @param loser
     */
    public void printWinner(Player winner, Player loser) {
        System.out.println(winner.getName() + "对" + loser.getName() + "：" + winner.getName() + "赢");

    }

    /**
     * 获取玩家人数
     *
     * @return
     */
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

    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        game.play();
    }

}
