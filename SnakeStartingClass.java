// Import awt stuff because of applet?
import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;


public class SnakeStartingClass extends Applet implements Runnable, KeyListener {
  
  private Snake snake;
  private Image image, currentSprite , character, characterUp, background;
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
  character = getImage(base, "data/character.png");
  characterUp = getImage(base, "data/characterUp.png");
  currentSprite = character;
  background = getImage(base, "data/background.png");
  }

  public void start() {
    bg1 = new Background(0, 0);
    bg2 = new Background(2160, 0);
    snake = new Snake();
    
    Thread thread = new Thread(this);
    thread.start();
  }
  
  public void stop() {

  }

  public void destroy() {

  }

  public void run() {
    while (true) {
      snake.update();
      if(snake.isUp()) {
        currentSprite = characterUp;
      }
      else if (snake.isUp() == true && snake.isDown() == false && snake.isLeft() == false) {
        currentSprite = characterUp;
      }
      else if (snake.isUp() == false && snake.isDown() == true && snake.isLeft() == false) {
        //currentSprite = characterDown;
      }
      else if (snake.isUp() == false && snake.isDown() == false && snake.isLeft() == true) {
        //currentSprite = characterLeft;
      }
      else {
        currentSprite = character;
      }
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
    g.drawImage(currentSprite, snake.getCenterX() - 61, snake.getCenterY() - 63, this); 
  }

  //Change so that when user presses a key, all other directions will stop until another key is pressed
  public void keyPressed(KeyEvent e) { //Will show only when debugging
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
        snake.moveUp();
        break;

      case KeyEvent.VK_DOWN:
        snake.moveDown();
        break;
        
      case KeyEvent.VK_LEFT:
        snake.moveLeft();
        break;

      case KeyEvent.VK_RIGHT:
        snake.moveRight();
        break;

      case KeyEvent.VK_SPACE:
        snake.jump();
        break;
    }

  }

  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
    case KeyEvent.VK_UP:
        snake.stop();
        System.out.println("Stop moving up");
        break;

    case KeyEvent.VK_DOWN:
        snake.stop();
        System.out.println("Stop moving down");
        break;

    case KeyEvent.VK_LEFT:
        //snake.stop();
        break;

    case KeyEvent.VK_RIGHT:
        //snake.stop();
        break;

    case KeyEvent.VK_SPACE:
        System.out.println("Stop jumping");
        break;

    }
  }

  public void keyTyped(KeyEvent arg0) {
    // TODO Auto-generated method stub

  }
}
