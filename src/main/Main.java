package main;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Bomberman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel g = new GamePanel();
        frame.add(g);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        g.startGameThread();
    }
}