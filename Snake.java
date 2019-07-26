
public class Snake {
  // In Java, Class Variables should be private so that only its methods can change them.
  private int x, y;
  private int centerX = 100, centerY = 382;
  private int currentDirectionX = 61, currentDirectionY = 61;
  private boolean up = false, down = false, left = false, right = true, stopped = false;
  private int speedX = 0, speedY = 1;

  /**
   * @author Vladimir Hardy
   * @brief updates and moves the snake.
   */
  public void update() {

    // Updates Y Position
    if (centerY + speedY >= 520) {
      centerY = 520;
    } else {
      centerY += speedY;
    }

    // Stops all other movement when one arrow key is pressed
    if (speedX > 0) {
      currentDirectionX = 61;
      currentDirectionY = 63;
      up = false;
      down = false;
      left = false;
      right = true;
      stopped = true;
    } else if (speedX < 0) {
      currentDirectionX = 61;
      currentDirectionY = 63;
      up = false;
      down = false;
      left = true;
      right = false;
      stopped = true;
    } else if (speedY > 0) {
      currentDirectionY = 31;
      up = false;
      down = true;
      left = false;
      right = false;
      stopped = true;
    } else if (speedY < 0){
      currentDirectionY = 91;
      up = true;
      down = false;
      left = false;
      right = false;
      stopped = false;
    } else {
      up = false;
      down = false;
      left = false;
      right = false;
      stopped = true;
    }
  }

  public Snake(int x, int y) { // Constructor for Links of snake
    centerX = x;
    centerY = y;
  }

  // Getters and Setters below:

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
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

  public boolean isRight() {
    return right;
  }
  
  public boolean isStopped() {
    return stopped;
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

  public void stopX() {
    speedX = 0;
  }

  public void stopY() {
    speedY = 0;
  }
  
  public void stopped() {
    speedX = 0;
    speedY = 0;
  }

  public void setCurrentDirectionX(int currentDirectionX) {
    this.currentDirectionX = currentDirectionX;
  }

  public void setCurrentDirectionY(int currentDirectionY) {
    this.currentDirectionX = currentDirectionY;
  }

  public void setCenterX(int centerX) {
    this.centerX = centerX;
  }

  public void setCenterY(int centerY) {
    this.centerY = centerY;
  }

  public int getCurrentDirectionX() {
    return currentDirectionX;
  }

  public int getCurrentDirectionY() {
    return currentDirectionY;
  }

  public int getCenterX() {
    return centerX;
  }

  public int getCenterY() {
    return centerY;
  }

  public int getSpeedX() {
    return speedX;
  }

  public int getSpeedY() {
    return speedY;
  }

  public void setSpeedX(int speedX) {
    this.speedX = speedX;
  }

  public void setSpeedY(int speedY) {
    this.speedY = speedY;
  }

}

