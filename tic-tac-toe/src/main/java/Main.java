import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game(scanner);

        while(!game.isGameInitialized()) {
            game.initGame();
        }

        System.out.println("Game started. ");
        game.play();
    }
}
