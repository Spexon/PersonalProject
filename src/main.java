import java.util.Scanner;

/**
 * @author Vladimir Hardy
 * @Brief Main menu showing all the GUI tutorials I've completed
 */
public class main {

  public static void main(String[] args) {
    boolean restart = true;
    Scanner input = new Scanner(System.in);
    while (restart) {
      System.out.println("Please select an option below:");
      System.out.println("1. Hello World Button");
      System.out.println("2. Form Menu");
      System.out.println("3. Colorful Circles");
      System.out.println("4. Snake");
      System.out.println("5. Exit");
      int num = input.nextInt();
      switch (num) {
        case 1:
          javafx.application.Application.launch(HelloWorld.class);
          break;
        case 2:
          javafx.application.Application.launch(Form.class);
          break;
        case 3:
          javafx.application.Application.launch(ColorfulCircles.class);
          break;
        case 4:
          //javafx.application.Application.launch(SnakeStartingClass.class);
          break;
        case 5:
          System.out.println("Have a nice day!");
          restart = false;
          break;
         default:
           System.out.println("Invalid Selection");
         
      }
    }
    input.close();
  }
}
