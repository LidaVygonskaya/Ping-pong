import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by PC on 07.05.2017.
 */
public class MenuMouseInput extends JComponent implements MouseListener, GameConstants {
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //public Rectangle playButton = new Rectangle(WIDTH_TABLE/2,150, 100, 50);
        int mx = e.getX();
        int my = e.getY();
        //PLAY BUTTON
        //ширина кнопки 200
        //высота кнопки 53
        if (MainGame.state == MainGame.STATE.MENU) {
            if (mx >= 120 && mx <= 120 + 200) {
                if (my >= HEIGHT_TABLE / 3 + 30 && my <= HEIGHT_TABLE / 3 + 80) {
                    Sound.playSound("C:\\Users\\PC\\Projects\\Ping-pong\\Sound\\MENU_A_Select.wav");
                    MainGame.state = MainGame.STATE.GAME;
                    MainGame.scorePlayer1 = 0;
                    MainGame.scorePlayer2  = 0;
                    MainGame.inGame = true;
                }
                if ((my >= HEIGHT_TABLE / 3 + 130) && (my <= HEIGHT_TABLE + 153)) {
                    Sound.playSound("C:\\Users\\PC\\Projects\\Ping-pong\\Sound\\MENU_A_Select.wav");
                    System.exit(0);
                }
            }
        }

        if (MainGame.inGame == false) {
            if (mx >= 120 && mx <= 120 + 200) {
                if((my >= HEIGHT_TABLE/3 + 130) && (my <= HEIGHT_TABLE + 153)) {
                    Sound.playSound("C:\\Users\\PC\\Projects\\Ping-pong\\Sound\\MENU_A_Select.wav");
                    MainGame.state = MainGame.STATE.MENU;
                    MainGame.bonuState = null;
                    MainGame.bonusAppear = null;
                }

            }
        }
    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {


    }
}
