public class Board {
    private final int size;
    private final Symbol[][] boardGrid;

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

    public void placeSymbol(int row, int col, Symbol symbol) throws CellOccupiedException {
        if(row < 0 || row >= size || col < 0 || col >= size) {
            throw new ArrayIndexOutOfBoundsException("Row / Col is out of bounds!");
        }

        if(this.boardGrid[row][col] != Symbol.EMPTY) {
            throw new CellOccupiedException("Cell is already occupied!!!");
        }

        this.boardGrid[row][col] = symbol;
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

    @Override
    public String toString() {
        String sep = "-".repeat(size * 4 + 1);
        StringBuilder sb = new StringBuilder(sep).append('\n');
        for (Symbol[] row : boardGrid) {
            for (Symbol cell : row) {
                sb.append("| ").append(cell == Symbol.EMPTY ? " " : cell.toString()).append(' ');
            }
            sb.append("|\n").append(sep).append('\n');
        }
        return sb.toString();
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
}
