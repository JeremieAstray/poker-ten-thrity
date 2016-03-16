package com.jeremie.poker;

import org.apache.commons.lang.StringUtils;

import javax.swing.*;

/**
 * 抽离的视图层
 * Created by jeremie on 2016/3/16.
 */
public class View {
    /**
     * 获取玩家人数
     *
     * @return
     */
    public static int getPlayerNumberByPane() {
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

    /**
     * 询问是否要卡的界面
     *
     * @param name
     * @return
     */
    public static boolean askForCard(String name) {
        int answer = JOptionPane.showConfirmDialog(null, name + "，请问你要加牌么？");
        return JOptionPane.YES_OPTION == answer;
    }

    /**
     * 输出文本
     */
    public static void printlnText(String text) {
        System.out.println(text);
    }

    /**
     * 输出文本
     */
    public static void printText(String text) {
        System.out.print(text);
    }
}
