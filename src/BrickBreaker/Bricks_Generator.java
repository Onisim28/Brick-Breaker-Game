package BrickBreaker;

import java.awt.*;

public class Bricks_Generator {
    public int[][] bricks;

    public Bricks_Generator(int lines, int columns) {
        bricks = new int[lines][columns];
        for (int i = 0; i < bricks.length; i++)
            for (int j = 0; j < bricks[0].length; j++) {
                bricks[i][j] = 1;
            }
    }

    public void mapShow(Graphics graphics) {
        // int x=110,y=3;

        for (int i = 0, x = 110; i < bricks.length; i++, x += 82) {
            for (int j = 0, y = 3; j < bricks[0].length; j++, y += 42) {
                if (bricks[i][j] == 1) {
                    graphics.setColor(Color.CYAN);
                    graphics.fillRect(x, y, 80, 40);
                }
            }
        }
    }
}




