import java.util.Scanner;

public class ConsoleIO implements GameIO{
    private final Scanner scanner;

    public ConsoleIO(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public Symbol getSymbol(String message) {
        print(message);
        Symbol symbol = Symbol.EMPTY;

        while (symbol == Symbol.EMPTY) {
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("X")) {
                symbol = Symbol.X;
            } else if (input.equalsIgnoreCase("O")) {
                symbol = Symbol.O;
            } else {
                print("Invalid symbol entered. Choose X or O.");
            }
        }
        return symbol;
    }

    @Override
    public String getStringInput(String message) {
        while(true) {
            print(message);

            String line = scanner.nextLine().trim();
            if(!line.isBlank()) {
                return line;
            }
            print("Enter a valid string");
        }
    }

    @Override
    public int getNumericInput(String message, int min, int max) {
        while(true) {
            print(message);

            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if(value >= min && value <= max) {
                    return value;
                }
                print("Enter a number between " + min + " and " + max);
            } catch (NumberFormatException exception) {
                print("Please enter a valid number");
            }
        }
    }

}
