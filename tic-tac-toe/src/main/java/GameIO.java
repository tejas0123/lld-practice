public interface GameIO {
    void print(String message);
    Symbol getSymbol(String message);
    String getStringInput(String message);
    int getNumericInput(String message, int min, int max);
}
