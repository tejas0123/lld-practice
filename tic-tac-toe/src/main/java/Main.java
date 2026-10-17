import java.io.Console;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameIO consoleIO = new ConsoleIO(scanner);
        GameRunner consoleGameRunner = new ConsoleGameRunner(consoleIO);
        consoleGameRunner.run();
    }
}
