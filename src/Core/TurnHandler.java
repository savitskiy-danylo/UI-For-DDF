package Core;

public class TurnHandler {
    private static TurnHandler turnHandler = new TurnHandler();
    private int turn = 1;


    private TurnHandler() { }

    public static TurnHandler getInstance(){ return turnHandler; }

    public void nextTurn(){ turn++; }

    public int getTurn() {
        return turn;
    }
}
