public class Board {
    private int size;
    private Symbol[][] boardGrid;

    public int getSize() {
        return this.size;
    }

    public Board(int size) {
        this.size = size;
        this.boardGrid = new Symbol[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardGrid[i][j] = Symbol.EMPTY;
            }
        }
    }

    public void makeEntry(int row, int col, Symbol symbol) {
        this.boardGrid[row][col] = symbol;
    }

    public Symbol getWinnerSymbol() {
        int i, j;
        int n = boardGrid.length;
        int count = 0;

        for(i = 0; i < n; i++) {
            count = 0;
            for(j = 0; j < n; j++) {
                if(boardGrid[i][0] != boardGrid[i][j]) {
                    break;
                } else if(boardGrid[i][0] == boardGrid[i][j] && boardGrid[i][j] != Symbol.EMPTY) {
                    count++;
                    if(count == n) {
                        return boardGrid[i][0];
                    }
                }
            }
        }

        count = 0;

        for(i = 0; i < n; i++) {
            count = 0;
            for(j = 0; j < n; j++) {
                if(boardGrid[j][i] != boardGrid[0][i]) {
                    break;
                } else if(boardGrid[0][i] == boardGrid[j][i] && boardGrid[0][i] != Symbol.EMPTY) {
                    count++;
                    if(count == n) {
                        return boardGrid[0][i];
                    }
                }
            }
        }

        count = 0;

        for(i = 0; i < n; i++) {
            if(boardGrid[i][i] == boardGrid[0][0] && boardGrid[i][i] != Symbol.EMPTY) {
                count++;
            }
        }

        if(count == n) {
            return boardGrid[0][0];
        }

        count = 0;

        for(i = n - 1; i >= 0; i--) {
            if (boardGrid[i][n - 1 - i] == boardGrid[0][n - 1] && boardGrid[i][n - 1 - i] != Symbol.EMPTY) {
                count++;
            }
        }

        if(count == n) {
            return boardGrid[0][n - 1];
        }

        return Symbol.EMPTY;
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (boardGrid[i][j] == Symbol.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        System.out.println();
        System.out.println("----------------------------");
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(boardGrid[i][j] == Symbol.EMPTY) {
                    System.out.print("|  ");
                } else {
                    System.out.print("| " + boardGrid[i][j].toString() + " ");
                }
                if(j == size - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            System.out.println("----------------------------");
        }
    }
}
