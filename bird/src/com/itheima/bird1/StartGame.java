package com.itheima.bird1;
import javax.swing.*;


//游戏入口
public class StartGame {
    public static void main(String[] args) {
        //绘制游戏窗口 标题
        JFrame frame = new JFrame("飞扬的小鸟");
        //设置游戏窗口大小
        frame.setSize(400,670);
        //设置窗口所处位置 默认居中
        frame.setLocationRelativeTo(null);
        //设置窗口的关闭模式
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //绘制画板
        BirdGame Game = new BirdGame();
        frame.add(Game);
        //可视化窗口
        frame.setVisible(true);
        Game.action();
    }
}
