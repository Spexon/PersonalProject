import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

/**
 * @author Vladimir Hardy
 * @brief begins all the necessary processes for the program to launch
 */

public class SnakeStartingClass extends Applet implements Runnable, KeyListener {

  private Snake snake, link1, link2;
  private Image image, characterBody, background, characterHead, apple;
  private final int LINKSIZE = 5, TOTALSIZE = 900;
  private final int X[] = new int[TOTALSIZE];
  private final int Y[] = new int[TOTALSIZE];
  private Graphics second;
  private URL base;
  private static Background bg1, bg2;
  private int dots;
  public int appleX, appleX2, appleY, appleY2;
  public boolean checkApple = false;

  /**
   * @brief Creates all necessary entities before program starts
   */
  public void init() {
    setSize(800, 480);
    setBackground(Color.BLACK);
    setFocusable(true);
    addKeyListener(this);
    Frame frame = (Frame) this.getParent().getParent();
    frame.setTitle("Snake");
    dots = 10;
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
    background = getImage(base, "data/background.png");
  }

  /**
   * @brief starts the program, initialized background
   */
  public void start() {
    bg1 = new Background(0, 0);
    bg2 = new Background(2160, 0);
    link1 = new Snake(61, 63);
    link2 = new Snake(91, 63);

    Thread thread = new Thread(this);
    thread.start();
  }

  /**
   * @brief First line that is read when program begins
   */
  public void run() {
    boolean restart = false;
    while (!restart) {
      move();
      if (!checkApple) {
        locateApple();
      }
      checkCollision();
      link1.update();
      link2.update();
      bg1.update();
      bg2.update();
      repaint(); // this calls paint

      try {
        Thread.sleep(17);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * @brief updates all necessary objects
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

    //System.out.println(X[0] + " " + Y[0]);
    if ((X[0] > (appleX - 25) && X[0] < (appleX + 25)) && 
        (Y[0] > (appleY - 25) && Y[0] < (appleY + 25))) {
      checkApple = false;
      dots+=10;
      System.out.println("Apple Eaten!");
    }

    for (int z = dots; z > 0; z--) {
      if ((X[0] == X[z]) && (Y[0] == Y[z])) {
        System.out.println("Hit itself");
        stop();
        destroy();
      }

      if (Y[0] >= 460) { // Prevents going beyond Y coordinate of 460 (bottom)
        System.out.println("Dead");
        Y[0] = 459;
      }

      if (Y[0] < 0) { // Prevents going beyond Y coordinate of 0 (top)
        System.out.println("Dead");
        Y[0] = 1;
      }

      if (X[0] >= 775) { // Prevents going beyond X coordinate of 790 (right)
        System.out.println("Dead");
        X[0] = 776;
      }

      if (X[0] < 0) { // Prevents going beyond X coordinate of 0 (left)
        System.out.println("Dead");
        X[0] = 1;
      }
    }
  }

  /**
   * @brief Generates a random location for the apple
   */
  public void locateApple() {
    Random rndNum = new Random();
    appleX = rndNum.nextInt(785) + 1;
    appleY = rndNum.nextInt(455) + 1;
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
      X[0] -= LINKSIZE;
    }

    if (link1.isRight()) {
      X[0] += LINKSIZE;
    }

    if (link1.isUp()) {
      Y[0] -= LINKSIZE;
    }

    if (link1.isDown()) {
      Y[0] += LINKSIZE;
    }
  }
  
  public void stop(boolean restart) {
    if(restart) {
      System.out.println("Better luck next time!");
    }
    System.out.println("In here");
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
  }

  /**
   * @brief sets certain values to true or false when key is pressed, and moves in said direction
   */
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
        link1.stopX();
        link1.isUp();
        link1.moveUp();
        break;

      case KeyEvent.VK_DOWN:
        link1.stopX();
        link1.isDown();
        link1.moveDown();
        break;

      case KeyEvent.VK_LEFT:
        link1.stopY();
        link1.isLeft();
        link1.moveLeft();
        break;

      case KeyEvent.VK_RIGHT:
        link1.stopY();
        link1.moveRight();
        break;
    }
  }

  public void keyTyped(KeyEvent arg0) {
    // TODO Auto-generated method stub

  }

  public void keyReleased(KeyEvent arg0) {
    // TODO Auto-generated method stub

  }
}
