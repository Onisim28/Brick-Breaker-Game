package BrickBreaker;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // int[][] Bricks=new int[5][5];
        //Bricks_Generator bricks_generator=new Bricks_Generator();

        GamePlay gameplay = new GamePlay();

        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 700, 600);
        frame.setTitle("Brick Breaker");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.add(gameplay);


    }
}
