public interface GameRunner {
    void run();
    Player createPlayer();
    Player createSecondPlayer(Symbol player1Symbol);
    Board createGameBoard();
}
