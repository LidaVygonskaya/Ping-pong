import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by PC on 11.05.2017.
 */
public class Bonus extends JComponent implements GameConstants {
    private BufferedImage bonus;
    public void renderSpeed(Graphics2D g2 ,int x, int y) {
       // Graphics2D g2 = (Graphics2D) getGraphics();
        try {
            bonus = ImageIO.read(new File("C:\\Users\\PC\\Projects\\Ping-pong\\Textures\\speed1.png"));
        } catch (IOException e) {
        }
        g2.drawImage(bonus, x, y, null);
    }
    public void renderBigRocket(Graphics2D g2, int x, int y) {
        try {
            bonus = ImageIO.read(new File("C:\\Users\\PC\\Projects\\Ping-pong\\Textures\\bonusRocket.png"));
        } catch (IOException e) {
        }
        g2.drawImage(bonus, x, y, null);
    }

    public void renderSmallRocket(Graphics2D g2, int x, int y) {
        try {
            bonus = ImageIO.read(new File("C:\\Users\\PC\\Projects\\Ping-pong\\Textures\\BonusSmall.png"));
        } catch (IOException e) {
        }
        g2.drawImage(bonus, x, y, null);
    }


}
