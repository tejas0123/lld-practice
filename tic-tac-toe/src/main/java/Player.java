public class Player {
    private final String name;
    private final Symbol symbol;

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