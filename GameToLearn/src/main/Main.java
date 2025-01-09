package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        GamePanel gamePanel = initialize();
        gamePanel.startGameThread();
    }

    private static GamePanel initialize() {
        System.out.println("test");

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); // so you can't change the size of the screen
        window.setTitle("Best Game Ever");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack(); // puts the window in the size I chose

        window.setLocationRelativeTo(null); // shown at the center of screen
        window.setVisible(true);
        return gamePanel;
    }
}