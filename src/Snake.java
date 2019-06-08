
public class Snake {
  // In Java, Class Variables should be private so that only its methods can change them.
  private int centerX = 100;
  private int centerY = 382;
  private boolean up = false;
  private boolean down = false;
  private boolean left = false;
  private boolean jumped = false;

  private int speedX = 0;
  private int speedY = 1;

  public void update() {

    // Moves Character or Scrolls Background accordingly.
    if (speedX < 0) {
      centerX += speedX;
    } else if (speedX == 0) {
      //System.out.println("Do not scroll the background.");

    } else {
      if (centerX <= 684) {
        centerX += speedX;
      } else {
       // System.out.println("Scroll Background Here");
      }
    }

    // Updates Y Position

    if (centerY + speedY >= 513) {
      centerY = 513;
      up = true;
    } else {
      centerY += speedY;
      up = false;
    }
    

    // Handles Jumping
    if (jumped == true) {
      speedY += 1;

      if (centerY + speedY >= 513) {
        centerY = 513;
        speedY = 0;
        jumped = false;
      }
    }
    
    // Prevents going beyond Y coordinate of 0 (top)
    if (centerY + speedY <= 60) {
      centerY = 61;
      System.out.println("Dead");
    }
 // Prevents going beyond Y coordinate of  410 (bottom)
    if (centerY + speedY >= 513) {
      System.out.println("Dead");
    }

    // Prevents going beyond X coordinate of 0 (left)
    if (centerX + speedX <= 60) {
      centerX = 61;
      System.out.println("Dead");
    }
    // Prevents going beyond X coordinate of 676 (right)
    if (centerX + speedX >= 684) {
      System.out.println("Dead");
    }
  }

  public boolean isUp() {
    return up;
  }

  public boolean isDown() {
    return down;
  }

  public boolean isLeft() {
    return left;
  }

  public void setUp(boolean up) {
    this.up = up;
  }

  public void setDown(boolean down) {
    this.down = down;
  }

  public void setLeft(boolean left) {
    this.left = left;
  }

  public void moveRight() {
    speedX = 4;
  }

  public void moveLeft() {
    speedX = -4;
  }
  
  public void moveUp() {
    speedY = -4;
  }
  
  public void moveDown() {
    speedY = 4;
  }

  public void stop() {
    speedX = 0;
  }

  public void jump() {
    if (jumped == false) {
      speedY = -15;
      jumped = true;
    }

  }

  public void setCenterX(int centerX) {
    this.centerX = centerX;
  }

  public void setCenterY(int centerY) {
    this.centerY = centerY;
  }

  public int getCenterX() {
    return centerX;
  }

  public int getCenterY() {
    return centerY;
  }

  public boolean isJumped() {
    return jumped;
  }

  public int getSpeedX() {
    return speedX;
  }

  public int getSpeedY() {
    return speedY;
  }

  public void setJumped(boolean jumped) {
    this.jumped = jumped;
  }

  public void setSpeedX(int speedX) {
    this.speedX = speedX;
  }

  public void setSpeedY(int speedY) {
    this.speedY = speedY;
  }
  
}

