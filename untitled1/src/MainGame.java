/**
 * Created by PC on 20.02.2017.
 */

import org.lwjgl.glfw.GLFWGammaRamp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;


public class MainGame extends JComponent implements ActionListener, GameConstants, MouseMotionListener {
    private static Menu menu1;
    private static Bonus bonus;
    //private static Graphics2D g2;
    private int ball_x = BALL_START_X;
    private int ball_y = BALL_START_Y;
    public int ball_speed_x = 8;
    public int ball_speed_y = 10;
    public int rocket_speed = 8;
    public int bonus_speed = 9;
    public int bonusSpeedY = HEIGHT_TABLE / 2;
    private int rocket1_x = 0;
    private int rocket2_x = 0;
    public static int scorePlayer1 = 0; //нижняя ракетка
    public static int scorePlayer2 = 0; // верхняя ракетка
    public static boolean inGame = false;
    private BufferedImage ballImage;
    private BufferedImage rocketImage;
    private BufferedImage tableImage = ImageIO.read(new File("C:\\Users\\PC\\Projects\\Ping-pong\\Textures\\tale1.gif"));
    private BufferedImage win;
    private BufferedImage lose;
    private float x1;
    private float y1;
    private float a;
    private float b;
    private static int weight = 0;
    private static int deviation = 0;
    Random rand1 = new Random();
    Random rand2 = new Random();
    int r = 200;
    int m;
    public static File file = new File("C:\\Users\\PC\\Projects\\Ping-pong\\Sound\\Menu.wav");
    public static Sound sound =  new Sound(file);
    private BufferedImage rocketImage2;

    public MainGame() throws AWTException, IOException {
    }

    public static enum STATE {
        MENU,
        GAME
    };
    public static enum BONUS {
        SPEED_BONUS,
        SPEED_BONUS_APPEAR,
        LENGTH_BONUS,
        LENGTH_BONUS_APPEAR,
        SMALL_BONUS,
        SMALL_BONUS_APPEAR,
        SMALL_ENEMY,
        SMALL_ENEMY_APPEAR,
    };

    public static STATE state = STATE.MENU;
    public static BONUS bonuState = null;
    public static BONUS bonusAppear = null;

