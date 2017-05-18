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
                    MainGame.state = MainGame.STATE.GAME;
                    System.out.println(MainGame.state);
                    MainGame.scorePlayer1 = 0;
                    MainGame.scorePlayer2  = 0;
                    MainGame.inGame = true;
                    System.out.println(MainGame.inGame);
                }
                if ((my >= HEIGHT_TABLE / 3 + 130) && (my <= HEIGHT_TABLE + 153)) {
                    System.exit(0);
                }
            }
        }

        if (MainGame.inGame == false) {
            if (mx >= 120 && mx <= 120 + 200) {
                if((my >= HEIGHT_TABLE/3 + 130) && (my <= HEIGHT_TABLE + 153)) {
                    MainGame.state = MainGame.STATE.MENU;
                    System.out.println(MainGame.state);
                    MainGame.bonuState = null;
                    MainGame.bonusAppear = null;
                    //MainGame.inGame = true;
                    System.out.println(MainGame.inGame);
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
