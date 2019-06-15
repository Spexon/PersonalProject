// Import awt stuff because of applet?
import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class SnakeStartingClass extends Applet implements Runnable, KeyListener {

  private Snake snake, link1, link2, link3, link4, link5;
  private Image image, currentSprite, character, characterUp, characterDown, characterLeft,
      background;
  private Graphics second;
  private URL base;
  private static Background bg1, bg2;
  

  public void init() {
    setSize(800, 480);
    setBackground(Color.BLACK);
    setFocusable(true);
    addKeyListener(this);
    Frame frame = (Frame) this.getParent().getParent();
    frame.setTitle("Snake");
    try {
      base = getDocumentBase();
    } catch (Exception e) {
      // TODO: handle exception
    }
    // Image Setups
    character = getImage(base, "data/characterlink.png");
    characterUp = getImage(base, "data/characterUp.png");
    characterDown = getImage(base, "data/characterDown.png");
    characterLeft = getImage(base, "data/characterLeft.png");
    currentSprite = character;
    background = getImage(base, "data/background.png");
  }

  public void start() {
    bg1 = new Background(0, 0);
    bg2 = new Background(2160, 0);
    link1 = new Snake(61, 63);
    link2 = new Snake(91, 63);
    link3 = new Snake(121, 63);
    link4 = new Snake(151, 63);
    link5 = new Snake(171, 63);

    Thread thread = new Thread(this);
    thread.start();
  }

  public void stop() {

  }

  public void destroy() {

  }

  public void run() {
    while (true) {
      link1.update();
      link2.update();
      link3.update();
      link4.update();
      link5.update();
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

  public void paint(Graphics g) {
    g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
    g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
   
    g.drawImage(currentSprite, link1.getCenterX() - link1.getCurrentDirectionX(),
        link1.getCenterY() - link1.getCurrentDirectionY(), this);
    if (link1.getX() < link2.getX()) {
      System.out.println("Move link 2!");
    }
    g.drawImage(currentSprite, link2.getCenterX() - (link2.getCurrentDirectionX() + 30),
        link2.getCenterY() - 63, this);
    g.drawImage(currentSprite, link3.getCenterX() - (link3.getCurrentDirectionX() + 60),
        link3.getCenterY() - 63, this);
    g.drawImage(currentSprite, link4.getCenterX() - (link4.getCurrentDirectionX() + 90),
        link4.getCenterY() - 63, this);
    g.drawImage(currentSprite, link5.getCenterX() - (link5.getCurrentDirectionX() + 120),
        link5.getCenterY() - 63, this);
  }

  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
        link1.stopX();
        link1.isUp();
        link1.moveUp();
        link2.stopX();
        link2.isUp();
        link2.moveUp();
        link3.stopX();
        link3.isUp();
        link3.moveUp();
        link4.stopX();
        link4.isUp();
        link4.moveUp();
        link5.stopX();
        link5.isUp();
        link5.moveUp();
        break;

      case KeyEvent.VK_DOWN:
        link1.stopX();
        link1.isDown();
        link1.moveDown();
        link2.stopX();
        link2.isDown();
        link2.moveDown();
        link3.stopX();
        link3.isDown();
        link3.moveDown();
        link4.stopX();
        link4.isDown();
        link4.moveDown();
        link5.stopX();
        link5.isDown();
        link5.moveDown();
        break;

      case KeyEvent.VK_LEFT:
        link1.stopY();
        link1.isLeft();
        link1.moveLeft();
        link2.stopY();
        link2.isLeft();
        link2.moveLeft();
        link3.stopY();
        link3.isLeft();
        link3.moveLeft();
        link4.stopY();
        link4.isLeft();
        link4.moveLeft();
        link5.stopY();
        link5.isLeft();
        link5.moveLeft();
        break;

      case KeyEvent.VK_RIGHT:
        link1.stopY();
        link1.moveRight();
        link2.stopY();
        link2.moveRight();
        link3.stopY();
        link3.moveRight();
        link4.stopY();
        link4.moveRight();
        link5.stopY();
        link5.moveRight();
        break;

      case KeyEvent.VK_SPACE:
        link1.jump();
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
