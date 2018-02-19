/**
 * Created by PC on 19.02.2017.
 */
import javax.swing.*;
import javax.swing.JFrame.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;


public class Window extends JFrame implements GameConstants {
   Window() {
       setTitle("Ping-Pong");
       setSize(WIDTH_TABLE, HEIGHT_TABLE);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setResizable(false);
       setLocationRelativeTo(null);

   }
}



