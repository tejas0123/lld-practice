import java.util.Optional;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player turnToPlay;
    private GameStatus status;
    private Player winner;

    public Game(Board gameBoard, Player player1, Player player2) {
        this.board = gameBoard;
        this.player1 = player1;
        this.player2 = player2;
        this.turnToPlay = player1;
        this.status = GameStatus.IN_PROGRESS;
        this.winner = null;
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(this.winner);
    }

    public Player getCurrentTurn() {
        return this.turnToPlay;
    }

    public String getBoardView() {
        return this.board.toString();
    }

    public GameStatus getStatus() {
        return this.status;
    }

    public GameStatus makeMove(int row, int col) throws CellOccupiedException, ArrayIndexOutOfBoundsException {
        if(this.status != GameStatus.IN_PROGRESS) {
            throw new IllegalStateException("Cannot make a move. Game not in progress!!!");
        }

        board.placeSymbol(row, col, this.turnToPlay.getSymbol());
        Symbol winnerSymbol = board.getWinnerSymbol();

        if(winnerSymbol != Symbol.EMPTY) {
            this.winner = this.turnToPlay;
            this.status = GameStatus.WON;
            return this.status;
        }

        if(board.isFull()) {
            this.status = GameStatus.DRAW;
            return this.status;
        }

        swapTurns();

        return GameStatus.IN_PROGRESS;
    }

    private void swapTurns() {
        this.turnToPlay = (turnToPlay.equals(player1)) ? player2 : player1;
    }
}
