import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by PC on 06.05.2017.
 */
public class Menu extends JComponent implements GameConstants {
    private BufferedImage background;
    private BufferedImage buttonP;
    private BufferedImage buttonQ;
    private BufferedImage ping;
    public void render(Graphics2D g2) {
        try {
            background = ImageIO.read(new File("C:\\Users\\PC\\Projects\\Ping-pong\\Textures\\background.gif"));
        } catch (IOException e) {
        }
        g2.drawImage(background, 0, 0, null);
        try {
            buttonP = ImageIO.read(new File("C:\\Users\\PC\\Projects\\Ping-pong\\Textures\\Button07.png"));
        } catch (IOException e) {
        }
        g2.drawImage(buttonP, 120, HEIGHT_TABLE/3 , null);
        try {
            buttonQ = ImageIO.read(new File("C:\\Users\\PC\\Projects\\Ping-pong\\Textures\\Button08.png"));
        } catch (IOException e) {
        }
        g2.drawImage(buttonQ, 120, HEIGHT_TABLE/3 + 100 , null);
        try {
            ping = ImageIO.read(new File("C:\\Users\\PC\\Projects\\Ping-pong\\Textures\\ping.png"));
        } catch (IOException e) {
        }
        g2.drawImage(ping, 10, 100 , null);

    }
}
