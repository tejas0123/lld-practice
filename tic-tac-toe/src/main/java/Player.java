public class Player {
    private String name;
    private Symbol symbol;

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }
}
