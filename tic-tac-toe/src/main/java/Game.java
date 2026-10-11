import java.util.Scanner;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player turnToPlay;
    private GameStatus status;
    private Player winner;
    private final Scanner scanner;

    public Game(Scanner scanner) {
        this.scanner = scanner;
        this.status = GameStatus.NOT_STARTED;
        this.board = null;
    }

    public Boolean isGameInitialized() {
        return this.status.equals(GameStatus.READY);
    }

    public void initGame() {
        System.out.println("Enter board size: ");
        int size = 0;

        while (this.board == null) {
            size = scanner.nextInt();
            scanner.nextLine();

            if (size < 3 || size > 5) {
                System.out.println("Invalid size. Choose between 3, 4 and 5");
                continue;
            }
            this.board = new Board(size);
        }

        System.out.println("Enter player 1 name: ");
        String name1 = scanner.nextLine();

        System.out.println("Choose player 1 symbol: X / O");
        Symbol player1Symbol = Symbol.EMPTY;

        while (player1Symbol == Symbol.EMPTY) {
            String symbol = scanner.nextLine().trim();

            if (symbol.equalsIgnoreCase("X")) {
                player1Symbol = Symbol.X;
            } else if (symbol.equalsIgnoreCase("O")) {
                player1Symbol = Symbol.O;
            } else {
                System.out.println("Invalid symbol entered. Choose X or O.");
            }
        }

        player1 = new Player(name1, player1Symbol);

        System.out.println("Enter player 2 name: ");
        String name2 = scanner.nextLine();

        Symbol player2Symbol = (player1Symbol.equals(Symbol.X) ? Symbol.O : Symbol.X);
        player2 = new Player(name2, player2Symbol);

        System.out.println(player1.getName() + " plays " + player1Symbol.toString());
        System.out.println(player2.getName() + " plays " + player2Symbol.toString());
        turnToPlay = player1;
        winner = null;
        status = GameStatus.READY;
    }

    public void play() {
        this.status = GameStatus.IN_PROGRESS;

        while(status == GameStatus.IN_PROGRESS) {
            System.out.println(turnToPlay.getName() + "'s turn");
            int row = getCellInput("row");
            int col = getCellInput("col");

            board.makeEntry(row, col, turnToPlay.getSymbol());
            board.printBoard();

            Symbol winnerSymbol = board.getWinnerSymbol();

            if(winnerSymbol != Symbol.EMPTY) {
                Player gameWinner = (player1.getSymbol() == winnerSymbol) ? player1 : player2;
                this.winner = gameWinner;
                status = GameStatus.WON;
                System.out.println(gameWinner.getName() + " wins!!!");
            } else if(board.isFull()) {
                status = GameStatus.DRAW;
                System.out.println("It's a draw!!!");
            }

            turnToPlay = (turnToPlay.equals(player1) ? player2 : player1);
        }
    }

    private int getCellInput(String cellParam) {
        int cellValue = -1;

        while(true) {
            System.out.println("Enter " + cellParam + " number: ");
            cellValue = scanner.nextInt();
            scanner.nextLine();

            if(cellValue < 0 || cellValue >= this.board.getSize()) {
                System.out.println("Invalid " + cellParam + " value..");
                continue;
            }
            break;
        }
        return cellValue;
    }
}
