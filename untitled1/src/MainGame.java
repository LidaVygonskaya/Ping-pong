/**
 * Created by PC on 20.02.2017.
 */

import sun.applet.Main;

import javax.swing.*;
import javax.swing.JFrame.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


public class MainGame extends JComponent implements ActionListener, GameConstants, MouseMotionListener {
    private int ball_x = BALL_START_X;
    private int ball_y = BALL_START_Y;
    private int ball_speed_x = 5;
    private int ball_speed_y = 7;
    private int rocket1_x = 0;
    private int rocket2_x = 0;
    public boolean inGame = true;
    //Graphics g2;

    public static void main(String[] args) {

        Window table = new Window();
        MainGame game = new MainGame();
        table.add(game);
        table.setVisible(true);
        table.pack();
        Timer t = new Timer(50, game);
        t.start();
        table.addMouseMotionListener(game);

    }


    public Dimension getPreferredSize() {
        return new Dimension(WIDTH_TABLE, HEIGHT_TABLE);
    }

    private void gameOver(){
        Graphics g2 = getGraphics();
        Font BigFontTR = new Font("TimesRoman", Font.BOLD, 30);
        g2.setFont(BigFontTR);
        g2.drawString("GAME OVER", BALL_START_X - 100, BALL_START_Y);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(64, 239, 247));
        g2.fillRect(0, 0, WIDTH_TABLE, HEIGHT_TABLE);
        //draw the rectangle
        g2.setColor(new Color(110, 65, 13));
        g2.fillRect(rocket1_x, ROCKET_Y, ROCKET_WIDTH, ROCKET_HEIGHT);
        g2.fillRect(rocket2_x, ROCKET2_Y, ROCKET_WIDTH, ROCKET_HEIGHT);
        //draw ball
        g2.setColor(new Color(221, 14, 172));
        g2.fillOval(ball_x, ball_y, BALL_SIZE, BALL_SIZE);
    }


    @Override
    public void actionPerformed(ActionEvent e){
        ball_x = ball_x + ball_speed_x;
        ball_y = ball_y + ball_speed_y;

        if ((ball_y >= HEIGHT_TABLE - BALL_SIZE) || (ball_y <= 0)) {
            inGame = false;
            gameOver();
        }
        if (ball_x >= (WIDTH_TABLE - BALL_SIZE)) {
            ball_speed_x = -5;
        }
        if (ball_x <= 0) {
            ball_speed_x = 5;
        }
        //if (ball_y <= 0) {
        //  ball_speed_y = 7;
        //}
        if (ball_y + 30 >= ROCKET_Y) {
            if ((ball_x + BALL_SIZE >= rocket1_x) && (ball_x <= rocket1_x + ROCKET_WIDTH) && (ball_y + BALL_SIZE >= ROCKET_Y) &&
                    (ball_y <= ROCKET_Y + ROCKET_HEIGHT)) {
                ball_speed_y = -7;
            }
        }
        if (inGame) {
            repaint();
        }
    }




    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        rocket1_x = e.getX();
        if (inGame) {
            repaint();
        }

    }

}
