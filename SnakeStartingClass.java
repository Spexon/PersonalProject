// Import awt stuff because of applet?
import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Image;

public class SnakeStartingClass extends Applet implements Runnable, KeyListener {
  private Image image;
  private Graphics second;
  
  private Snake snake;
  
  public void init() {
    setSize(800, 480);
    setBackground(Color.BLACK);
    setFocusable(true);
    Frame frame = (Frame) this.getParent().getParent();
    frame.setTitle("Snake");
    addKeyListener(this);
  }

  public void start() {
    Thread thread = new Thread(this);
    thread.start();
    snake = new Snake();
  }

  public void update(Graphics g) {
    // TODO Auto-generated method stub
    super.update(g);
  }

  public void paint(Graphics g) {
    // TODO Auto-generated method stub
    super.paint(g);
  }

  public void stop() {

  }

  public void destroy() {

  }

  public void run() {
    while (true) {
      repaint(); // this calls paint

      try {
        Thread.sleep(17);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }

  public void keyPressed(KeyEvent e) { //Will show only when debugging
    switch (e.getKeyCode()) {
      case KeyEvent.VK_UP:
        System.out.println("Moving Up");
        break;

      case KeyEvent.VK_DOWN:
        System.out.println("Moving Down");
        break;
        
      case KeyEvent.VK_LEFT:
        System.out.println("Moving Left");
        break;

      case KeyEvent.VK_RIGHT:
        System.out.println("Moving right");
        break;

      case KeyEvent.VK_SPACE:
        System.out.println("Jumping");
        break;
    }

  }

  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
    case KeyEvent.VK_UP:
        System.out.println("Stop moving up");
        break;

    case KeyEvent.VK_DOWN:
        System.out.println("Stop moving down");
        break;

    case KeyEvent.VK_LEFT:
        System.out.println("Stop moving left");
        break;

    case KeyEvent.VK_RIGHT:
        System.out.println("Stop moving right");
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
