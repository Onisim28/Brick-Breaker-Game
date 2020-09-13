package BrickBreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int numb_ofBricks = 25;

    Timer time;

    private int speed_ofBall = 7;
    private int sliderX = 310;
    private int ballX = 150;
    private int ballY = 350;
    private int ballXdir = 1;
    private int ballYdir = -2;
    private Bricks_Generator bricks_generator;


    public GamePlay() {
        bricks_generator = new Bricks_Generator(5, 5);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time = new Timer(speed_ofBall, this);
        time.start();
    }

    public void paint(Graphics graphics)//Graphics - used for creating the slider,and diffrenet shapes
    {
        graphics.setColor(Color.BLACK);//Background
        graphics.fillRect(1, 1, 692, 592);//creates a rectangle for the background


        bricks_generator.mapShow(graphics);//drawing bricks


        graphics.setColor(Color.YELLOW);//Borders
        graphics.fillRect(0, 0, 3, 592);
        graphics.fillRect(0, 0, 692, 3);
        graphics.fillRect(684, 0, 3, 592);

        graphics.setColor(Color.GREEN);//Slider
        graphics.fillRect(sliderX, 550, 100, 8);

        graphics.setColor(Color.YELLOW);//Ball
        graphics.fillOval(ballX, ballY, 20, 20);

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("serif", Font.BOLD, 25));
        graphics.drawString("SCORE: " + score, 540, 30);
        //Draw score

        if ((ballY > 550) && (numb_ofBricks > 0)) {
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("serif", Font.BOLD, 25));
            graphics.drawString("GAME OVER", 230, 300);
        }
        //GAME OVER Appearance

        if (numb_ofBricks == 0) {
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("serif", Font.BOLD, 25));
            graphics.drawString("YOU WON", 230, 300);
        }

        graphics.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time.start();

        if (play) {
            ballX += ballXdir;
            ballY += ballYdir;
            if (ballX == 0)
                ballXdir = -ballXdir;
            if (ballY == 0)
                ballYdir = -ballYdir;
            if (ballX == 670)
                ballXdir = -ballXdir;
        }
        if (play)
            if (new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(sliderX, 550, 100, 8)))
                ballYdir = -ballYdir;

        if (play)
            first_for:for (int i = 0, x = 110; i < bricks_generator.bricks.length; i++, x += 82) {
                for (int j = 0, y = 3; j < bricks_generator.bricks[0].length; j++, y += 42) {
                    if (bricks_generator.bricks[i][j] == 1)
                        if (new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(x, y, 82,
                                42))) {
                            score += 10;
                            numb_ofBricks--;
                            bricks_generator.bricks[i][j] = 0;
                            ballYdir = -ballYdir;
                            break first_for;
                        }
                }
            }


        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (sliderX > 580)
                sliderX = 580;
            else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (sliderX < 10)
                sliderX = 10;
            else {
                moveLeft();
            }
        }
    }

    public void moveRight() {
        play = true;
        sliderX += 25;
    }

    public void moveLeft() {
        play = true;
        sliderX -= 25;
    }
}
