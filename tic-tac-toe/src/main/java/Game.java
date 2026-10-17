import java.util.Optional;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player turnToPlay;
    private GameStatus status;
    private Player winner;

    public Game(Board gameBoard, Player player1, Player player2) {
        this.board = gameBoard;
        this.player1 = player1;
        this.player2 = player2;
        this.turnToPlay = player1;
        this.status = GameStatus.READY;
        this.winner = null;
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(this.winner);
    }

    public Player getCurrentTurn() {
        return this.turnToPlay;
    }

    public GameStatus makeMove(int row, int col) throws CellOccupiedException, ArrayIndexOutOfBoundsException {
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

    public void showBoard(GameIO gameIO) {
        gameIO.print(board.toString());
    }

    /*public void play() {
        this.status = GameStatus.IN_PROGRESS;

        while(status == GameStatus.IN_PROGRESS) {
            System.out.println(turnToPlay.getName() + "'s turn");
            boolean isEntryValid = false;

            while(!isEntryValid) {
                int row = ("row");
                int col = getCellInput("col");
                try {
                    board.placeSymbol(row, col, turnToPlay.getSymbol());
                    isEntryValid = true;
                } catch (CellOccupiedException exception) {
                    System.out.println(exception.getMessage());
                }
            }

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
    }*/
}
