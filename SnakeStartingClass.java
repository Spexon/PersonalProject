import java.applet.Applet;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Random;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import java.awt.Toolkit;
import java.util.Timer;


/**
 * @author Vladimir Hardy
 * @brief begins all the necessary processes for the program to launch
 */


public class SnakeStartingClass extends Applet implements Runnable, KeyListener, ActionListener {

  private Snake link1;
  private Image image, characterBody, background, characterHead, apple, gameOver;
  private int LINKSPEED = 5, TOTALSIZE = 900;
  private final int X[] = new int[TOTALSIZE];
  private final int Y[] = new int[TOTALSIZE];
  private Graphics second;
  private JButton btn, easy, normal, hard;
  private URL base;
  private static Background bg1, bg2;
  private int dots;
  public int appleX, appleX2, appleY, appleY2, addScore;
  public boolean checkApple, restart, collision = true, resume = true, startGame = false;


  public void frontPage() {

    easy = new JButton("easy"); // 1
    add(easy);
    easy.setBounds(100, 50, 550, 25);
    easy.setVisible(true);
    easy.addActionListener(new ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent e) {
        System.out.println("Easy button");
        LINKSPEED = 4;
        resume = true;
      }
    });

    normal = new JButton("Normal"); // 2
    add(normal);
    normal.setBounds(100, 50, 700, 25);
    normal.setVisible(true);
    normal.addActionListener(new ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent e) {
        System.out.println("Normal button");
        LINKSPEED = 6;
        resume = true;
      }
    });

    hard = new JButton("Hard"); // 3
    add(hard);
    hard.setBounds(100, 50, 850, 25);
    hard.setVisible(true);
    hard.addActionListener(new ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent e) {
        System.out.println("Hard button");
        LINKSPEED = 8;
        resume = true;
      }
    });
  }

  /**
   * @brief Creates all necessary entities before program starts and is called only once
   */
  public void init() {

    setSize(1910, 960);
    setBackground(Color.BLACK);
    setFocusable(true);
    addKeyListener(this);
    Frame frame = (Frame) this.getParent().getParent();
    frame.setTitle("Snake");
    setLayout(new FlowLayout());
    dots = 10;
    addScore = 0;
    try {
      base = getDocumentBase();
    } catch (Exception e) {
      // TODO: handle exception
    }
    // Image Setups
    ImageIcon iib = new ImageIcon("data/characterlink.png");
    characterBody = iib.getImage();
    ImageIcon iih = new ImageIcon("data/characterHead.png");
    characterHead = iih.getImage();
    ImageIcon iia = new ImageIcon("data/apple.png");
    apple = iia.getImage();
    ImageIcon iig = new ImageIcon("data/gameOver.png");
    gameOver = iig.getImage();
    background = getImage(base, "data/background2.png");
    btn = new JButton("Restart");

  }

  /**
   * @brief execution starts from this method
   */
  public void start() {
    bg1 = new Background(0, 0);
    bg2 = new Background(2160, 0);
    link1 = new Snake(413, 78); // 213,78 starting point
    X[0] = 980; //sets starting position of snake
    Y[0] = 430;
    Thread thread = new Thread(this);
    thread.start();
    // frontPage();
  }

  /**
   * @brief First line that is read when program begins
   */
  public void run() {

    while (!restart && resume) {
      move();
      if (!checkApple) {
        locateApple();
      }
      checkCollision();
      link1.update();
      bg1.update();
      bg2.update();
      repaint(); // this calls paint

      try {
        Thread.sleep(17);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    add(btn);
    btn.setBounds(100, 50, 100, 100);
    btn.setVisible(true);
    btn.addActionListener(this);
  }

  /**
   * @brief updates all necessary objects at 60fps
   */
  public void update(Graphics g) {
    if (image == null) {
      image = createImage(this.getWidth(), this.getHeight());
      second = image.getGraphics();
    }
    second.setColor(getBackground());
    second.fillRect(0, 0, getWidth(), getHeight());
    second.setColor(getForeground());
    paint(second);
    g.drawImage(image, 0, 0, this);
  }

  /**
   * @brief checks if snake collides with certain objects, such as itself or the borders
   */
  private void checkCollision() {
    // System.out.println(X[0] + " " + Y[0]);
    if (collision) {
      if ((X[0] > (appleX - 25) && X[0] < (appleX + 25))
          && (Y[0] > (appleY - 25) && Y[0] < (appleY + 25))) {
        score();
        checkApple = false;
        dots += 10;
        System.out.println("Apple Eaten!");
      }

      for (int z = dots; z > 0; z--) {
        if ((X[0] == X[z]) && (Y[0] == Y[z])) {
          System.out.println("Hit itself");
          restart = true;
        }
      
        if (Y[0] >= 836) { // Prevents going beyond Y coordinate of 960 (bottom)
          Y[0] = 835;
        }

        if (Y[0] < 78) { // Prevents going beyond Y coordinate of 0 (top)
          Y[0] = 79;
        }

        if (X[0] >= 1765) { // Prevents going beyond X coordinate of 1910 (right)
          X[0] = 1764;
        }

        if (X[0] < 214) { // Prevents going beyond X coordinate of 0 (left)
          X[0] = 215;
        }
      }
    }
  }

  /**
   * @brief Generates a random location for the apple
   */
  public void locateApple() {// 213,78 starting point
    Random rndNum = new Random();
    appleX = rndNum.nextInt(1546) + 214;
    appleY = rndNum.nextInt(752) + 78;
    checkApple = true;
  }

  /**
   * @brief moves the snake when a certain key is pressed
   */
  private void move() {
    
    for (int z = dots; z > 0; z--) { // X and Y position of snake
      
      X[z] = X[(z - 1)];
      Y[z] = Y[(z - 1)];
    }

    if (link1.isLeft()) {
      X[0] -= LINKSPEED;
    }

    if (link1.isRight()) {
      X[0] += LINKSPEED;
    }

    if (link1.isUp()) {
      Y[0] -= LINKSPEED;
    }

    if (link1.isDown()) {
      Y[0] += LINKSPEED;
    }
  }

  /**
   * @brief paints background and snake at X and Y coordinates
   */
  public void paint(Graphics g) {
    g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
    g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
    g.drawImage(apple, appleX, appleY, this);
    for (int z = 0; z < dots; z++) {
      if (z == 0) {
        g.drawImage(characterHead, X[z], Y[z], this);

      } else {
        g.drawImage(characterBody, X[z], Y[z], this);
      }
    }
    Toolkit.getDefaultToolkit().sync();
    if (restart) {
      g.drawImage(gameOver, 750, 360, this);
    }
  }

  public int score() {
    addScore += 10;
    System.out.println("Score:" + addScore);
    return addScore;
  }

  /**
   * @brief sets certain values to true or false when key is pressed, and moves in said direction
   */
  public void keyPressed(KeyEvent e) {
    startGame = true;
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:

        link1.stopX();
        link1.isUp();
        link1.moveUp();
        new java.util.Timer().schedule(new java.util.TimerTask() {
          @Override
          public void run() {
            collision = true;
            cancel();
          }
        }, 2000);
        break;

      case KeyEvent.VK_DOWN:

        link1.stopX();
        link1.isDown();
        link1.moveDown();
        new java.util.Timer().schedule(new java.util.TimerTask() {
          @Override
          public void run() {
            collision = true;
            cancel();
          }
        }, 2000);
        break;
      case KeyEvent.VK_LEFT:

        link1.stopY();
        link1.isLeft();
        link1.moveLeft();
        new java.util.Timer().schedule(new java.util.TimerTask() {
          @Override
          public void run() {
            collision = true;
            cancel();
          }
        }, 2000);
        break;
      case KeyEvent.VK_RIGHT:

        link1.stopY();
        link1.moveRight();
        new java.util.Timer().schedule(new java.util.TimerTask() {
          @Override
          public void run() {
            collision = true;
            cancel();
          }
        }, 2000);
        break;

      case KeyEvent.VK_SPACE:
        collision = false;
        link1.stopped();
    }
  }

  public void keyTyped(KeyEvent arg0) {
    // TODO Auto-generated method stub

  }

  public void keyReleased(KeyEvent arg0) {
    // TODO Auto-generated method stub

  }

  /**
   * @brief Action that restarts the game.
   */
  public void actionPerformed(java.awt.event.ActionEvent e) {
    restart = false;
    startGame = true;

    start();
    run();
    System.out.println("Restarting... (Not working yet)");

  }
}
