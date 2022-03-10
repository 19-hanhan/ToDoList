package test;

import TDLFrame.MainFrame;

import javax.swing.*;

public class Main {

    private static int frameWidth = 400;
    private static int frameHeight = 627;
    private static int xLocation = 200;
    private static int yLocation = 100;

    public static void main(String[] args) {

        // 设定主窗口
        MainFrame run = new MainFrame();
        run.setTitle("Main");
        run.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        run.setLocation(xLocation, yLocation);
        run.setSize(frameWidth, frameHeight);
        run.setVisible(true);


    }

}
