
public class Background {
  private int bgX, bgY, speedX;
  public Background (int x, int y){
    bgX = x;
    bgY = y;
    speedX = 0;
 }
  public void update() {
    bgX += speedX;

    if (bgX <= -2160){
       bgX += 4320;
    }
 }
  
}
