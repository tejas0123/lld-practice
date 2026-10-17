public class ConsoleGameRunner implements GameRunner {

    private final GameIO gameIO;
    private final Game game;
    private int boardSize;

    public ConsoleGameRunner(GameIO gameIO) {
        this.gameIO = gameIO;
        this.game = setup();
    }

    @Override
    public void run() {
        GameStatus gameStatus = GameStatus.IN_PROGRESS;

        while(gameStatus == GameStatus.IN_PROGRESS) {
            gameIO.print(game.getCurrentTurn().getName() + "'s turn");
            int row = gameIO.getNumericInput("Enter row:", 0, boardSize - 1);
            int col = gameIO.getNumericInput("Enter col", 0, boardSize - 1);

            try {
                gameStatus = game.makeMove(row, col);
                game.showBoard(gameIO);
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

    @Override
    public Player createPlayer() {
        String playerName = gameIO.getStringInput("Enter player 1 name: ");
        Symbol playerSymbol = gameIO.getSymbol("Enter player 1 symbol");
        return new Player(playerName, playerSymbol);
    }

    @Override
    public Player createSecondPlayer(Symbol player1Symbol) {
        String playerName = gameIO.getStringInput("Enter player 2 name: ");
        Symbol player2Symbol = (player1Symbol == Symbol.X) ? Symbol.O : Symbol.X;
        return new Player(playerName, player2Symbol);
    }

    @Override
    public Board createGameBoard() {
        int boardSize = gameIO.getNumericInput("Enter board size", 3, 5);
        return new Board(boardSize);
    }

    private Game setup() {
        Player player1 = createPlayer();
        Player player2 = createSecondPlayer(player1.getSymbol());
        Board gameBoard = createGameBoard();
        boardSize = gameBoard.getSize();
        return new Game(gameBoard, player1, player2);
    }
}
