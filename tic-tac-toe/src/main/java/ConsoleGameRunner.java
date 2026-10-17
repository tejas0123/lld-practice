public class ConsoleGameRunner implements GameRunner {

    private final GameIO gameIO;
    private int boardSize;

    public ConsoleGameRunner(GameIO gameIO) {
        this.gameIO = gameIO;
    }

    @Override
    public void run() {
        final Game game = setup();

        while(game.getStatus() == GameStatus.IN_PROGRESS) {
            gameIO.print(game.getCurrentTurn().getName() + "'s turn");
            int row = gameIO.getNumericInput("Enter row:", 0, boardSize - 1);
            int col = gameIO.getNumericInput("Enter col:", 0, boardSize - 1);

            try {
                GameStatus gameStatus = game.makeMove(row, col);
                gameIO.print(game.getBoardView());
                if(gameStatus == GameStatus.WON) {
                    game.getWinner().ifPresent(player -> gameIO.print(player.getName() + " wins!!!"));
                } else if(gameStatus == GameStatus.DRAW) {
                    gameIO.print("It's a draw!!!");
                }
            } catch(CellOccupiedException | ArrayIndexOutOfBoundsException exception) {
                gameIO.print(exception.getMessage());
            }
        }
    }

    private Game setup() {
        Player player1 = createPlayer();
        Player player2 = createSecondPlayer(player1.getSymbol());
        Board gameBoard = createGameBoard();
        boardSize = gameBoard.getSize();
        return new Game(gameBoard, player1, player2);
    }

    private Player createPlayer() {
        String playerName = gameIO.getStringInput("Enter player 1 name:");
        Symbol playerSymbol = gameIO.getSymbol("Enter player 1 symbol X / O:");
        return new Player(playerName, playerSymbol);
    }

    private Player createSecondPlayer(Symbol player1Symbol) {
        String playerName = gameIO.getStringInput("Enter player 2 name:");
        Symbol player2Symbol = (player1Symbol == Symbol.X) ? Symbol.O : Symbol.X;
        return new Player(playerName, player2Symbol);
    }

    private Board createGameBoard() {
        int boardSize = gameIO.getNumericInput("Enter board size:", 3, 5);
        return new Board(boardSize);
    }
}