    public static void main(String[] args) throws AWTException, IOException {
        Window table = new Window();
        if (!sound.isPlaying()) {
            sound.play();
        }
        MainGame game = new MainGame();
        table.addMouseListener(new MenuMouseInput());
        bonus = new Bonus();
        menu1 = new Menu();
        table.add(game);
        table.pack();
        table.setVisible(true);

        if (!sound.isPlaying()) {
            sound.play();
        }

        while (state == STATE.MENU) {
           table.add(game);
        }

        if ((state == STATE.GAME)) {
            table.repaint();
            table.pack();
            javax.swing.Timer t = new javax.swing.Timer(45, game);
            t.start();
            table.addMouseMotionListener(game);


            TimerTask rand = new TimerTask() {
                @Override
                public void run() {
                    Random random = new Random();
                    deviation = random.nextInt(100);
                    weight = random.nextInt(3);
                }
            };
            Timer randTimer = new Timer ();
            randTimer.schedule(rand, 0, 2000);


            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    Random random = new Random();
                    int rand = random.nextInt(4);
                    //states = 0,1,2,3,4;
                    switch(rand) {
                        case 0: bonusAppear = BONUS.SPEED_BONUS_APPEAR;
                            break;
                        case 1: bonusAppear = BONUS.LENGTH_BONUS_APPEAR;
                            break;
                        case 2: bonusAppear = BONUS.SMALL_BONUS_APPEAR;
                            break;
                        case 3: bonusAppear = BONUS.SMALL_ENEMY_APPEAR;
                            break;
                    }
                    TimerTask task = new TimerTask() {
                    @Override
                        public void run() {
                            bonuState = null;
                        }
                    };
                    Timer time = new Timer();
                    time.schedule(task, 20000);

                }
            };
            Timer timer = new Timer();
            timer.schedule(timerTask, 5000, 30000);
        }
    }

    public int random() {
        Random random = new Random();
        if ((weight == 0)) {

            return -deviation + (int) traektory() - rocketImage.getWidth() / 2;
        }
        if ((weight == 1) || (weight == 2)) {

            return deviation + (int) traektory() - rocketImage.getWidth() / 2;
        }

        return 0;
    }

    public int traektory() {
        x1 = (float) ball_x;
        y1 = (float) ball_y;
        a = (float) ball_speed_y / (float) ball_speed_x;
        b = y1 - a * x1;
        while (!lastLine(a, b)) {
            if (a > 0) {
                a = -a;
            } else {
                a = -a;
                b = b - 2 * WIDTH_TABLE;
            }

        }
        return (int) Math.abs(((ROCKET2_Y - b) / a));
    }

    public boolean lastLine(float a, float b) {
        float result = Math.abs((ROCKET2_Y - b) / a);
        return (result >= 0) && (result <= WIDTH_TABLE);
    }

    public Dimension getPreferredSize() {
        return new Dimension(WIDTH_TABLE, HEIGHT_TABLE);
    }

    private void gameOver() {
        Graphics g2 = getGraphics();
        try {
            lose = ImageIO.read(new File("C:\\Users\\PC\\Projects\\Ping-pong\\Textures\\gameOver.png"));
        } catch (IOException e) {
            //nothing to do
        }
        g2.drawImage(lose, 60, BALL_START_Y - 120, null);

        try {
            lose = ImageIO.read(new File("C:\\Users\\PC\\Projects\\Ping-pong\\Textures\\Button09.png"));
        } catch (IOException e) {
            //nothing to do
        }
        g2.drawImage(lose, 120, HEIGHT_TABLE/3 + 100, null);
    }

    private void youWin() {
        Graphics g2 = getGraphics();
        try {
            win = ImageIO.read(new File("C:\\Users\\PC\\Projects\\Ping-pong\\Textures\\youWin.png"));
        } catch (IOException e) {
            //nothing to do
        }
        g2.drawImage(win, 105, BALL_START_Y - 120, null);

        try {
            win = ImageIO.read(new File("C:\\Users\\PC\\Projects\\Ping-pong\\Textures\\Button09.png"));
        } catch (IOException e) {
            //nothing to do
        }
        g2.drawImage(win, 120, HEIGHT_TABLE/3 + 100, null);



    }

    public void paintComponent(Graphics g) {
        if (!sound.isPlaying()) {
            sound.play();
        }
        Graphics2D g2 = (Graphics2D) g;
        if (state == STATE.GAME) {

            try {
                tableImage = ImageIO.read(new File("C:\\Users\\PC\\Projects\\Ping-pong\\Textures\\tale1.gif"));
            } catch (IOException e) {
            }
            g2.drawImage(tableImage, 0, 0, null);
            try {
                rocketImage = ImageIO.read(new File("C:\\Users\\PC\\Projects\\Ping-pong\\Textures\\StandartRocket.png"));
            } catch (IOException e) {
                //nothing to do
            }
            //g2.drawImage(rocketImage, rocket2_x, ROCKET2_Y, null);
            g2.setColor(new Color(0, 0, 0));
            g2.setFont(new Font("Times Italic", Font.BOLD, 15));
            g2.drawString("Score " + scorePlayer1 + ":" + scorePlayer2, 385, 815);

            try {
                ballImage = ImageIO.read(new File("C:\\Users\\PC\\Projects\\Ping-pong\\Textures\\ball.png"));
            } catch (IOException e) {
                //nothing to do
            }
            g2.drawImage(ballImage, ball_x, ball_y, null);
            //бонус скорости
            if (bonusAppear == BONUS.SPEED_BONUS_APPEAR) {
                bonus.renderSpeed(g2, r, bonusSpeedY);

            }
            //длинная ракетка
            if (bonusAppear == BONUS.LENGTH_BONUS_APPEAR) {
                bonus.renderBigRocket(g2, r,bonusSpeedY);
            }

            if (bonusAppear == BONUS.SMALL_BONUS_APPEAR) {
                bonus.renderSmallRocket(g2, r, bonusSpeedY);
            }

            if (bonusAppear == BONUS.SMALL_ENEMY_APPEAR) {
                bonus.renderSmallRocketEnemy(g2, r, bonusSpeedY);
            }


            if (bonuState == BONUS.SMALL_ENEMY) {
                try {
                    rocketImage2 = ImageIO.read(new File("C:\\Users\\PC\\Projects\\Ping-pong\\Textures\\SmallRocket.png"));
                } catch (IOException e) {
                }
                g2.drawImage(rocketImage2, rocket2_x, ROCKET2_Y, null);
            } else {
                g2.drawImage(rocketImage, rocket2_x, ROCKET2_Y, null);
            }


            if (bonuState == BONUS.LENGTH_BONUS) {
                try {
                    rocketImage = ImageIO.read(new File("C:\\Users\\PC\\Projects\\Ping-pong\\Textures\\BigRocket.png"));
                } catch (IOException e) {
                }

                g2.drawImage(rocketImage, rocket1_x, ROCKET_Y, null);
            }
            else if (bonuState == BONUS.SMALL_BONUS) {
                try {
                    rocketImage = ImageIO.read(new File("C:\\Users\\PC\\Projects\\Ping-pong\\Textures\\SmallRocket.png"));
                } catch (IOException e) {
                }

                g2.drawImage(rocketImage, rocket1_x, ROCKET_Y, null);
            } 

            else {
                g2.drawImage(rocketImage, rocket1_x, ROCKET_Y, null);
                
            }


        }
        else  {
            menu1.render(g2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame == true) {
            if (!sound.isPlaying()) {
                sound.play();
            }
            ball_x = ball_x + ball_speed_x;
            ball_y = ball_y + ball_speed_y;

            //бонус скорости
            if ((bonusAppear == BONUS.SPEED_BONUS_APPEAR) || (bonusAppear == BONUS.LENGTH_BONUS_APPEAR)
                    || (bonusAppear == BONUS.SMALL_BONUS_APPEAR) || (bonusAppear == BONUS.SMALL_ENEMY_APPEAR))
                bonusSpeedY = bonusSpeedY + bonus_speed;

            if ((bonusSpeedY >= ROCKET_Y)) {
                if ((r <= rocket1_x + rocketImage.getWidth()) && (r >= rocket1_x - 50)) {
                    Sound.playSound("C:\\Users\\PC\\Projects\\Ping-pong\\Sound\\Collect_Point_01.wav");
                    if (bonusAppear == BONUS.SPEED_BONUS_APPEAR) {
                        bonuState = BONUS.SPEED_BONUS;
                    }
                    if (bonusAppear == BONUS.LENGTH_BONUS_APPEAR) {
                        bonuState = BONUS.LENGTH_BONUS;
                    }
                    if (bonusAppear == BONUS.SMALL_BONUS_APPEAR)
                        bonuState = BONUS.SMALL_BONUS;
                    if (bonusAppear == BONUS.SMALL_ENEMY_APPEAR)
                        bonuState =  BONUS.SMALL_ENEMY;
                }
                m = rand2.nextInt(HEIGHT_TABLE / 4);
                bonusSpeedY = HEIGHT_TABLE / 2 + m;
                r = rand1.nextInt(WIDTH_TABLE - 45);
                bonusAppear = null;
            }
            int rocket2c_x;
            if ((ball_y + ballImage.getHeight() < HEIGHT_TABLE / 3) && (ball_speed_y < 0)) {
                rocket2c_x = random();
                if (rocket2c_x < 0) {
                    rocket2c_x = 0;
                } else if (rocket2c_x > WIDTH_TABLE - rocketImage.getWidth()) {
                    rocket2c_x = WIDTH_TABLE - rocketImage.getWidth();
                }

                if (rocket2_x < rocket2c_x)
                    rocket2_x += rocket_speed;
                if (rocket2_x > rocket2c_x)
                    rocket2_x -= rocket_speed;
            } else {
                if (rocket2_x + rocketImage.getWidth() / 2 < WIDTH_TABLE / 2)
                    rocket2_x += rocket_speed;
                if (rocket2_x + rocketImage.getWidth()/ 2 > WIDTH_TABLE / 2)
                    rocket2_x -= rocket_speed;
            }


            if (ball_y >= HEIGHT_TABLE - ballImage.getHeight()) {
                Sound.playSound("C:\\Users\\PC\\Projects\\Ping-pong\\Sound\\Pickup_04.wav");
                scorePlayer2 += 1;
                ball_x = BALL_START_X;
                ball_y = BALL_START_Y;
                try {
                    sleep(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            if (ball_y <= 0) {
                Sound.playSound("C:\\Users\\PC\\Projects\\Ping-pong\\Sound\\Pickup_04.wav");
                scorePlayer1 += 1;
                ball_x = BALL_START_X;
                ball_y = BALL_START_Y;
                try {
                    sleep(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

            }
            if (ball_x >= (WIDTH_TABLE - ballImage.getWidth())) {
                Sound.playSound("C:\\Users\\PC\\Projects\\Ping-pong\\Sound\\Pop.wav");
                if (bonuState == BONUS.SPEED_BONUS) {
                    ball_speed_x = -18;
                } else {
                    ball_speed_x = -12;
                }
            }
            if (ball_x <= 0) {
                Sound.playSound("C:\\Users\\PC\\Projects\\Ping-pong\\Sound\\Pop.wav");
                if (bonuState == BONUS.SPEED_BONUS) {
                    ball_speed_x = 18;
                } else {
                    ball_speed_x = 12;
                }
            }

            if (ball_y + ballImage.getHeight() >= ROCKET2_Y) {
                if ((ball_x + ballImage.getWidth() >= rocket2_x) && (ball_x <= rocket2_x + rocketImage.getWidth()) && (ball_y + ballImage.getHeight() >= ROCKET2_Y) &&
                        (ball_y <= ROCKET2_Y + rocketImage.getHeight())) {
                    Sound.playSound("C:\\Users\\PC\\Projects\\Ping-pong\\Sound\\Pop.wav");
                    if (bonuState == BONUS.SPEED_BONUS) {
                        ball_speed_y = 20;
                    } else {
                        ball_speed_y = 14;
                    }
                }
            }

            if (ball_y + ballImage.getHeight() >= ROCKET_Y) {
                if ((ball_x + ballImage.getWidth() >= rocket1_x) && (ball_x <= rocket1_x + rocketImage.getWidth()) && (ball_y + ballImage.getHeight() >= ROCKET_Y) &&
                        (ball_y <= ROCKET_Y + rocketImage.getHeight())) {
                    Sound.playSound("C:\\Users\\PC\\Projects\\Ping-pong\\Sound\\Pop.wav");
                    if (bonuState == BONUS.SPEED_BONUS) {
                        ball_speed_y = -20;
                    } else {
                        ball_speed_y = -14;
                    }
                }
            }

            if (scorePlayer1 == 1) {
                sound.stop();
                Sound.playSound("C:\\Users\\PC\\Projects\\Ping-pong\\Sound\\wiin.wav");
                inGame = false;
                youWin();
            }

            if (scorePlayer2 == 5) {
                sound.stop();
                Sound.playSound("C:\\Users\\PC\\Projects\\Ping-pong\\Sound\\Jingle_Lose_00.wav");
                inGame = false;
                gameOver();

            }
        }
            if ((inGame) || (state == STATE.MENU)) {
                repaint();
            }

    }


    @Override

    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if ((e.getX() <= WIDTH_TABLE - rocketImage.getWidth()/2) && (e.getX() >= rocketImage.getWidth()/2 - 5))
            rocket1_x = e.getX() - rocketImage.getWidth()/2;
        if (inGame) {
            repaint();
        }

    }

}
